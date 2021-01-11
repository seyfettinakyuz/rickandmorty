package com.akyuz.rickandmorty.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * File Description
 */
public abstract class BaseFragment<VM extends ViewModel, VDB extends ViewDataBinding> extends Fragment {
    private Unbinder unbinder;
    private AppCompatActivity activity;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;

    protected VDB dataBinding;

    protected abstract Class<VM> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        View view = dataBinding.getRoot();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public AppCompatActivity getBaseActivity() {
        return activity;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    public void runOnUiThread(Runnable runnable){
        getBaseActivity().runOnUiThread(runnable);
    }
}
