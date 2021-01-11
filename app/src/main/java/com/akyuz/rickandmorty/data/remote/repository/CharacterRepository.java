package com.akyuz.rickandmorty.data.remote.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.data.remote.ApiService;
import com.akyuz.rickandmorty.data.remote.Utils;
import com.akyuz.rickandmorty.data.remote.model.Character;
import com.akyuz.rickandmorty.data.remote.model.CharacterListResponse;
import com.akyuz.rickandmorty.data.remote.model.Episode;
import com.akyuz.rickandmorty.helper.ResourceManager;
import com.akyuz.rickandmorty.model.CharacterInfo;
import com.annimon.stream.Stream;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

@SuppressLint("StaticFieldLeak")
public class CharacterRepository {
    private final String TAG = "CharacterRepository";
    private final ApiService apiService;
    private Callback callback;

    @Inject
    CharacterRepository(ApiService service) {
        this.apiService = service;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void getCharacterList(int page) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                apiService.getCharacterList(page).enqueue(new retrofit2.Callback<CharacterListResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CharacterListResponse> call,
                                           @NonNull Response<CharacterListResponse> response) {
                        if (response.body() != null && response.body().getCharacterList() != null &&
                                !response.body().getCharacterList().isEmpty()) {
                            callback.characterList(response.body());
                        } else {
                            callback.error("Karakter listesi boş");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CharacterListResponse> call, @NonNull Throwable t) {
                        callback.error(Utils.getCustomErrorMessage(t));
                    }
                });
                return null;
            }
        }.execute();
    }

    public void getEpisode(Character character,int episode) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                apiService.getEpisode(episode)
                        .enqueue(new retrofit2.Callback<Episode>() {
                            @Override
                            public void onResponse(@NonNull Call<Episode> call,
                                                   @NonNull Response<Episode> response) {
                                if(response.body() == null){
                                    Log.e(TAG,ResourceManager.getString(R.string.episodeNotFound));
                                }
                                callback.episode(character,response.body());
                            }

                            @Override
                            public void onFailure(@NonNull Call<Episode> call,
                                                  @NonNull Throwable t) {
                                Log.e(TAG,Utils.getCustomErrorMessage(t));
                                callback.episode(character,null);
                            }
                        });
                return null;
            }
        }.execute();
    }

    public interface Callback {
        void characterList(CharacterListResponse response);

        void episode(Character character,Episode episode);

        void error(String message);
    }
}

