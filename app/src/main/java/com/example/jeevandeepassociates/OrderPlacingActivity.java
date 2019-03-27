package com.example.jeevandeepassociates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderPlacingActivity extends AppCompatActivity {

    private static final String url = ServerHelper.getIpAddress() + "phpfile.php";
    List<Items> ItemList;
    ProgrammingAdapter Adapter;
    RecyclerView RView;

    static double totalAmt = 0;
    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placing_order);

        if (getIntent().hasExtra("amt")) {
            totalAmt += getIntent().getDoubleExtra("amt", 0);
        }


        ItemList = new ArrayList<>();
        RView = findViewById(R.id.RView);
        RView.setHasFixedSize(true);
        placeOrder = findViewById(R.id.placeOrder);

        RView.setLayoutManager(new LinearLayoutManager(OrderPlacingActivity.this));
        loadProducts();

        RView.setAdapter(new ProgrammingAdapter(ItemList, OrderPlacingActivity.this));
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderPlacingActivity.this, PaymentActivity.class);
                intent1.putExtra("finalAmt", totalAmt);
                startActivity(intent1);
            }
        });


    }

    public void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String product = obj.getString("Product");
                        String code = obj.getString("Code");
                        String packing = obj.getString("Packing");
                        int qty = obj.getInt("Qty");
                        int netRate = obj.getInt("NetRate");

                        Items item = new Items(code, product, packing, qty, netRate);
                        ItemList.add(item);
                    }
                    Adapter = new ProgrammingAdapter(ItemList, OrderPlacingActivity.this);
                    RView.setAdapter(Adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(OrderPlacingActivity.this, "Volley Error " + error, Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(OrderPlacingActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}

