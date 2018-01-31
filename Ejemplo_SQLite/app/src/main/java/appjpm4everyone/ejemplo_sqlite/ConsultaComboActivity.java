package appjpm4everyone.ejemplo_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class ConsultaComboActivity extends AppCompatActivity {

    //Realizo las instancias de los objetos
    Spinner combopersonas;
    TextView txtNombre, txtID, txtTelefono;

    //Variables para trabajar la base de datos y el Spinner
    ArrayList<String>   listaPersonas;
    ArrayList<Usuario>  peopleList;
    //Creo la instancia para la consulta
    ConexionSQLHelper conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        //Hago el casting de los Objetos de XML
        combopersonas = (Spinner) findViewById(R.id.PersonasCombo);
        txtID         = (TextView) findViewById(R.id.Idtxt);
        txtNombre     = (TextView) findViewById(R.id.Nombretxt);
        txtTelefono   = (TextView) findViewById(R.id.Telefonotxt);

        //Accedo a la base de datos haciento un casting
        conectar = new ConexionSQLHelper(getApplicationContext(), "bd_usuarios", null, 1);
        
        //Creo un metodo para consultar la lista de personas
        consultarListaPersonas();

        //Realizo el llenado en el Spinner
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPersonas);
        combopersonas.setAdapter(adaptador);

        //Realizo el llenado de los text view con la información de labase de datos
        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    txtID.setText(peopleList.get(position - 1).getId().toString());
                    txtNombre.setText(peopleList.get(position - 1).getNombre());
                    txtTelefono.setText(peopleList.get(position - 1).getTelefono());
                }
                else{//Limpia los TextView si no selecciono la información de la BD
                    txtID.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//Final llenado de los TextView
    }


    private void consultarListaPersonas() {
        //Vamos a leer la base de datos
        SQLiteDatabase db = conectar.getReadableDatabase();

        Usuario persona=null;
        peopleList = new ArrayList<Usuario>();

        //Sentencia SQL
        //select * from Usuario (recordar que el seleccionar todo en SQL es con ´*´
        //va con un null, porque no tiene parametros para identificar valores en la tabla de la BD
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        //Hacemos un bucle para extraer la info de la base de datos y ponerla en el ArrayList

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            //Los Logs me permiten ver la informacion obtenida desde la consola
            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            peopleList.add(persona);

        }
        
        //Construyo la lista en el combo usando un método
        obtenerlista();

    }//Final private void consultarListaPersonas()

    private void obtenerlista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");

        //Cremos un for para la construccion de la lista de personas
        for(int i=0;i<peopleList.size(); i++){
            listaPersonas.add(peopleList.get(i).getId()+ " - "+peopleList.get(i).getNombre());
        }
    }//Final private void obtenerlista()
}
