package com.ucenm.ucenmrestapi.configuracion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ucenm.ucenmrestapi.R;

import java.util.List;

public class PersonasAdapter extends ArrayAdapter<Personas> {

    public PersonasAdapter(@NonNull Context context, List<Personas> lista) {
        super(context, 0, lista);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
           convertView = LayoutInflater.from(getContext())
                   .inflate(R.layout.item_personas, parent, false);
        }
        Personas person = getItem(position);

        TextView nombre = convertView.findViewById(R.id.txtnombre);
        TextView apellido = convertView.findViewById(R.id.txtapellido);
        TextView direccion = convertView.findViewById(R.id.txtdireccion);
        TextView telefono = convertView.findViewById(R.id.txttelefono);

        nombre.setText(person.getNombres());
        apellido.setText(person.getApellidos());
        direccion.setText(person.getDireccion());
        telefono.setText(person.getTelefono());

        return convertView;

    }
}
