package e.jesus.tareas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Jesus on 14/12/2017.
 */

public class CrearNota extends AppCompatActivity{

    String[] categorias = {"Personal","Trabajo", "Urgente", "Salud"};
    ArrayAdapter adapterCategorias;
    Spinner categoriasSpinner;
    Button guardarNota;
    EditText tituloNota, contenidoNota;
    String elegido = "";
    //ArrayList<Nota> notas;
    Nota notaAeditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_nota);
        guardarNota = findViewById(R.id.guardarNotaButton);
        tituloNota = findViewById(R.id.tituloNotaEdirText);
        contenidoNota = findViewById(R.id.contenidoNotaEditText);


         notaAeditar = (Nota) getIntent().getSerializableExtra("notaAeditar");
        if (notaAeditar != null){
         tituloNota.setText(notaAeditar.getTituloNota());
         contenidoNota.setText(notaAeditar.getContenido());
        }

        categoriasSpinner = findViewById(R.id.categoriaSpinner);
        adapterCategorias = new ArrayAdapter(CrearNota.this, android.R.layout.simple_spinner_item, categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriasSpinner.setAdapter(adapterCategorias);



        categoriasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                elegido = categorias[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        guardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notaAeditar == null){
                    String titulo = tituloNota.getText().toString();
                    String contenido = contenidoNota.getText().toString();

                    Nota nota = new Nota(titulo,contenido,elegido.toString());

                    Intent data = new Intent(CrearNota.this, MainActivity.class);
                    data.putExtra("nota", nota);
                    setResult(RESULT_OK, data);
                    finish();
                }else{
                    String titulo = tituloNota.getText().toString();
                    String contenido = contenidoNota.getText().toString();

                    Nota nota = new Nota(titulo,contenido,elegido.toString());

                    Intent data = new Intent(CrearNota.this, MainActivity.class);
                    data.putExtra("nota", nota);
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });

    }



}
