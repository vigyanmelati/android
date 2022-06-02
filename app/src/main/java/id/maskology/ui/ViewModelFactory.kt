package id.maskology.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.maskology.data.Repository
import id.maskology.injection.Injection
import id.maskology.ui.detailEcommerce.viewmodel.DetailEcommerceViewModel
import id.maskology.ui.detailProduct.viewmodel.DetailProductViewModel
import id.maskology.ui.main.viewmodel.EducationViewModel
import id.maskology.ui.main.viewmodel.HomeViewModel
import id.maskology.ui.maskStory.viewmodel.MaskStoryViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(EducationViewModel::class.java)) {
            return EducationViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(MaskStoryViewModel::class.java)) {
            return MaskStoryViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailEcommerceViewModel::class.java)) {
            return DetailEcommerceViewModel(repository) as T
        }
        throw IllegalStateException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(application))
            }.also { INSTANCE = it }
    }
}