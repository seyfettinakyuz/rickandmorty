package com.akyuz.rickandmorty.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.databinding.FragmentDetailBinding;
import com.akyuz.rickandmorty.model.CharacterInfo;
import com.akyuz.rickandmorty.view.base.BaseFragment;
import com.akyuz.rickandmorty.viewmodel.DetailViewModel;

public class DetailFragment extends BaseFragment<DetailViewModel, FragmentDetailBinding> {
    private final static String CHARACTER_INFO_KEY = "characterInfo";

    public static DetailFragment newInstance(CharacterInfo characterInfo) {
        Bundle args = new Bundle();
        args.putParcelable(CHARACTER_INFO_KEY, characterInfo);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            CharacterInfo characterInfo = bundle.getParcelable(CHARACTER_INFO_KEY);
            viewModel.setCharacterInfo(characterInfo);
            if (characterInfo != null) {
                dataBinding.setData(characterInfo);
            }
        }

        dataBinding.favorite.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.onFavorite(isChecked));
        dataBinding.favorite.setChecked(viewModel.isFavorite());
        dataBinding.back.setOnClickListener(v -> getBaseActivity().onBackPressed());

    }

    @Override
    protected Class<DetailViewModel> getViewModel() {
        return DetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }
}
