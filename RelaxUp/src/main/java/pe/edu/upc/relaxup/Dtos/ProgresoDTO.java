package pe.edu.upc.relaxup.Dtos;

import jakarta.persistence.Column;
import pe.edu.upc.relaxup.Entities.MetaEmocional;

import java.time.LocalDate;

public class ProgresoDTO {
    private int idProgreso;
    private int nivelControlIra;
    private LocalDate fecha;
    private String observaciones;
    private int idMetaEmocional;

    public int getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(int idProgreso) {
        this.idProgreso = idProgreso;
    }

    public int getNivelControlIra() {
        return nivelControlIra;
    }

    public void setNivelControlIra(int nivelControlIra) {
        this.nivelControlIra = nivelControlIra;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdMetaEmocional() {return idMetaEmocional;}

    public void setIdMetaEmocional(int idMetaEmocional) {this.idMetaEmocional = idMetaEmocional;}

}
