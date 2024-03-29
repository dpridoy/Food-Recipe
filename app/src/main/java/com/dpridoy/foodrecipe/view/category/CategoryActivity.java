package com.dpridoy.foodrecipe.view.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.dpridoy.foodrecipe.R;
import com.dpridoy.foodrecipe.adapter.ViewPagerCategoryAdapter;
import com.dpridoy.foodrecipe.model.Categories;
import com.dpridoy.foodrecipe.view.home.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();
    }

    private void initIntent(){
        Intent intent=getIntent();
        List<Categories.Category> categories= (List<Categories.Category>) intent.getSerializableExtra(MainActivity.EXTRA_CATEGORY);

        int position=intent.getIntExtra(MainActivity.EXTRA_POSITION,0);

        ViewPagerCategoryAdapter adapter=new ViewPagerCategoryAdapter(
                getSupportFragmentManager(),
                categories);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();

    }

    private void initActionBar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
