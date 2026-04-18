package com.ucenm.ucenmrestapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ucenm.ucenmrestapi.configuracion.ApiConfig;
import com.ucenm.ucenmrestapi.configuracion.Personas;
import com.ucenm.ucenmrestapi.configuracion.PersonasAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    ListView listView;
    List<Personas> lista = new ArrayList<>();

    PersonasAdapter Apadpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listViewPersonas);
        Apadpter = new PersonasAdapter(this, lista);
        listView.setAdapter(Apadpter);

        ObtenerPersonas();
    }

    private void ObtenerPersonas() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                ApiConfig.EndPointGet,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            lista.clear();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);

                                Personas person = new Personas();
                                person.setNombres(obj.getString("nombres"));
                                person.setApellidos(obj.getString("apellidos"));
                                person.setDireccion(obj.getString("direccion"));
                                person.setTelefono(obj.getString("telefono"));
                                person.setFoto(obj.getString("foto"));

                                lista.add(person);
                            }

                            Apadpter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Log.e("ErrorJSON", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Mensaje", "Error de Volley: " + volleyError.toString());
                Toast.makeText(ActivityList.this, "Error al conectar con la API", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }
}