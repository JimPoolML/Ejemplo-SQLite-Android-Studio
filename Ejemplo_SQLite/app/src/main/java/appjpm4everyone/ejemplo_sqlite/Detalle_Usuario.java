package appjpm4everyone.ejemplo_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;

public class Detalle_Usuario extends AppCompatActivity {

    //Creo las distintas instancias de XML
    TextView Idzona, Nombrezona, Telefonozona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__usuario);

        //Relaizo el casting de las instancias creadas
        Idzona = (TextView) findViewById(R.id.zonaID);
        Nombrezona = (TextView) findViewById(R.id.zonaNombre);
        Telefonozona = (TextView) findViewById(R.id.zonaTelefono);

        //Creo un bundle para obtener la información
        Bundle objetoRecibido = getIntent().getExtras();
        //Objeto Usuario para "desmenuzar" la información
        Usuario userDetalle=null;

        if(objetoRecibido!=null){
            //con getSerializable obtengo la informacion de los objetos enviadops entre actividades
            userDetalle = (Usuario) objetoRecibido.getSerializable("usuario");
            Idzona.setText(userDetalle.getId().toString());
            Nombrezona.setText(userDetalle.getNombre().toString());
            Telefonozona.setText(userDetalle.getTelefono().toString());
        }else{//No recibio nada
            Idzona.setText("");
            Nombrezona.setText("");
            Telefonozona.setText("");
        }

    }

    //Funcion para finalizar la actividad
    public void ClickRegreso(View view) {
        finish();
    }
}
