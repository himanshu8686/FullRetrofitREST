package com.fullretrofitrest.jsonPlaceholder;

import com.fullretrofitrest.domain.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * In  @GET("posts") 'posts' is our relative url
 */
public interface JsonPlaceholderApi
{
    /**
     * this method is used for getting response from the json
     * @return list of json objects
     */
    @GET("posts")
    Call<List<Post>> getAllPosts();
}
