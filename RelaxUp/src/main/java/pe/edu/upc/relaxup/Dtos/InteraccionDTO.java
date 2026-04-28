package pe.edu.upc.relaxup.Dtos;

import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Entities.Usuario;

import java.time.LocalDate;

public class InteraccionDTO {
    private int idInteraccion;
    private String mensaje;
    private LocalDate fecha;
    private int idUsuario;
    private int idComunidad;

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        this.idComunidad = idComunidad;
    }
}
