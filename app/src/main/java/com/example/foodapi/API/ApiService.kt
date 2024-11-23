package com.example.foodapi.API

import com.example.foodapi.Model.Data
import com.example.foodapi.Model.ReceipeResponse
import com.example.foodapi.Model.SimilarItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("food_info/")
    suspend fun getRecipeData(): ReceipeResponse

    @GET("food_info/")
    fun getSimilarItems(): Call<List<SimilarItem>>

}