package com.example.jeevandeepassociates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    TextView finalAmt;
    Button payment;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        double amt = getIntent().getDoubleExtra("finalAmt", 0);
        finalAmt = findViewById(R.id.finalAmt);
        payment = findViewById(R.id.payment);
        list = findViewById(R.id.list);
        finalAmt.setText(Double.toString(amt));


        CustomAdapter adapter = new CustomAdapter(PaymentActivity.this, 0, ProductSelectionActivity.selectedItems);
        list.setAdapter(adapter);


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(OrderPlacingActivity.totalAmt == 0)) {
                    OrderPlacingActivity.totalAmt = 0;

                    openDialog();
                    ProductSelectionActivity.selectedItems.clear();
                } else {
                    Toast.makeText(PaymentActivity.this, "ORDER NOT PLACED", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
    public void openDialog(){
        DialogBox dialogBox=new DialogBox();
        dialogBox.show(getSupportFragmentManager(),"Payment Success");
    }
}
