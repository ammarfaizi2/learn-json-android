package com.example.testjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.txtTitle);
        txtBody  = findViewById(R.id.txtBody);

        getData();
    }

    /*
     * Membuat function getData untuk memparsing data JSON.
     */
    private void getData() {
        /* Menginisialisasi antrian menggunakan RequestQueue. */
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts/2";

        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        /* Request sebuah string dari URL */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonTry = new JSONObject(response.toString());
                    txtBody.setText(jsonTry.getString("body"));
                    txtTitle.setText(jsonTry.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error response: ", error.toString());
            }
        });

        /* Menambahkan request ke antrian */
        queue.add(stringRequest);
    }
}