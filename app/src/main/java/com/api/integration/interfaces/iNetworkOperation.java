package com.api.integration.interfaces;


import com.api.integration.network.models.commits.GithubCommitsAPIResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface iNetworkOperation {
    @Headers({"Accept: application/vnd.github.cloak-preview"})
    @GET("search/commits?q=rails/rails")
    Observable<GithubCommitsAPIResponse> getCommits();
}
