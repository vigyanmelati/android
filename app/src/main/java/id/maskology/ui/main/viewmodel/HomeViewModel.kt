package id.maskology.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository) : ViewModel() {

    private val _listProduct = MutableLiveData<PagingData<Product>>()
    val listProduct: LiveData<PagingData<Product>> = _listProduct

    init {
        getListProduct()
    }

    private fun getListProduct() = viewModelScope.launch {
        repository.getAllProduct().collect { values ->
            _listProduct.value = values
        }
    }
}