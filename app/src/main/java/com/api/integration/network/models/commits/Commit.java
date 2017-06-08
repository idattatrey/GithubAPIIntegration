
package com.api.integration.network.models.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("committer")
    @Expose
    public Committer committer;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("tree")
    @Expose
    public Tree tree;
    @SerializedName("comment_count")
    @Expose
    public Integer commentCount;

}
