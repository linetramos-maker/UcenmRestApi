package com.ucenm.ucenmrestapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ucenm.ucenmrestapi.configuracion.ApiConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityCreate extends AppCompatActivity {

    EditText nombres, apellidos, direccion, telefono;
    ImageView foto;
    Button btntakephoto, btnCreate, btnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellido);
        direccion = (EditText) findViewById(R.id.direccion);
        telefono = (EditText) findViewById(R.id.telefono);

        foto = (ImageView) findViewById(R.id.photo);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btntakephoto = (Button) findViewById(R.id.btntakephoto);
        btnLista = (Button) findViewById(R.id.btnLista);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendCreatePerson();
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intent);
            }
        });
    }

    private void SendCreatePerson()
    {
        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("nombres",nombres.getText().toString());
            jsonBody.put("apellidos",apellidos.getText().toString());
            jsonBody.put("direccion",direccion.getText().toString());
            jsonBody.put("telefono",telefono.getText().toString());
            jsonBody.put("foto","");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiConfig.EndPointPost,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            boolean issuccess = response.getBoolean("issuccess");
                            String message = response.getString("message");

                            Toast.makeText(getApplicationContext(),
                                    message, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Log.e("Mensaje", volleyError.toString());
            }
        });


        Volley.newRequestQueue(this).add(request);
    }
}