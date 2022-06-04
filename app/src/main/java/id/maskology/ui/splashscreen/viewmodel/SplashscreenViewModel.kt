package id.maskology.ui.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.maskology.data.Repository

class SplashscreenViewModel(private val repository: Repository) : ViewModel() {
    fun getEmailPreferences(): LiveData<String> {
        return repository.getEmailPreferences()
    }
}