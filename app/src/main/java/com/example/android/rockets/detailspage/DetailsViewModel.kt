package com.example.android.rockets.detailspage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.rockets.network.Rocket

class DetailsViewModel(rocket: Rocket, application: Application) : AndroidViewModel(application) {

    private val _selectedRocket = MutableLiveData<Rocket>()
    val selectedRocket: LiveData<Rocket>
        get() = _selectedRocket

    private val _wikiUrlString = MutableLiveData<String>()
    val wikiUrlString
        get() = _wikiUrlString

    init {
        _selectedRocket.value = rocket
    }

    fun goToWiki() {
        _wikiUrlString.value = _selectedRocket.value?.wikiUrlString
    }

    fun wikiNavigationDone() {
        _wikiUrlString.value = null
    }
}