package com.dpridoy.foodrecipe.view;

import com.dpridoy.foodrecipe.model.Categories;
import com.dpridoy.foodrecipe.model.Meals;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
