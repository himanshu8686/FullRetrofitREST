package com.fullretrofitrest.jsonPlaceholder;

import com.fullretrofitrest.domain.Comment;
import com.fullretrofitrest.domain.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    /**
     * this method is used for getting comments according to the postId
     * @param postId
     * @return list of comments for json objects
     */
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    /**
     *This method os used for getting all post by particular user
     * @return list of json objects of posts
     * here @Query automatically added ?= to the url and append userId
     */
    @GET("posts")
    Call<List<Post>> getAllPostsByUserId(@Query("userId") int userId);
}
