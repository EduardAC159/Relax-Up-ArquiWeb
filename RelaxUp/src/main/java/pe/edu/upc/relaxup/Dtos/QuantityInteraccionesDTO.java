package pe.edu.upc.relaxup.Dtos;

public class QuantityInteraccionesDTO {
        private int idUsuario;
        private String Nombre;
        private int Interacciones;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getInteracciones() {
        return Interacciones;
    }

    public void setInteracciones(int interacciones) {
        Interacciones = interacciones;
    }
}
