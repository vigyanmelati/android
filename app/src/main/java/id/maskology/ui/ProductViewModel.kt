package id.maskology.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.model.Product

class ProductViewModel (Repository: Repository) : ViewModel() {
    val product: LiveData<PagingData<Product>> =
        Repository.getAllProduct().cachedIn(viewModelScope)
}