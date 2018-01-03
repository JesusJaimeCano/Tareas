package e.jesus.tareas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Jesus on 02/01/2018.
 */

public class AdaptadorNotas extends ArrayAdapter<Nota>{

    AdaptadorNotas(Context context , ArrayList<Nota> notas){
        super(context, R.layout.nota_personalizada , notas);
        }

    /*@Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View notapersonalizada =

        TextView tituloNota = notapersonalizada.findViewById(R.id.tituloTexViewPersonalizado);

        Nota notaActual = notasTermindas.get(position);

        tituloNota.setText(notaActual.getTituloNota());

        switch (notaActual.getCategoria()){
            case "Personal":
                notapersonalizada.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case "Trabajo":
                notapersonalizada.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "Urgente":
                notapersonalizada.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case "Salud":
                break;
        }


        return  notapersonalizada;*/




    }
