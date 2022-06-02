package id.maskology.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository) : ViewModel() {

    private val _listProduct = MutableLiveData<PagingData<Product>>()
    val listProduct: LiveData<PagingData<Product>> = _listProduct

    private val _listCategory = MutableLiveData<PagingData<Category>>()
    val listCategory: LiveData<PagingData<Category>> = _listCategory

    init {
        getListProduct()
        getListCategory()
    }

    private fun getListProduct() = viewModelScope.launch {
        repository.getAllProduct().cachedIn(viewModelScope).collect { values ->
            _listProduct.value = values
        }
    }

    private fun getListCategory() = viewModelScope.launch {
        repository.getAllCategory().cachedIn(viewModelScope).collect { values ->
            _listCategory.value = values
        }
    }
}