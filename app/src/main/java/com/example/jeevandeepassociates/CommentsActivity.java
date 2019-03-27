package com.example.jeevandeepassociates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    ListView Comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Comments = findViewById(R.id.comments);
        ArrayAdapter adapter = new ArrayAdapter(CommentsActivity.this, R.layout.comments_list_view, R.id.comments, RatingActivity.comments);
        Comments.setAdapter(adapter);


    }
}
