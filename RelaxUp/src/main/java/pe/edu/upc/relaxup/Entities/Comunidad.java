package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comunidad")
public class Comunidad
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComunidad;

    @Column(name = "nombre",nullable = false,length = 30)
    private String nombre;

    @Column(name = "descripcion",nullable = false,length = 200)
    private String descripcion;

    public Comunidad() {
    }

    public Comunidad(int idComunidad, String nombre, String descripcion) {
        this.idComunidad = idComunidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        this.idComunidad = idComunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
