package com.akyuz.rickandmorty.viewmodel;

import androidx.lifecycle.ViewModel;

import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.helper.Constants;
import com.akyuz.rickandmorty.helper.PrefUtils;
import com.akyuz.rickandmorty.helper.Utils;
import com.akyuz.rickandmorty.model.CharacterInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailViewModel extends ViewModel {
    private CharacterInfo characterInfo;
    private List<String> favoriteList = new ArrayList<>();
    @Inject
    public DetailViewModel(){

    }

    public void setCharacterInfo(CharacterInfo characterInfo){
        this.characterInfo = characterInfo;
    }

    public void onFavorite(boolean favorite){
        if (favorite) {
            if (!favoriteList.contains(characterInfo.recordInfo())) {
                favoriteList.add(characterInfo.recordInfo());
                PrefUtils.setStringPref(Constants.FAVORITE_CHARACTER_LIST_KEY, Utils.setFavoriteList(favoriteList));
            }
        } else {
            if (favoriteList.contains(characterInfo.recordInfo())) {
                favoriteList.remove(favoriteList.indexOf(characterInfo.recordInfo()));
                PrefUtils.setStringPref(Constants.FAVORITE_CHARACTER_LIST_KEY, Utils.setFavoriteList(favoriteList));
            }
        }
    }

    public boolean isFavorite(){
        if(characterInfo == null){
            return false;
        }

        String favoriteCharacterList = PrefUtils.getStringPref(Constants.FAVORITE_CHARACTER_LIST_KEY, "");
        favoriteList = Utils.getFavoriteList(favoriteCharacterList);
        return favoriteList.contains(characterInfo.recordInfo());

    }
}
