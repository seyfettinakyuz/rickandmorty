package com.akyuz.rickandmorty.di.builder;

import com.akyuz.rickandmorty.view.fragment.CharacterListFragment;
import com.akyuz.rickandmorty.view.fragment.DetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract DetailFragment buildDetailFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract CharacterListFragment buildCharacterListFragment();
}
