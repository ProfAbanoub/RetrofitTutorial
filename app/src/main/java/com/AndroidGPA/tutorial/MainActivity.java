package com.AndroidGPA.tutorial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // TextView to display Posts and Comments
    private TextView textViewPost;

    // PlaceholderApi object
    private PlaceholderApi placeholderApi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * This Tutorial created by Abanoub Samir
         * 8 / 9 / 2021
         * AndroidGPA.com
         */

        textViewPost = findViewById(R.id.textview_post);

        // Retrofit Object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Placeholder API
        placeholderApi = retrofit.create(PlaceholderApi.class);

        // Method to get All Posts
        // getPostsList();

        // Method to getComments
        // getCommentsList();

        // Method to post a Post
        // createPost();

        // Method to update Post
        //updatePost();

        // Method to delete Post
        deletePost();
    }

    private void getPostsList() {
        Call<List<Post>> call = placeholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String postData = "";
                    postData += "ID: " + post.getId() + "\n";
                    postData += "User ID: " + post.getUserId() + "\n";
                    postData += "Title: " + post.getTitle() + "\n";
                    postData += "Description: " + post.getDescription() + "\n\n";

                    textViewPost.append(postData);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });
    }

    private void getCommentsList() {
        /* Map<String, String> parameters = new HashMap<>();
         * parameters.put("postId", "7");
         * parameters.put("_sort", "id");
         * parameters.put("_order", "desc"); */

        Call<List<Comment>> call = placeholderApi.getComments("posts/8/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {

                    String commentData = "";
                    commentData += "ID: " + comment.getId() + "\n";
                    commentData += "Post ID: " + comment.getPostId() + "\n";
                    commentData += "Name: " + comment.getName() + "\n";
                    commentData += "Email: " + comment.getEmail() + "\n";
                    commentData += "Body: " + comment.getBody() + "\n\n";

                    textViewPost.append(commentData);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        // Post post = new Post(10, "Title test", "Description test, This is a description from AndroidGPA.com retrofit tutorial");

        Call<Post> call = placeholderApi.createPost(23, "Title test", "Body test, AndroidGPA.com retrofit tutorial, written by Abanoub Samir");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                }

                Post postResponse = response.body();

                String postData = "";
                postData += "Code: " + response.code() + "\n";
                postData += "ID: " + postResponse.getId() + "\n";
                postData += "Post ID: " + postResponse.getUserId() + "\n";
                postData += "Name: " + postResponse.getTitle() + "\n";
                postData += "Body: " + postResponse.getDescription() + "\n\n";

                textViewPost.append(postData);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(7, null, "This is description");

        Call<Post> call = placeholderApi.patchPost(10, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                }

                Post postResponse = response.body();

                String postData = "\n";
                postData += "Code: " + response.code() + "\n";
                postData += "ID: " + postResponse.getId() + "\n";
                postData += "Post ID: " + postResponse.getUserId() + "\n";
                postData += "Name: " + postResponse.getTitle() + "\n";
                postData += "Description: " + postResponse.getDescription() + "\n\n";

                textViewPost.append(postData);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });
    }

    private void deletePost() {
        Call<Void> call = placeholderApi.deletePost(8);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewPost.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });
    }
}