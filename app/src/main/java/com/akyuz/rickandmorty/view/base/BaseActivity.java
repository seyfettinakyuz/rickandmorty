package com.akyuz.rickandmorty.view.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * File Description
 */
public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {
    private Unbinder unbinder;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @SuppressWarnings("unused")
    public D dataBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }
}