package com.akyuz.rickandmorty.view.activity;

import android.os.Bundle;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.databinding.ActivityMainBinding;
import com.akyuz.rickandmorty.helper.FragmentTransactions;
import com.akyuz.rickandmorty.view.base.BaseActivity;
import com.akyuz.rickandmorty.view.fragment.CharacterListFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransactions.goFragment("CharacterListFragment",CharacterListFragment.newInstance(),this);
    }
}