package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "emergencia")
public class Emergencia
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmergencia;

    @Column(name ="tipo",nullable = false,length = 30)
    private String tipo;

    @Column(name = "descripcion",nullable = false,length = 200)
    private String descripcion;

    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;

    public Emergencia() {
    }

    public Emergencia(int idEmergencia, String tipo, String descripcion, LocalDate fecha) {
        this.idEmergencia = idEmergencia;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getIdEmergencia() {
        return idEmergencia;
    }

    public void setIdEmergencia(int idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
