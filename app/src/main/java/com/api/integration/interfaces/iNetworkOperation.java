package com.api.integration.interfaces;


import com.api.integration.network.models.commits.GithubCommitsAPIResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface iNetworkOperation {
    @Headers({"Authorization: token b2dd9a0e799f17060fe9e671c0ded484b6c9b50a", "Accept: application/vnd.github.cloak-preview"})
    @GET("search/commits?q=rails/rails")
    Observable<GithubCommitsAPIResponse> getCommits();
}
