package appjpm4everyone.ejemplo_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import appjpm4everyone.ejemplo_sqlite.Entidades.Mascota;
import appjpm4everyone.ejemplo_sqlite.utilidades.Utilidades;

public class DetalleMascotaActivity extends AppCompatActivity {

    //Creo las instancias de los distintos objetos
    ConexionSQLHelper conectarMascota;
    TextView campoIdMascota, campoNombreMascota, campoRaza;
    TextView campoIdPersona, campoNombrePersona, campoTelefonoPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conectarMascota=new ConexionSQLHelper (getApplicationContext(),"bd_usuarios",null,1);

        campoIdPersona = (TextView) findViewById(R.id.campoId);
        campoNombrePersona = (TextView) findViewById(R.id.campoNombre);
        campoTelefonoPersona = (TextView) findViewById(R.id.cmpTelefono);

        campoIdMascota = (TextView) findViewById(R.id.campoIdMascota);
        campoNombreMascota = (TextView) findViewById(R.id.campoNombreMascota);
        campoRaza = (TextView) findViewById(R.id.campoRaza);

        //Realizo un Bundle para verificar los datos recibidos
        Bundle objetoEnviado=getIntent().getExtras();
        Mascota mascota=null;

        if(objetoEnviado!=null){
            mascota= (Mascota) objetoEnviado.getSerializable("mascota");
            campoIdMascota.setText(mascota.getIdMascota().toString());
            campoNombreMascota.setText(mascota.getNombreMascota().toString());
            campoRaza.setText(mascota.getRaza().toString());
            //Metodo que consulta los datos de la BD, utilizando parametros
            consultarPersona(mascota.getIdDueno());
        }

    }

    private void consultarPersona(Integer idPersona) {
        //Realizo la conexion a la BD para lectura
        SQLiteDatabase db=conectarMascota.getReadableDatabase();
        String[] parametros={idPersona.toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};
        Toast.makeText(getApplicationContext(),"El documento "+idPersona,Toast.LENGTH_LONG).show();

        try {
            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoIdPersona.setText(idPersona.toString());
            campoNombrePersona.setText(cursor.getString(0));
            campoTelefonoPersona.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            campoNombrePersona.setText("");
            campoTelefonoPersona.setText("");
        }

    }

    //Funcion para finalizar la actividad
    public void ClickVolver(View view) {
        finish();
    }
}
