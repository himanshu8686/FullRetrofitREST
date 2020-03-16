package com.fullretrofitrest.jsonPlaceholder;

import com.fullretrofitrest.domain.Comment;
import com.fullretrofitrest.domain.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    /**
     * This method is used for get All posts by
     * @param userId and
     * @param sort is used to sort data based on json key
     * @param order is either desc or asc
     * @return
     */
    @GET("posts")
    Call<List<Post>> getAllPostByUserIdAndSortById(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );


    /**
     * This method is our POST reqest method used for creating the json object
     * @param post is embedded into the body of the Request
     * @return async call to create the json object
     */
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    /**
     * This method is another way of creating POST request
     * It is suitable for only simple key-value pair not for complex nested values
     * @param userId is json key
     * @param title is json key
     * @param text is json key
     * @return async call to create the json object
     */
    @FormUrlEncoded
    @POST("posts")
   Call<Post> createPostByFormUrlEncoded(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
            );

    /**
     * In this method PUT request completely replaces or updated the fields in json object
     * @param id to which object is going to be updated
     * @param post object sent into the body of the Request
     * @return async call to create the json object
     */
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post);

    /**
     * In this method PATCH is only update the value which is going to be set in the fields
     * @param id to which object is going to be updated
     * @param post object sent into the body of the Request
     * @return
     */
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,@Body Post post);

    /**
     * In this method we don't want body so we set it to Void the Call<Void>
     * @param id to be deleted
     * @return void i.e empty body
     */
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
