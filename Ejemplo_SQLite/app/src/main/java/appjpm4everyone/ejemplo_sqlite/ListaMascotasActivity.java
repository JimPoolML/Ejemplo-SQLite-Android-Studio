package appjpm4everyone.ejemplo_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.Entidades.Mascota;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class ListaMascotasActivity extends AppCompatActivity {

    //Creo las instancias de los distintos objetos
    ListView listViewMascotas;

    //Creo unos arreglos para obtener y publicar dicha información
    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascota;

    //Creo la conexion hacia la BD
    ConexionSQLHelper conectarMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        conectarMascota = new ConexionSQLHelper(getApplicationContext(),"bd_usuario",null,1);
        listViewMascotas = (ListView) findViewById(R.id.listViewMascot);

        //Método que consulta la lista de la BD TABLA_USUARIOS
        consultarListaPersonas();

        //Creo un Adaptador para mostrar en el ListView la info de la BD
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewMascotas.setAdapter(adaptador);

        listViewMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Mascota mascota=listaMascota.get(position);

                //Creo un Intent para mostar la informacion completa en otra actividad
                Intent intent=new Intent(ListaMascotasActivity.this,DetalleMascotaActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("mascota",mascota);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void consultarListaPersonas() {
        //Hago la conexion a la base de datos para la lectura
        SQLiteDatabase db=conectarMascota.getReadableDatabase();

        Mascota mascota=null;
        listaMascota=new ArrayList<Mascota>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);

        //Bucle while para obtener la info de la BD y guardarlos en el ArrayList Mascota
        while (cursor.moveToNext()){
            mascota=new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDueno(cursor.getInt(3));
            listaMascota.add(mascota);
        }
        
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaMascota.size();i++){
            listaInformacion.add(listaMascota.get(i).getIdMascota()+" - "
                    +listaMascota.get(i).getNombreMascota());
        }
    }
}
