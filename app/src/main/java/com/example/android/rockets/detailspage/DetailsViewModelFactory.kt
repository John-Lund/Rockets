package com.example.android.rockets.detailspage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.rockets.network.Rocket

class DetailsViewModelFactory(
    private val rocket: Rocket,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(rocket, application) as T
        }
        throw IllegalArgumentException("Can't create a view model with this class")
    }
}