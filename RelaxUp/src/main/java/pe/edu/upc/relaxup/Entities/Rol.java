package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(name = "rol",nullable = false,length = 30)
    private String rol;

    @Column(name = "descripcion",nullable = false,length = 300)
    private String descripcion;

    public Rol() {
    }

    public Rol(int idRol, String rol, String descripcion) {
        this.idRol = idRol;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
