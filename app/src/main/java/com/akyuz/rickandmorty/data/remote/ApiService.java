package com.akyuz.rickandmorty.data.remote;

import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.data.remote.model.CharacterListResponse;
import com.akyuz.rickandmorty.data.remote.model.Episode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("character")
    Call<CharacterListResponse> getCharacterList(@Query("page") int page);

    @GET("character/{id}")
    Call<Character> getCharacter(@Path("id") int id);

    @GET("episode/{id}")
    Call<Episode> getEpisode(@Path("id") int id);
}
