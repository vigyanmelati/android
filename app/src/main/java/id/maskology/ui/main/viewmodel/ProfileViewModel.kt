package id.maskology.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    fun getNamePreferences(): LiveData<String> {
        return repository.getNamePreferences()
    }

    fun getImageUrlPreferences(): LiveData<String> {
        return repository.getImageUrlPreferences()
    }

    fun deleteAuthPreferences() = viewModelScope.launch{
        repository.deleteAuthPreferences()
    }

}