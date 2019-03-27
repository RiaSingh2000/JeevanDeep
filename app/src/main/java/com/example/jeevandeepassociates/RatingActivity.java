package com.example.jeevandeepassociates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    Button Btn, View;
    RatingBar Rating;
    EditText Comment;
    TextView Text, Rate, Disp;
    static final ArrayList<String> comments = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Btn = findViewById(R.id.btn);
        Rating = findViewById(R.id.rating);
        Comment = findViewById(R.id.comment);
        Text = findViewById(R.id.text);
        Rate = findViewById(R.id.rate);
        Disp = findViewById(R.id.disp);
        View = findViewById(R.id.View);


        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disp.setText("You rated us " + Rating.getRating() + "stars.\n\n THANK YOU");
                String comm = Comment.getText().toString();
                if (!(comm.equals(" ") || comm.equals("")))
                    comments.add(comm);

                Comment.setText("");
                Rating.setRating(0);


            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(RatingActivity.this, CommentsActivity.class);
                startActivity(i);
            }
        });


    }


}

