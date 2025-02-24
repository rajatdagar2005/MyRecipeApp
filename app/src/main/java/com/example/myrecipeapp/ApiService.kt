package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// making connection with URL and making it compatible with kotlin
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

// creating our API service to use API
val recipeService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService{
    // category respose is data class in category which contain entity categories that has list of category data class
    // this function returns categories response
    // suspend fun is used in place of function when you want URL to work

    // get allows us to do HTTP request
    @GET("categories.php")
    suspend fun getCategories (): CategoriesRespose
}