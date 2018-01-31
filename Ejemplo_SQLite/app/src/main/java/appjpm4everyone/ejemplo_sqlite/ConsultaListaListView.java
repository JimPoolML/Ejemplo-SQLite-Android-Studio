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
import android.widget.Toast;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class ConsultaListaListView extends AppCompatActivity {

    //Creo las instancias para los Objetos
    ListView listView_People;

    //Creamos unos Array para mostar la información de la BD
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    //Creo la instancia para la consulta
    ConexionSQLHelper conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista_list_view);
        //Hago el casting de los objetos creados en XML
        listView_People = (ListView) findViewById(R.id.listViewPeople);

        conectar = new ConexionSQLHelper(getApplicationContext(),"bd_usuarios", null, 1);
        
        //Creo un método para consultar la lista de las personas
        consultar_lista_personas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        //enviamos el adaptador al ListView
        listView_People.setAdapter(adaptador);

        //Evento click para que muestre la información en un Toast
        listView_People.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String informar= "id: "+listaUsuarios.get(position).getId()+"\n";
                informar+= "Nombre: "+listaUsuarios.get(position).getNombre()+"\n";
                informar+= "Telefono: "+listaUsuarios.get(position).getTelefono()+"\n";

                //Hacemos un Toast
                Toast.makeText(getApplicationContext(),informar,Toast.LENGTH_LONG).show();

                //Creamos la instancia usuario para poder enviar el objeto a traves de un Intent
                Usuario user=listaUsuarios.get(position);
                Intent intent = new Intent(ConsultaListaListView.this, Detalle_Usuario.class);

                //Objeto Bundle para enviar objetos o parametros
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",user);   //Con putSerializable envio Objetos
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void consultar_lista_personas() {
        //Vamos a leer la base de datos
        SQLiteDatabase db = conectar.getReadableDatabase();

        //Creamos una instancia Usuario
        Usuario usuario=null;
        listaUsuarios = new ArrayList<Usuario>();
        //Select * from usuarios   (Texto de SQL)
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        //Uso de un While para extraer la información
        while(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuario);
        }//Final While cursor.moveToNext

        //Método para obtner la informacion extraida
        obtenerlista();
    }

    private void obtenerlista() {
        listaInformacion = new ArrayList<String>();

        //Ciclo for para llenar el ListView
        for (int i=0; i<listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+ " - "
                    +listaUsuarios.get(i).getNombre());
        }
    }
}
