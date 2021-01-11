package com.akyuz.rickandmorty.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.akyuz.rickandmorty.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

import com.akyuz.rickandmorty.viewmodel.CharacterListViewModel;
import com.akyuz.rickandmorty.viewmodel.DetailViewModel;
import com.akyuz.rickandmorty.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindCharacterListViewModel(CharacterListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsDetailViewModel(DetailViewModel viewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
