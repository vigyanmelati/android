package id.maskology.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.Result
import id.maskology.data.model.CategoryProduct
import id.maskology.data.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository) : ViewModel() {

    private val _listProduct = MutableLiveData<PagingData<Product>>()
    val listProduct: LiveData<PagingData<Product>> = _listProduct

    private val _listCategoryProduct = MutableLiveData<Result<List<CategoryProduct>>>()
    val listCategoryProduct: LiveData<Result<List<CategoryProduct>>> = _listCategoryProduct

    init {
        getListProduct()
        getListCategoryProduct()
    }

    private fun getListProduct() = viewModelScope.launch {
        repository.getAllProduct().cachedIn(viewModelScope).collect { values ->
            _listProduct.value = values
        }
    }

    private fun getListCategoryProduct() = viewModelScope.launch {
        repository.getAllCategoryProduct().collect { values ->
            _listCategoryProduct.value = values
        }
    }
}