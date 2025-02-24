package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState:State<RecipeState> = _categoryState // this variable is created so that private val can be acessed to other classes

    // like a constructure
    init {
        fetchCategories()
    }


    private fun fetchCategories(){
        // viewmodelscope is used to process the suspend functions
        viewModelScope.launch {
            // try and catch is used to stop our programme from crashing when any error occur
            try {
                val respose = recipeService.getCategories()
                // _categorystate ko override karke update kar rahe hai
                _categoryState.value = _categoryState.value.copy(
                    list = respose.categories, // this is real categories from URL
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error Fetching Category ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null  // we use question mark after data type when we have chances of error
    )
}