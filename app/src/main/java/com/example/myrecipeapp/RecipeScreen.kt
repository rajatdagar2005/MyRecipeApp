package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun recipeScreen(modifier:Modifier = Modifier,viewState:MainViewModel.RecipeState,navigateToDetail:(Category)->Unit){ // this because this is not MainActivity file , also lamda function
    //val recipeViewModel : MainViewModel = viewModel()
    //val viewState by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()){
        when{ // like switch case -> is required for this
            // loading sign
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                    strokeWidth = 4.dp,
//                    color = Color.Blue
//                )
            }
            viewState.error != null ->{
                Text(text = "ERROR OCCUR")
            }
            else->{
                // display categories
                categoryScreen(categories = viewState.list,navigateToDetail)
            }
        }
    }
}
// this function is for displaying on screen
@Composable
fun categoryScreen(categories:List<Category>,navigateToDetail:(Category)->Unit){ // basically categories is everything that link of data contains
    // maximum no. of columns we want is 2
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
            category ->
            CategoryItem(category = category,navigateToDetail)
        }
    }
}

// how each item looks like
@Composable
fun CategoryItem(category: Category,navigateToDetail:(Category)->Unit){  // lamda function for navigation
    Column (modifier = Modifier
        .padding(8.dp)
        .fillMaxSize().clickable { navigateToDetail(category) }, // clickable is used on columns click
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        // strCategoryThumb contains image URL
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb) , contentDescription = null, modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f))

        Text(text = category.strCategory, color = Color.Black, style = TextStyle(fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 4.dp))
    }
}