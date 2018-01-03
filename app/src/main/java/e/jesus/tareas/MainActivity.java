package e.jesus.tareas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO = Menu.FIRST;
    public static final int OPCION_DOS = Menu.FIRST+1;
    public static final int OPCION_TRES = Menu.FIRST+2;

    ListView lista,lista2;

    ArrayAdapter adapter,adapter2;
    ArrayList<Nota> notasPendientes, notasTermindas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaListViewMA);
        lista2 = findViewById(R.id.lista2ListViewMA);
        notasPendientes = new ArrayList<>();
        notasTermindas = new ArrayList<>();


        adapter = new NotaAdaptador();
        adapter2 = new NotaAdaptadorTerminadas();
        lista.setAdapter(adapter);
        lista2.setAdapter(adapter2);

        registerForContextMenu(lista);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){

            case 1:
                if(resultCode == RESULT_OK) {
                    Nota notaRecibida = (Nota) data.getExtras().get("nota");
                    notasPendientes.add(notaRecibida);
                    adapter.notifyDataSetChanged();
                }
                break;
            case 2:

                if(resultCode == RESULT_OK) {
                    Nota notaRecibida = (Nota) data.getExtras().get("nota");
                    notasPendientes.add(notaRecibida);
                    adapter.notifyDataSetChanged();
                }
                break;



        }


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case OPCION_DOS:

                Intent intent = new Intent(MainActivity.this, CrearNota.class);
                intent.putExtra("notaAeditar", notasPendientes.get(elemento.position));
                notasPendientes.remove(elemento.position);
                adapter.notifyDataSetChanged();
                startActivityForResult(intent, 2);

                break;
            case OPCION_TRES:

                notasTermindas.add(notasPendientes.get(elemento.position));
                notasPendientes.remove(elemento.position);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

                break;

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, OPCION_DOS, Menu.NONE, "Editar");
        menu.add(Menu.NONE, OPCION_TRES, Menu.NONE, "Terminar");
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, OPCION_UNO, Menu.NONE, "Crear Tarea");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case OPCION_UNO:
                Intent intent = new Intent(MainActivity.this, CrearNota.class);
                startActivityForResult(intent, 1);
                break;
        }
        return true;
    }


    class NotaAdaptador extends ArrayAdapter<Nota>{
        NotaAdaptador(){
            super(MainActivity.this, R.layout.nota_personalizada, notasPendientes);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View notapersonalizada = getLayoutInflater().inflate(R.layout.nota_personalizada,parent, false);

            TextView tituloNota = notapersonalizada.findViewById(R.id.tituloTexViewPersonalizado);

            Nota notaActual = notasPendientes.get(position);

            tituloNota.setText(notaActual.getTituloNota().toString());
            Log.d("Categoria", notaActual.getCategoria());

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


            return  notapersonalizada;

        }
    }

    class NotaAdaptadorTerminadas extends ArrayAdapter<Nota>{
        NotaAdaptadorTerminadas(){
            super(MainActivity.this, R.layout.nota_personalizada, notasTermindas);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View notapersonalizada = getLayoutInflater().inflate(R.layout.nota_personalizada,parent, false);

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


            return  notapersonalizada;

        }
    }

}
