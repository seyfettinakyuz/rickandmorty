package com.akyuz.rickandmorty.viewmodel;

import androidx.lifecycle.ViewModel;

import com.akyuz.rickandmorty.data.remote.Utils;
import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.data.remote.model.CharacterListResponse;
import com.akyuz.rickandmorty.data.remote.model.Episode;
import com.akyuz.rickandmorty.data.remote.model.Info;
import com.akyuz.rickandmorty.data.remote.repository.CharacterRepository;
import com.akyuz.rickandmorty.model.CharacterInfo;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CharacterListViewModel extends ViewModel implements CharacterRepository.Callback {
    private int lastPage = 1;
    private final CharacterRepository characterRepository;
    private final List<Character> characterList = new ArrayList<>();
    private Info info;
    private Callback callback;
    private boolean loading = false;
    private boolean mLastPage = false;

    @Inject
    public CharacterListViewModel(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
        this.characterRepository.setCallback(this);
    }

    public void getCharacterList() {
        characterRepository.getCharacterList(lastPage);
    }

    public void goDetail(Character character) {
        String episodeNumber = Stream.of(Stream.of(character.getEpisodeList())
                .findLast().get().split("/")).findLast().get();

        if (episodeNumber != null && !episodeNumber.isEmpty() && Utils.isNumeric(episodeNumber)) {
            characterRepository.getEpisode(character, Integer.parseInt(episodeNumber));
        } else {
            callback.goDetail(getCharacterInfo(character,new Episode("","")));
        }

    }

    @Override
    public void characterList(CharacterListResponse response) {
        this.characterList.addAll(response.getCharacterList());
        this.info = response.getInfo();
        this.loading = false;
        this.mLastPage = (info == null || info.getNext() == null || info.getNext().isEmpty());
        callback.characterList(response.getCharacterList());
    }

    @Override
    public void episode(Character character, Episode episode) {
        if (episode == null) {
            episode = new Episode("","");
        }

        callback.goDetail(getCharacterInfo(character,episode));

    }

    @Override
    public void error(String message) {
        callback.error(message);
    }

    private CharacterInfo getCharacterInfo(Character character,Episode episode){
        CharacterInfo characterInfo = new CharacterInfo();
        characterInfo.setCharacterId(character.getId());
        characterInfo.setName(character.getName());
        characterInfo.setImage(character.getImage());
        characterInfo.setStatus(character.getStatus());
        characterInfo.setSpecies(character.getSpecies());
        characterInfo.setNumberOfEpisodes(character.getEpisodeList() != null ?
                character.getEpisodeList().size() : 0);
        characterInfo.setGender(character.getGender());
        characterInfo.setOriginLocationName(character.getOriginLocation() != null
                ? character.getOriginLocation().getName() : "");
        characterInfo.setLastLocationName(character.getLastLocation() != null
                ? character.getLastLocation().getName() : "");
        characterInfo.setLastSeenEpisodeName(episode.getName());
        characterInfo.setLastSeenEpisodeAirDate(episode.getAirDate());
        return characterInfo;
    }

    public void nextPage(){
        loading = true;
        this.lastPage = lastPage+1;
        getCharacterList();
    }

    public boolean isLoading() {
        return loading;
    }

    public boolean isLastPage(){
        return mLastPage;
    }

    public List<Character> sortByNameAsc() {
        return Stream.of(characterList).sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).toList();
    }

    public List<Character> sortByNameDesc() {
        return Stream.of(characterList).sorted((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName())).toList();
    }

    public List<Character> sortByStatusAsc() {
        return Stream.of(characterList).sorted((o1, o2) -> o1.getStatus().compareToIgnoreCase(o2.getStatus())).toList();
    }
    public List<Character> sortByStatusDesc() {
        return Stream.of(characterList).sorted((o1, o2) -> o2.getStatus().compareToIgnoreCase(o1.getStatus())).toList();
    }


    public interface Callback {
        void characterList(List<Character> characterList);

        void error(String message);

        void goDetail(CharacterInfo characterInfo);
    }

}
