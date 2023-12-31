package com.ahmeturunveren.goshop.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmeturunveren.goshop.data.model.FavoriteModel
import com.ahmeturunveren.goshop.data.repository.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repo: ProductsRepo): ViewModel() {

    val getFavoriteProduct: MutableLiveData<List<FavoriteModel>> = MutableLiveData()

    init {
        getFavorite()
    }

    private fun getFavorite() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteProduct.postValue(repo.getProductToFavorite())
    }

    fun deleteFromFavorites(favId:Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteProductFromFavorite(favId)
        getFavorite()
    }
}