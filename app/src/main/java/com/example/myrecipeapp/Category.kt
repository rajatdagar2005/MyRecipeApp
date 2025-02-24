package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // makes an object into string and when want to use revert it back to object
data class Category(val idCategory:String,
                    val strCategory:String,
                    val strCategoryThumb:String,
                    val strCategoryDescription:String):Parcelable

//  this is copied from list all category link from mealDB.com
//  {
//    "categories": [
//    {
//      "idCategory": "1",
//      "strCategory": "Beef",
//      "strCategoryThumb": "https://www.themealdb.com/images/category/beef.png",
//      "strCategoryDescription": "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"
//    }
//    ]
//   }

data class CategoriesRespose(val categories:List<Category>) 