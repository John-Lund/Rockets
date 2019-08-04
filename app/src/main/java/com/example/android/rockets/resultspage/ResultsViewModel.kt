package com.example.android.rockets.resultspage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.rockets.network.Rocket

import com.example.android.rockets.network.RocketApi
import com.example.android.rockets.network.convertStringToRocketsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class RocketApiServiceStatus { LOADING, ERROR, DONE }
class ResultsViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _rocketsList = MutableLiveData<List<Rocket>>()
    val rocketsList: LiveData<List<Rocket>>
        get() = _rocketsList

    private val _loadStatus = MutableLiveData<RocketApiServiceStatus>()
    val loadStatus: LiveData<RocketApiServiceStatus>
        get() = _loadStatus

    init {
        getRocketData()
    }

    fun getRocketData() {
        coroutineScope.launch {
            lateinit var resultString: String
            val getResponseDeferred = RocketApi.retrofitService.getRocketsAsync()
            try {
                _loadStatus.value = RocketApiServiceStatus.LOADING
                resultString = getResponseDeferred.await()
                val list = convertStringToRocketsList(resultString)
                _loadStatus.value = RocketApiServiceStatus.DONE
                if (list.isNotEmpty()) {
                    _rocketsList.value = list
                }
            } catch (e: Exception) {
                _rocketsList.value = ArrayList()
                _loadStatus.value = RocketApiServiceStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToSelectedRocket = MutableLiveData<Rocket>()
    val navigateToSelectedRocket: LiveData<Rocket>
        get() = _navigateToSelectedRocket

    fun displayDetailsfragment(rocket: Rocket) {
        _navigateToSelectedRocket.value = rocket
    }

    fun navigateToDetailsFragmentComplete() {
        _navigateToSelectedRocket.value = null
    }
}