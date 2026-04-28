package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "Interaccion")
public class Interaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInteraccion;

    @Column(name = "mensaje",nullable = false,length = 300)
    private String mensaje;

    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idComunidad")
    private Comunidad comunidad;

    public Interaccion() {
    }

    public Interaccion(int idInteraccion, String mensaje, LocalDate fecha, Usuario usuario, Comunidad comunidad) {
        this.idInteraccion = idInteraccion;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.usuario = usuario;
        this.comunidad = comunidad;
    }

    public int getIdInteraccion() {
        return idInteraccion;
    }

    public void setIdInteraccion(int idInteraccion) {
        this.idInteraccion = idInteraccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }
}
