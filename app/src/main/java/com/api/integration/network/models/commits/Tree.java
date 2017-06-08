
package com.api.integration.network.models.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tree {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("sha")
    @Expose
    public String sha;

}
