package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recordatorio")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecordatorio;

    @Column(name = "mensaje",nullable = false,length = 300)
    private String mensaje;

    @Column(name = "fechaHora",nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "tipo",nullable = false,length = 30)
    private String tipo;

    @Column(name = "estado",nullable = false,length = 30)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Recordatorio() {
    }

    public Recordatorio(int idRecordatorio, String mensaje, LocalDateTime fechaHora, String tipo, String estado, Usuario usuario) {
        this.idRecordatorio = idRecordatorio;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.estado = estado;
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
