package appjpm4everyone.ejemplo_sqlite.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import appjpm4everyone.ejemplo_sqlite.R;
import appjpm4everyone.ejemplo_sqlite.Entidades.Usuario;

/**
 * Created by SIMON on 31/01/2018.
 */

public class AdaptadorListaPersona extends
        RecyclerView.Adapter<AdaptadorListaPersona.PersonasViewHolder>{

    //Cremos el ArrayList que contega los datos de la clase Usuario
    ArrayList<Usuario> listaUsuario;

    public AdaptadorListaPersona(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    //Este método enlaza este adaptador creado con el archivo lista_persona_items.XML (donde está el TextView)
    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_personas,null,false);
        return new PersonasViewHolder(view);  //Retorna el mismo view que fue creado

    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.documento.setText(listaUsuario.get(position).getId().toString());
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTelefono());

    }

    @Override
    //Metodo que retorna el tamaño de la lista creada
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        //Creo las instancias de los abjetos del archivo lista_items_personas.XML
        TextView documento,nombre,telefono;

        public PersonasViewHolder(View itemView) {
                super(itemView);
            //Hago casting con las instancias creadas,
            //Recordadndo que debo hacer el "Puente"
                documento = (TextView) itemView.findViewById(R.id.textDocumento);
                nombre = (TextView) itemView.findViewById(R.id.textNombre);
                telefono = (TextView) itemView.findViewById(R.id.textTelefono);
            }


    }
}
