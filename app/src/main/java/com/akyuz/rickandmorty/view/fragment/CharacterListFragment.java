package com.akyuz.rickandmorty.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.data.remote.Resource;
import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.databinding.FragmentCharacterListBinding;
import com.akyuz.rickandmorty.helper.FragmentTransactions;
import com.akyuz.rickandmorty.helper.PaginationListener;
import com.akyuz.rickandmorty.helper.Utils;
import com.akyuz.rickandmorty.model.CharacterInfo;
import com.akyuz.rickandmorty.view.adapter.CharacterListAdapter;
import com.akyuz.rickandmorty.view.base.BaseFragment;
import com.akyuz.rickandmorty.viewmodel.CharacterListViewModel;

import java.util.List;

public class CharacterListFragment extends BaseFragment<CharacterListViewModel, FragmentCharacterListBinding>
        implements CharacterListViewModel.Callback, CharacterListAdapter.Callback {

    private boolean isGrid = false;
    private PaginationListener paginationListener;
    private boolean nameAsc = true;
    private boolean statusAsc = true;
    private final CharacterListAdapter adapter = new CharacterListAdapter(this, isGrid);


    public static CharacterListFragment newInstance() {
        Bundle args = new Bundle();
        CharacterListFragment fragment = new CharacterListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.setCallback(this);
        dataBinding.recyclerView.setAdapter(adapter);
        setScrollListener();

        viewModel.getCharacterList();
        dataBinding.list.setOnClickListener(v -> {
            boolean listSelected = dataBinding.list.isSelected();
            dataBinding.list.setSelected(!listSelected);
            isGrid = !listSelected;
            setScrollListener();
        });


        dataBinding.sortName.setOnClickListener(v -> {
            List<Character> characters;

            if (nameAsc) {
                characters = viewModel.sortByNameDesc();
            } else {
                characters = viewModel.sortByNameAsc();
            }

            nameAsc = !nameAsc;
            dataBinding.sortName.setSelected(!nameAsc);
            if (characters.size() > 0) {
                dataBinding.setResource(Resource.success(characters));
            }
        });

        dataBinding.sortStatus.setOnClickListener(v -> {
            List<Character> characters;
            if (statusAsc) {
                characters = viewModel.sortByStatusDesc();
            } else {
                characters = viewModel.sortByStatusAsc();
            }
            statusAsc = !statusAsc;
            dataBinding.sortStatus.setSelected(!statusAsc);
            if (characters.size() > 0) {
                dataBinding.setResource(Resource.success(characters));
            }
        });
    }


    @Override
    protected Class<CharacterListViewModel> getViewModel() {
        return CharacterListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_character_list;
    }

    @Override
    public void characterList(List<Character> characterList) {
        if (adapter.isEmpty()) {
            dataBinding.setResource(Resource.success(characterList));
        } else {
            adapter.removeLoadingFooter();
            adapter.addAll(characterList);
        }
    }

    @Override
    public void error(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getBaseActivity()).setTitle("Error").setMessage(message).create();
        runOnUiThread(alertDialog::show);
    }

    @Override
    public void goDetail(CharacterInfo characterInfo) {
        if (characterInfo != null) {
            FragmentTransactions.goFragment("DetailFragment",
                    DetailFragment.newInstance(characterInfo), getBaseActivity());
        }
    }

    @Override
    public void onItemClicked(Character character) {
        viewModel.goDetail(character);
    }

    private void setScrollListener() {
        LinearLayoutManager layoutManager = getLayoutManager();
        dataBinding.recyclerView.setLayoutManager(layoutManager);
        adapter.setGrid(isGrid);
        dataBinding.recyclerView.setAdapter(adapter);
        dataBinding.recyclerView.removeOnScrollListener(paginationListener);
        setPaginationListener(layoutManager);
        dataBinding.recyclerView.addOnScrollListener(paginationListener);
        adapter.notifyDataSetChanged();
    }

    private void setPaginationListener(LinearLayoutManager layoutManager) {
        paginationListener = new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                viewModel.nextPage();
                adapter.addLoadingFooter();
            }

            @Override
            public boolean isLastPage() {
                return viewModel.isLastPage();
            }

            @Override
            public boolean isLoading() {
                return viewModel.isLoading();
            }
        };
    }


    private LinearLayoutManager getLayoutManager() {
        if (isGrid) {
            return new GridLayoutManager(getBaseActivity(),
                    Utils.isTablet(getBaseActivity()) ? 3:2,
                    LinearLayoutManager.VERTICAL,
                    false);
        } else {
            return new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);

        }
    }
}
