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

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Recordatorio() {
    }

    public Recordatorio(Usuario usuario, String tipo, LocalDateTime fechaHora, String mensaje, int idRecordatorio) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.idRecordatorio = idRecordatorio;
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
