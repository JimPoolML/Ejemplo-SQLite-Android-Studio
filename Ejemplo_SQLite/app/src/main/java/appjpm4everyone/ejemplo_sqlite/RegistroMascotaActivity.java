package appjpm4everyone.ejemplo_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class RegistroMascotaActivity extends AppCompatActivity {

    //Creo las instancias de los Objetos en XML
    EditText razaMascota, nombreMascota;
    Spinner comboDueno;

    //Arreglos para obtener la información de la Base de datos
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> peopleList;

    //Instancia para la conexión con la BD
    ConexionSQLHelper connectarM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        //Hago el Casting con los Objetos de XML
        razaMascota = (EditText) findViewById(R.id.cmpRazaMascota);
        nombreMascota = (EditText) findViewById(R.id.cmpNombreMascota);
        comboDueno = (Spinner) findViewById(R.id.MascotasCombo);

        connectarM = new ConexionSQLHelper(getApplicationContext(),"bd_usuarios",null,1);

        //Creo un metodo para consultar los Nombres de los usuarios registrados
        //en la BD TABLA_USUARIOS_REGISTRADOS
        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        comboDueno.setAdapter(adaptador);



        comboDueno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    //Metodo para consultar los usuarios de la BD llamada TABLA_USUARIO
    private void consultarListaPersonas() {
        SQLiteDatabase db=connectarM.getReadableDatabase();

        Usuario persona=null;
        peopleList =new ArrayList<Usuario>();
        //select * from usuarios, sentencia de SQL
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            peopleList.add(persona);

        }
        obtenerLista();     //Metodo que verifica la info de los posibles dueños para la mascota
    }

    //Obtiene los datos del dueño, para darle a una mascota un dueño
    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<peopleList.size();i++){
            listaPersonas.add(peopleList.get(i).getId()+" - "+peopleList.get(i).getNombre());
        }
    }

    //Si presiono alguno de los Botones
    public void ClickRegistroMascota(View view) {
        switch (view.getId()){
            case R.id.btnRegistrarMascota :
            registrar_mascota();
            break;    
        }//Final switch case
    }

    private void registrar_mascota() {


        SQLiteDatabase db=connectarM.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,nombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,razaMascota.getText().toString());

        int idCombo= (int) comboDueno.getSelectedItemId();
        /**
         * Valida la seleccion del combo de dueños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la pos 0 del combo,
         * sinó entonces se retorna la posicion del combo para consultar el usuario almacenado en la lista
         */
        if (idCombo!=0){
            Log.i("TAMAÑO",peopleList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            int idDuenio=peopleList.get(idCombo-1).getId();
            Log.i("id DUEÑO",idDuenio+"");

            values.put(Utilidades.CAMPO_ID_DUENO,idDuenio);

            Long idResultante=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
        }

    }
}
