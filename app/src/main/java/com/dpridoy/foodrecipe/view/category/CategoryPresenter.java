package com.dpridoy.foodrecipe.view.category;

import com.dpridoy.foodrecipe.Utils;
import com.dpridoy.foodrecipe.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    void getMealByCategory(String category){
        view.showLoading();
        Call<Meals> mealsCall= Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body()!=null){
                    view.setMeals(response.body().getMeals());
                }else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
