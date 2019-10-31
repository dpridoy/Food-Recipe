package com.dpridoy.foodrecipe.view.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dpridoy.foodrecipe.R;
import com.dpridoy.foodrecipe.Utils;
import com.dpridoy.foodrecipe.adapter.RecyclerViewHomeAdapter;
import com.dpridoy.foodrecipe.adapter.ViewPagerHeaderAdapter;
import com.dpridoy.foodrecipe.model.Categories;
import com.dpridoy.foodrecipe.model.Meals;
import com.dpridoy.foodrecipe.view.category.CategoryActivity;
import com.dpridoy.foodrecipe.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.viewPagerHeader) ViewPager viewPagerMeal;
    @BindView(R.id.recyclerCategory) RecyclerView recyclerViewCategory;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter=new MainPresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal,this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20,0,20,0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView mealName = v.findViewById(R.id.mealName);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);

        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category,this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager=new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
//            Toast.makeText(this,category.get(position).getStrCategory(),Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);

        });
    }

    @Override
    public void onErrorLoading(String message) {
        new AlertDialog.Builder(this).setTitle(message).setMessage(message).show();
        Utils.showDialogMessage(this,"Title",message);
    }
}
