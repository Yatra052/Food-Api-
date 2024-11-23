package com.example.foodapi.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapi.API.RetrofitInstance
import com.example.foodapi.Adapter.FactAdapter
import com.example.foodapi.Adapter.SimilarAdapter
import com.example.foodapi.Model.Data
import com.example.foodapi.Model.NutritionInfoScaled
import com.example.foodapi.Model.ReceipeResponse
import com.example.foodapi.Model.SimilarItem
import com.example.foodapi.R
import com.example.foodapi.ViewModel.FoodInfoViewModel
import com.example.foodapi.databinding.FragmentFoodInfoBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodInfoFragment : Fragment() {
    private val foodInfoViewModel: FoodInfoViewModel by viewModels()
    private lateinit var factsAdapter: FactAdapter
    private lateinit var binding: FragmentFoodInfoBinding
    private lateinit var similarRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodInfoBinding.inflate(inflater, container, false)
        foodInfoViewModel.fetchFoodItem()
        foodInfoViewModel.fetchGenericFacts()
        foodInfoViewModel.fetchNutritionData()

        foodInfoViewModel.similarItems()



        factsAdapter = FactAdapter(emptyList())
        binding.genericFacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genericFacts.adapter = factsAdapter


        foodInfoViewModel.facts.observe(viewLifecycleOwner) { facts ->
            factsAdapter.updateData(facts)

        }


        foodInfoViewModel.nutrition.observe(viewLifecycleOwner) { nutrition ->
            displayNutrition(nutrition)

        }



        foodInfoViewModel.foodItem.observe(viewLifecycleOwner, Observer { data ->
            displayRecipe(data)
        })


        return (binding.root)

    }


    private fun displayRecipe(data: Data) {
        binding.foodname.text = data.name
        binding.outof.text = data.health_rating.toString()
        binding.desc.text = data.description


    }



    private fun displayNutrition(nutritionList: List<NutritionInfoScaled>?) {
        nutritionList?.let {
            if (it.isNotEmpty()) {

                binding.n1.text = "${it[0].name}"
                binding.e1.text = "${it[0].value.toInt()}"
                binding.p1.text = "${it[0].units}"

            }
            if (it.size > 1) {
             binding.n2.text = "${it[1].name}"
                binding.e2.text = "${it[1].value.toInt()}"
                binding.p2.text = "${it[1].units}"

            }
            if (it.size > 2) {
                binding.n3.text = "${it[2].name}"
                binding.e3.text = "${it[2].value.toInt()}"
                binding.p3.text = "${it[2].units}"

            }
            if (it.size > 3) {
              binding.n4.text = "${it[3].name}"
                binding.e4.text = "${it[3].value.toInt()}"
                binding.p4.text = "${it[3].units}"

            }
            if (it.size > 4) {
              binding.n5.text = "${it[4].name}"
                binding.e5.text = "${it[4].value.toInt()}"
                binding.p5.text = "${it[4].units}"


            }
            if (it.size > 5) {
              binding.n5.text = "${it[5].name}"
                binding.e5.text = "${it[5].value.toInt()}"
                binding.p5.text = "${it[5].units}"


            }

        }
    }




}

