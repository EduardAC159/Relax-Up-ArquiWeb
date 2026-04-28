package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "progreso")
public class Progreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProgreso;

    @Column(name = "nivel_control_ira",nullable = false)
    private int nivelControlIra;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "observaciones",nullable = false,length = 200)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idMetaEmocional")
    private MetaEmocional metaEmocional;

    public Progreso() {
    }

    public Progreso(int idProgreso, int nivelControlIra, LocalDate fecha, String observaciones, MetaEmocional metaEmocional) {
        this.idProgreso = idProgreso;
        this.nivelControlIra = nivelControlIra;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.metaEmocional = metaEmocional;
    }

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

    public MetaEmocional getMetaEmocional() {
        return metaEmocional;
    }

    public void setMetaEmocional(MetaEmocional metaEmocional) {
        this.metaEmocional = metaEmocional;
    }
}
