package com.api.integration.interfaces;


import com.api.integration.network.models.commits.GithubCommitsAPIResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface iNetworkOperation {
    @Headers({"Authorization: token ce9431c099ae55e0a524394a8a406007c35c83f1", "Accept: application/vnd.github.cloak-preview"})
    @GET("search/commits?q=rails/rails")
    Observable<GithubCommitsAPIResponse> getCommits();
}
