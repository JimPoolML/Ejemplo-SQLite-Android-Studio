package appjpm4everyone.ejemplo_sqlite.Entidades;

import java.io.Serializable;

/**
 * Created by SIMON on 25/01/2018.
 */

//Implements Serializable se debe a que puede pasar este objeto entre actividades
public class Mascota implements Serializable {

    //Creo los Objetos de la clase Mascota
    private Integer idDueno;
    private Integer idMascota;
    private String nombreMascota;
    private String raza;

    public Mascota(Integer idDueno, Integer idMascota, String nombreMascota, String raza) {
        this.idDueno = idDueno;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }
    public Mascota(){

    }

    public Integer getIdDueno() {return idDueno;    }

    public void setIdDueno(Integer idDueno) { this.idDueno = idDueno;  }

    public Integer getIdMascota() {return idMascota;  }

    public void setIdMascota(Integer idMascota) {this.idMascota = idMascota;   }

    public String getNombreMascota() {  return nombreMascota;   }

    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota;    }

    public String getRaza() {    return raza;  }

    public void setRaza(String raza) {  this.raza = raza;  }

}//Final public class Mascota
