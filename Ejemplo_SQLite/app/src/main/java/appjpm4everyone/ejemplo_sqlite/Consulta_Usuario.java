package appjpm4everyone.ejemplo_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class Consulta_Usuario extends AppCompatActivity {

    //Creo las instancias de los Objetos
    EditText campoId, campoNombre, campoTelefono;

    //Creo la instancia para la consulta
    ConexionSQLHelper conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta__usuario);

        //Realiso el casting para el acceso a la base de datos
        conectar = new ConexionSQLHelper(getApplicationContext(),"bd_usuarios", null , 1);

        //Realizo el casting desde XML
        campoId = (EditText) findViewById(R.id.cmpconID);
        campoNombre = (EditText) findViewById(R.id.cmpconNombre);
        campoTelefono = (EditText) findViewById(R.id.cmpconTelefono);

    }

    public void ClickConsulta(View view) {

        switch (view.getId()) {

            case (R.id.btnBuscar):
                //buscar();
                buscarSQL();
            break;

            case (R.id.btnActualizar):
                //Actualizar el usuario
                actualizar_usuario();
            break;

            case (R.id.btnEliminar):
                //Eliminar el usuario
                eliminar_usuario();
            break;

        }//Final Switch case

    }//Final ClickConsulta

    private void eliminar_usuario() {
        //Abro la base de Datos para poder Editarla
        SQLiteDatabase db=conectar.getWritableDatabase();

        //Hago un Arreglo de String para obtener el ID del usuario en la Base de Datos
        String[] parametros = {campoId.getText().toString()};

        try{
        //Secuencia en SQL para eliminar un usuario a partir de cierto parametro, en este caso el ID
            db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+" =?",parametros);
            Toast.makeText(getApplicationContext(), "Usuario Eliminado", Toast.LENGTH_LONG).show();
            campoId.setText("");    //Limpia los EditText
            limpiar();
            db.close();
        }catch (Exception e){
            //Publica un Toast indicando que la actualización fue incorrecta
            Toast.makeText(getApplicationContext(),"Eliminación Incorrecta",Toast.LENGTH_LONG).show();
        }
    }

    private void actualizar_usuario() {
        //Abro la base de Datos para poder Editarla
        SQLiteDatabase db=conectar.getWritableDatabase();

        //Hago un Arreglo de String para obtener el ID del usuario en la Base de Datos
        String[] parametros = {campoId.getText().toString()};

        try {
            //
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

            //Actualizacion de datos
            db.update(Utilidades.TABLA_USUARIO, values, Utilidades.CAMPO_ID + "=?", parametros);
            Toast.makeText(getApplicationContext(), "Usuario Actualizado", Toast.LENGTH_LONG).show();
            db.close();
        }catch (Exception e){
            //Publica un Toast indicando que la actualización fue incorrecta
            Toast.makeText(getApplicationContext(),"Actualización Incorrecta",Toast.LENGTH_LONG).show();
        }

    }

    private void buscarSQL() {
        //Abro la base de Datos para poder Leerla
        SQLiteDatabase db=conectar.getReadableDatabase();

        //Hago un Arreglo de String para obtener el ID del usuario en la Base de Datos
        String[] parametros = {campoId.getText().toString()};

        try{
            //Sentencia SQL
            //select nombre,telefono from Usuario where id=?
            /*Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
            " FROM "+Utilidades.TABLA_USUARIO+" WHERE"+Utilidades.CAMPO_ID+"=? ",parametros);*/

            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            //Escribimos la info consultada en los EditText
            campoNombre.setText(cursor.getString(0));       //Es 0 porque el 1er parametro de campos es Utilidades.CAMPO_NOMBRE
            campoTelefono.setText(cursor.getString(1));     //Es 1 porque el 1er parametro de campos es Utilidades.CAMPO_TELEFONO
            //cursor.close();

        }catch (Exception e){
            //Publica un Toast indicando que el documento no existe
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    //Metodo que busca usuarios en la base de datos
    private void buscar() {
        //Abro la base de Datos para poder Leerla
        SQLiteDatabase db=conectar.getReadableDatabase();

        //Hago un Arreglo de String para obtener el ID del usuario en la Base de Datos
        String[] parametros = {campoId.getText().toString()};

        //Hago un Arreglo de String para obtener el ID del usuario en la Base de Datos
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        //Se implementa un try catch, para evitar errores
        try{

        //Hacemos uso de la clase cursor para la consulta de información en la DB
        Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID+"=? ",parametros,null,null,null);
        cursor.moveToFirst();

        //Escribimos la info consultada en los EditText
        campoNombre.setText(cursor.getString(0));       //Es 0 porque el 1er parametro de campos es Utilidades.CAMPO_NOMBRE
        campoTelefono.setText(cursor.getString(1));     //Es 1 porque el 1er parametro de campos es Utilidades.CAMPO_TELEFONO
        cursor.close();

        }catch (Exception e){
        //Publica un Toast indicando que el documento no existe
        Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        limpiar();
        }












    }

    //Limpiar, deja los EditText vacios
    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }

}//Final Activity
