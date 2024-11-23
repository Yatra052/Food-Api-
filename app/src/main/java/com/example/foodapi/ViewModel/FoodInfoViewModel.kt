package com.example.foodapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapi.API.RetrofitInstance
import com.example.foodapi.Model.Data
import com.example.foodapi.Model.NutritionInfoScaled
import com.example.foodapi.Model.ReceipeResponse
import com.example.foodapi.Model.SimilarItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodInfoViewModel: ViewModel() {

    private val _foodItem = MutableLiveData<Data>()
    val foodItem: LiveData<Data> = _foodItem

    private val _facts = MutableLiveData<List<String>>()
    val facts: LiveData<List<String>> get() = _facts

    private val _nutrition = MutableLiveData<List<NutritionInfoScaled>>()
    val nutrition: LiveData<List<NutritionInfoScaled>> get() = _nutrition

    private val _similar = MutableLiveData<List<SimilarItem>>()
    val similar: LiveData<List<SimilarItem>> get() = _similar

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchFoodItem() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getRecipeData()
            if (response.success) {
                _foodItem.value = response.data
            }
        }
    }

    fun fetchGenericFacts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRecipeData()
                _facts.postValue(response.data.generic_facts)
            } catch (e: Exception) {
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    fun fetchNutritionData(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRecipeData()
                _nutrition.postValue(response.data.nutrition_info_scaled)

            }catch (e:Exception){
                _errorMessage.postValue("Failed to load data: ${e.message}")

            }
        }
    }


    fun similarItems(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRecipeData()
                Log.d("API", response.data.similar_items.size.toString())
                 _similar.postValue(response.data.similar_items)
            }catch (e:Exception){
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }


    fun fetchSimilarItems() {

        RetrofitInstance.api.getSimilarItems().enqueue(object : Callback<List<SimilarItem>> {
            override fun onResponse(
                call: Call<List<SimilarItem>>,
                response: Response<List<SimilarItem>>
            ) {
                if (response.isSuccessful) {
                    // Update LiveData with the response data
                    _similar.value = response.body()
                } else {
                    Log.e("FoodInfoViewModel", "Error fetching similar items: ${response.message()}")
                    _similar.value = emptyList()  // Handle empty response
                }
            }

            override fun onFailure(call: Call<List<SimilarItem>>, t: Throwable) {
                Log.e("FoodInfoViewModel", "Network request failed: ${t.message}")
                _similar.value = emptyList()
            }
        })
    }
}







