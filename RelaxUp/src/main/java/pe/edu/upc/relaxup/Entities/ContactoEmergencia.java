package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto_emergencia")
public class ContactoEmergencia
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;

    @Column(name = "nombre_contacto",nullable = false,length = 30)
    private String nombre;

    @Column(name = "celular_contacto",nullable = false)
    private int celular;

    @Column(name = "relacion",length = 30,nullable = false)
    private String relacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public ContactoEmergencia() {
    }

    public ContactoEmergencia(int idContacto, String nombre, int celular, String relacion, Usuario usuario) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.celular = celular;
        this.relacion = relacion;
        this.usuario = usuario;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
