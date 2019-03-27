package com.example.jeevandeepassociates;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button Call,Rate,Locate,Order;
TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"Welcome!!",Toast.LENGTH_LONG).show();
        Call=findViewById(R.id.call_us);
        Rate=findViewById(R.id.rate_us);
        Locate=findViewById(R.id.locate_us);
        Order=findViewById(R.id.order);
        Name=findViewById(R.id.name);

        Call.setOnClickListener(this);
        Rate.setOnClickListener(this);
        Locate.setOnClickListener(this);
        Order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_us: Intent intent1=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9334007779"));
            startActivity(intent1);
            break;
            case R.id.rate_us:startActivity(new Intent(MainActivity.this, RatingActivity.class));
            break;
            case R.id.locate_us:startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("geo:23.7878334,86.4134198")));
            break;
            case R.id.order:startActivity(new Intent(MainActivity.this, OrderPlacingActivity.class));
                break;
        }
    }
}

