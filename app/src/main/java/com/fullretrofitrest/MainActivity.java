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

        makeGetRequestForAllPosts();
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
