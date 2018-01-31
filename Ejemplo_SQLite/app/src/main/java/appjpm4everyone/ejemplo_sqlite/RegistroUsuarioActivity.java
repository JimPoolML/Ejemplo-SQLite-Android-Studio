package appjpm4everyone.ejemplo_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class RegistroUsuarioActivity extends AppCompatActivity {

    //Creo las instancias de los Objetos
    EditText campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        //Realizo el casting desde XML
        campoId = (EditText) findViewById(R.id.cmpID);
        campoNombre = (EditText) findViewById(R.id.cmpNombre);
        campoTelefono = (EditText) findViewById(R.id.cmpTelefono);


    }

    public void ClickRegistro(View view) {
       //UsuariosRegistro();   //Implementando Content Values
        UsuariosRegistroSQL();  //Implementando las sentencias de SQL
    }//Final ClickRegistro

    private void UsuariosRegistroSQL() {

        //Accedo a la base de datos haciento un casting
        ConexionSQLHelper conectar=new ConexionSQLHelper(this,"bd_usuarios",null,1);

        //Abro la base de Datos para poder Editarla
        SQLiteDatabase db=conectar.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')

        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoTelefono.getText().toString()+"')";


        //Sentencia en SQLite
        db.execSQL(insert);
        Toast.makeText(getApplicationContext(), "Registro Completo: ",Toast.LENGTH_SHORT).show();
        db.close();

    }

    private void UsuariosRegistro() {

        //Accedo a la base de datos haciento un casting
        ConexionSQLHelper conectar = new ConexionSQLHelper(this, "bd_usuarios", null, 1);

        //Abro la base de Datos para poder Editarla
        SQLiteDatabase db = conectar.getWritableDatabase();

        //Creamos los ContentValues, que tienen una clave y un valor asociado
        ContentValues values = new ContentValues();

        //Con los Values, accedemos a los distintos valores del la Base de Datos
        values.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

        //Para insertar los datos en la Base de Datos utilizamos la variable Long
        Long idResustado = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        //Hacemos un toast para visualizar el resultado
        Toast.makeText(getApplicationContext(), "Id Registro: "+ idResustado,Toast.LENGTH_SHORT).show();
        db.close();







    }
}
