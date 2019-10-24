package com.dpridoy.foodrecipe.api;

import com.dpridoy.foodrecipe.model.Categories;
import com.dpridoy.foodrecipe.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MenuApi {

    @GET("random.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();
}
