package com.dpridoy.foodrecipe.view.detail;

import com.dpridoy.foodrecipe.model.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
