package id.maskology.ui.detailProduct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.Result
import id.maskology.data.model.Category
import id.maskology.data.model.Store
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailProductViewModel(private val repository: Repository) : ViewModel() {
    private val _store = MutableLiveData<Result<Store>>()
    val store: LiveData<Result<Store>> = _store

    fun getStore(id: String) = viewModelScope.launch {
        repository.getStore(id).collect { values ->
            _store.value = values
        }
    }
}