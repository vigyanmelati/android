package id.maskology.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.maskology.data.Repository
import id.maskology.data.model.Category
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EducationViewModel(private val repository: Repository) : ViewModel() {

    private val _listCategory = MutableLiveData<PagingData<Category>>()
    val listCategory: LiveData<PagingData<Category>> = _listCategory

    init {
        getListCategory()
    }

    private fun getListCategory() = viewModelScope.launch {
        repository.getAllCategory().cachedIn(viewModelScope).collect { values ->
            _listCategory.value = values
        }
    }
}