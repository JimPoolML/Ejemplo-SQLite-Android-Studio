package appjpm4everyone.ejemplo_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Accedo a la base de datos haciento un casting
        ConexionSQLHelper conectar = new ConexionSQLHelper(this, "bd usuarios", null, 1);
    }

    public void Click_Opciones(View view) {
        //Si el boton fue presionado
        Intent miIntent = null;

        switch (view.getId()){
            case R.id.btn_op_Registrar:
                miIntent = new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                break;

            case R.id.btn_op_Reg_Mascota:
               miIntent = new Intent(MainActivity.this, RegistroMascotaActivity.class);
                break;

            case R.id.btn_op_Consultar:
                miIntent = new Intent(MainActivity.this, Consulta_Usuario.class);
                break;

            case R.id.btn_op_Spinner:
                miIntent = new Intent(MainActivity.this, ConsultaComboActivity.class);
                break;

            case R.id.btn_op_ListView:
                miIntent = new Intent(MainActivity.this, ConsultaListaListView.class);
                break;

            case R.id.btn_op_List_Mascota:
               miIntent = new Intent(MainActivity.this, ListaMascotasActivity.class);
                break;

            case R.id.btn_op_RecyclerView:
                miIntent = new Intent(MainActivity.this, RecyclerListaPersonas.class);
                break;

        }//Final switch case

        if(miIntent!=null){
            startActivity(miIntent);
        }
    }
}
