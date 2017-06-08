
package com.api.integration.network.models.commits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("sha")
    @Expose
    public String sha;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("commit")
    @Expose
    public Commit commit;
    @SerializedName("author")
    @Expose
    public Author_ author;
    @SerializedName("committer")
    @Expose
    public Committer_ committer;
    @SerializedName("parents")
    @Expose
    public List<Parent> parents = null;
    @SerializedName("repository")
    @Expose
    public Repository repository;
    @SerializedName("score")
    @Expose
    public Float score;

}
