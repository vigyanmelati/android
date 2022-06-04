package id.maskology.ui.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.maskology.data.Repository
import id.maskology.data.model.Store
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val repository: Repository) : ViewModel()  {

    fun getEmailPreferences(): LiveData<String> {
        return repository.getEmailPreferences()
    }

    fun saveAuthPreferences(id: String, name: String, token: String) = viewModelScope.launch {
        repository.saveAuthPreferences(id, name, token)
    }
}