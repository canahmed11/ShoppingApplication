package com.ahmeturunveren.goshop.data.source.remote

import com.ahmeturunveren.goshop.data.model.ProductResponseModel
import com.ahmeturunveren.goshop.util.constants.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface GoShopApi {

    // get category names
    @GET(Constants.ALL_CATEGORIES)
    suspend fun getAllCategories(): List<String>

    @GET(Constants.SPECIFIC_CATEGORY)
    suspend fun getSpecificCategory(
        @Path("category") category:String
    ):List<ProductResponseModel>
}