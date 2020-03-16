package com.fullretrofitrest;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fullretrofitrest.domain.Comment;
import com.fullretrofitrest.domain.Post;
import com.fullretrofitrest.jsonPlaceholder.JsonPlaceholderApi;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_result;
    private ProgressBar progressBar_horizontal;

    public static final String BASE_URL="https://jsonplaceholder.typicode.com/";
    private JsonPlaceholderApi jsonPlaceholderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result= findViewById(R.id.tv_result);
        progressBar_horizontal=findViewById(R.id.progressBar_horizontal);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar_horizontal.setVisibility(View.VISIBLE);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceholderApi=retrofit.create(JsonPlaceholderApi.class);

        //makeGetRequestForAllPosts();

        //getCommentsByPostId();

        // getAllPostsByUserIdByUrlRewriting();

        //getAllPostsByMultipleQueryParameter();

        createPost();
    }

    /**
     * This createPost() method will create the post and from the response we retrieve the values and put it into our text view
     *
     */
    private void createPost()
    {
        Post post=new Post(23,"Himanshu","he is genius!");
        Call<Post> call=jsonPlaceholderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response)
            {
                progressBar_horizontal.setVisibility(View.GONE);
                if (!response.isSuccessful())
                {
                    tv_result.setText("Code:"+response.code());
                    return;
                }

                Post postResponse=response.body();

                String content="";
                content=content+"Response Code from server: "+response.code() +"\n";
                content=content+"ID : "+postResponse.getId() +"\n";
                content=content+"User ID : "+postResponse.getUserId() +"\n";
                content=content+"Title : "+postResponse.getTitle() +"\n";
                content=content+"Text : "+postResponse.getText() +"\n\n";

                tv_result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

    /**
     * This method is to fetch get all posts by having multiple query parameters
     * in our case an array of Integer of userId which is sorted by id and order descending way
     */
    private void getAllPostsByMultipleQueryParameter()
    {
        Call<List<Post>> call= jsonPlaceholderApi.getAllPostByUserIdAndSortById(new Integer[]{2,4,6},"id","desc");
        call.enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                progressBar_horizontal.setVisibility(View.GONE);
                if (!response.isSuccessful())
                {
                    tv_result.setText("Code:"+response.code());
                    return;
                }

                List<Post> posts=response.body();
                for (Post post:posts)
                {
                    String content="";
                    content=content+"ID : "+post.getId() +"\n";
                    content=content+"User ID : "+post.getUserId() +"\n";
                    content=content+"Title : "+post.getTitle() +"\n";
                    content=content+"Text : "+post.getText() +"\n\n";

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });

    }

    /**
     * this method uses query parameter for filter out
     * and uses
     *  @GET("posts")
     *     Call<List < Post>> getAllPostByUserIdAndSortById(
     *             @Query("userId") Integer[] userId,
     *             @Query("_sort") String sort,
     *             @Query("_order") String order
     *     );
     */
    private void getAllPostsByUserIdByUrlRewriting()
    {
        Call<List<Post>> call= jsonPlaceholderApi.getAllPostsByUserId(4);
        call.enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                progressBar_horizontal.setVisibility(View.GONE);
                if (!response.isSuccessful())
                {
                    tv_result.setText("Code:"+response.code());
                    return;
                }

                List<Post> posts=response.body();
                for (Post post:posts)
                {
                    String content="";
                    content=content+"ID : "+post.getId() +"\n";
                    content=content+"User ID : "+post.getUserId() +"\n";
                    content=content+"Title : "+post.getTitle() +"\n";
                    content=content+"Text : "+post.getText() +"\n\n";

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }


    /**
     * this method is used for making GET request to fetch all the comments json objects
     * and use
     * @GET("posts/{id}/comments")
     * Call<List<Comment>> getComments(@Path("id") int postId);
     */
    private void getCommentsByPostId()
    {
        Call<List<Comment>> call=jsonPlaceholderApi.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response)
            {
                progressBar_horizontal.setVisibility(View.GONE);
                if (!response.isSuccessful())
                {
                    tv_result.setText("Code:"+response.code());
                    return;
                }
                List<Comment> comments=response.body();
                for (Comment comment:   comments)
                {
                    String content="";
                    content=content+"ID : "+comment.getId() +"\n";
                    content=content+"Post ID : "+comment.getPostId() +"\n";
                    content=content+"Name : "+comment.getName() +"\n";
                    content=content+"Email : "+comment.getEmail() +"\n";
                    content=content+"Text : "+comment.getText() +"\n\n";

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

    /**
     *  this method is used for making GET request to fetch all the json objects
     *  and use    Call<List<Post>> getAllPosts() for this
     */
    private void makeGetRequestForAllPosts()
    {
        Call<List<Post>> call= jsonPlaceholderApi.getAllPosts();
        call.enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                progressBar_horizontal.setVisibility(View.GONE);
                if (!response.isSuccessful())
                {
                    tv_result.setText("Code:"+response.code());
                    return;
                }

                List<Post> posts=response.body();
                for (Post post:posts)
                {
                    String content="";
                    content=content+"ID : "+post.getId() +"\n";
                    content=content+"User ID : "+post.getUserId() +"\n";
                    content=content+"Title : "+post.getTitle() +"\n";
                    content=content+"Text : "+post.getText() +"\n\n";

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }
}
