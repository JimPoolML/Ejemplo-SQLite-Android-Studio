package appjpm4everyone.ejemplo_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.Adaptadores.AdaptadorListaPersona;
import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class RecyclerListaPersonas extends AppCompatActivity {

    //Creo las instancias, tanto de los objetos como de la conexión a la BD

    //ArrayList que contiene lainformación de la BD
    ArrayList<Usuario> listaUsuario;
    //RecyclerView para mostrarlo de forma personalizada
    RecyclerView recyclerViewUsuario;
    //Conexion con la BD
    ConexionSQLHelper conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_lista_personas);

        //Realizo el casting de los objetos contenidos en las Instancias

        //Conexion a la BD
        conectar = new ConexionSQLHelper(getApplicationContext(),"bd_usuarios",null,1);
        //ArrayList con la clase Usuarios
        listaUsuario = new ArrayList<>();
        //RecyclerView personalizado
        recyclerViewUsuario = (RecyclerView) findViewById(R.id.personasRecycler);
        //Pongo el RecyclerView en modo List vertical
        recyclerViewUsuario.setLayoutManager(new LinearLayoutManager(this));

        //Creo un método para consultar la BD
        consultarListaPersonas();

        //Creamos un adaptapter aplicandole el ArrayList, para el llenado de datos en el RecyclerView
        AdaptadorListaPersona adapter = new AdaptadorListaPersona(listaUsuario);
        //Asignamos ese Adaptador al RecyclerView
        recyclerViewUsuario.setAdapter(adapter);

    }

    private void consultarListaPersonas() {
        //Objeto para leer la base de datos
        SQLiteDatabase db = conectar.getReadableDatabase();
        //Objeto de la clase usuario, para el llenado de la información
        Usuario usuario=null;

        //Sentencia SQL
        //select * from Usuario (recordar que el seleccionar todo en SQL es con ´*´
        //va con un null, porque no tiene parametros para identificar valores en la tabla de la BD
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        //Bucle While para obtener toda la información de la BD
        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            //Paso los datos del Cursor al ArrayList
            listaUsuario.add(usuario);
        }
    }
}
