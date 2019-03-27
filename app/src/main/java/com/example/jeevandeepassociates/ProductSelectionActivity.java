package com.example.jeevandeepassociates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductSelectionActivity extends AppCompatActivity {
   static final ArrayList<SelectedItems> selectedItems=new ArrayList<SelectedItems>();
  TextView product,packing,qty,netRate,amt;
  EditText reqQty;
  Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_product);
        product=findViewById(R.id.product);
        packing=findViewById(R.id.packing);
        qty=findViewById(R.id.qty);
        netRate=findViewById(R.id.netRate);
        amt=findViewById(R.id.amt);
        reqQty=findViewById(R.id.reqQty);
        order=findViewById(R.id.order);
        String prd= getIntent().getStringExtra("product");
        String pck=getIntent().getStringExtra("packing");
        int quantity=getIntent().getIntExtra("qty",0);
        int rate=getIntent().getIntExtra("netRate",0);


        product.setText(prd);
        packing.setText(pck);
        qty.setText(Integer.toString(quantity));
        netRate.setText(Integer.toString(rate));

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount;
                if(Integer.parseInt(reqQty.getText().toString())<=Integer.parseInt(qty.getText().toString())) {
                    amount = Integer.parseInt(reqQty.getText().toString()) * Integer.parseInt(netRate.getText().toString());

                    amt.setText(Double.toString(amount));
                    Intent intent2 = new Intent(ProductSelectionActivity.this, OrderPlacingActivity.class);
                    intent2.putExtra("amt", amount);
                    startActivity(intent2);
                    setChanges();
                    Toast.makeText(ProductSelectionActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

                    selectedItems.add(new SelectedItems(product.getText().toString(),Integer.parseInt(reqQty.getText().toString()),Double.parseDouble(amt.getText().toString())));



                }
                else{
                    Toast.makeText(ProductSelectionActivity.this, "Out of Stock", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

       private  void  setChanges(){
        String url=ServerHelper.getIpAddress()+"changes.php";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ProductSelectionActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductSelectionActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
             params.put("Qty",Integer.toString(Integer.parseInt(qty.getText().toString())-Integer.parseInt(reqQty.getText().toString())));
             params.put("Product",product.getText().toString());
           return params;

            }
        };
           RequestQueue queue= Volley.newRequestQueue(ProductSelectionActivity.this);
           queue.add(request);
    }
}
