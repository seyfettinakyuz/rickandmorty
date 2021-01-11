package com.akyuz.rickandmorty.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.databinding.ItemCharacterBinding;
import com.akyuz.rickandmorty.databinding.ItemGridCharacterBinding;
import com.akyuz.rickandmorty.databinding.ItemProgressBinding;
import com.akyuz.rickandmorty.helper.BaseViewHolder;
import com.akyuz.rickandmorty.helper.Constants;
import com.akyuz.rickandmorty.helper.PrefUtils;
import com.akyuz.rickandmorty.helper.ResourceManager;
import com.akyuz.rickandmorty.helper.Utils;
import com.akyuz.rickandmorty.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CharacterListAdapter extends BaseAdapter<BaseViewHolder<Character>, Character> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoadingAdded = false;
    private boolean isGrid;

    private List<Character> data;
    private final Callback callback;

    public CharacterListAdapter(@NonNull Callback callback, boolean isGrid) {
        data = new ArrayList<>();
        this.callback = callback;
        this.isGrid = isGrid;
    }

    public void setGrid(boolean isGrid) {
        this.isGrid = isGrid;
    }

    @Override
    public void setData(List<Character> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder<Character> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NORMAL) {
            if (isGrid) {
                return ItemGridViewHolder.create(LayoutInflater.from(parent.getContext()), parent, callback);
            } else {
                return ItemViewHolder.create(LayoutInflater.from(parent.getContext()), parent, callback);
            }
        } else {
            return ProgressHolder.create(LayoutInflater.from(parent.getContext()), parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Character> viewHolder, int i) {
        viewHolder.onBind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == data.size() - 1 && isLoadingAdded) ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    static class ProgressHolder extends BaseViewHolder<Character> {
        private static ProgressHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemProgressBinding itemMainListBinding = ItemProgressBinding.inflate(inflater, parent, false);
            return new ProgressHolder(itemMainListBinding);
        }

        final ItemProgressBinding binding;

        public ProgressHolder(ItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(Character data) {

        }
    }

    static class ItemViewHolder extends BaseViewHolder<Character> {

        private static ItemViewHolder create(LayoutInflater inflater, ViewGroup parent, Callback callback) {
            ItemCharacterBinding itemMainListBinding = ItemCharacterBinding.inflate(inflater, parent, false);
            return new ItemViewHolder(itemMainListBinding, callback);
        }

        final ItemCharacterBinding binding;

        private ItemViewHolder(ItemCharacterBinding binding, Callback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onItemClicked(binding.getData()));
        }

        @Override
        public void onBind(Character item) {
            binding.setData(item);
            binding.favorite.setImageDrawable(ResourceManager.getDrawable(
                    isFavorite(item) ? R.drawable.ic_favorite : R.drawable.ic_not_favorite));

            binding.executePendingBindings();
        }
    }

    static class ItemGridViewHolder extends BaseViewHolder<Character> {

        private static ItemGridViewHolder create(LayoutInflater inflater, ViewGroup parent, Callback callback) {
            ItemGridCharacterBinding itemMainListBinding = ItemGridCharacterBinding.inflate(inflater, parent, false);
            return new ItemGridViewHolder(itemMainListBinding, callback);
        }

        final ItemGridCharacterBinding binding;

        private ItemGridViewHolder(ItemGridCharacterBinding binding, Callback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onItemClicked(binding.getData()));
        }

        @Override
        public void onBind(Character item) {
            binding.setData(item);
            binding.favorite.setImageDrawable(ResourceManager.getDrawable(
                    isFavorite(item) ? R.drawable.ic_favorite : R.drawable.ic_not_favorite));

            binding.executePendingBindings();
        }
    }

    private static boolean isFavorite(Character item) {
        String favoriteCharacterList = PrefUtils.getStringPref(Constants.FAVORITE_CHARACTER_LIST_KEY, "");
        List<String> favoriteList = Utils.getFavoriteList(favoriteCharacterList);
        return favoriteList.contains(item.recordInfo());
    }

    public void add(Character character) {
        data.add(character);
        notifyItemInserted(data.size() - 1);
    }

    public void addAll(List<Character> characterList) {
        for (Character character : characterList) {
            add(character);
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Character());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = data.size() - 1;
        Character item = getItem(position);

        if (item != null) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Character getItem(int position) {
        return data.get(position);
    }

    public interface Callback {
        void onItemClicked(Character item);
    }
}
