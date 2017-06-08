package com.api.integration.interfaces;


import com.api.integration.network.models.commits.GithubCommitsAPIResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface iNetworkOperation {
    @Headers({"Authorization: token 37d78f00904d9fb33e8ab538ec296d259c03f161", "Accept: application/vnd.github.cloak-preview"})
    @GET("search/commits?q=rails/rails")
    Observable<GithubCommitsAPIResponse> getCommits();
}
