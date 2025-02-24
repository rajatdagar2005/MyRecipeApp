package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    // same as you did in recipe Screen
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    // same as you did in Navigation sample
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route ){
        // remember this is c not C
        composable(route = Screen.RecipeScreen.route){
            recipeScreen(viewState = viewState,navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("nothing",it)  // here we are storing the category
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route=Screen.DetailScreen.route){
            // here category is storing previous category
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("nothing")?: Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }
}