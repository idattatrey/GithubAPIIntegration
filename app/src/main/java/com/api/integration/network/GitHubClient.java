package com.api.integration.network;

import com.api.integration.constants.Constants;
import com.api.integration.interfaces.iNetworkOperation;
import com.api.integration.network.models.commits.GithubCommitsAPIResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class GitHubClient {

    private static GitHubClient instance;
    private iNetworkOperation iNetworkOperation;

    private GitHubClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        iNetworkOperation = retrofit.create(iNetworkOperation.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<GithubCommitsAPIResponse> getRailsCommits() {
        return iNetworkOperation.getCommits();
    }
}