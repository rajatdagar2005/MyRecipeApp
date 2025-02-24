package com.example.myrecipeapp

sealed class Screen(val route:String) { // to ensure type safety by restricting types matched at compile time
    object RecipeScreen:Screen("RecipeScreen")
    object DetailScreen:Screen("DetailScreen")
} // new thing