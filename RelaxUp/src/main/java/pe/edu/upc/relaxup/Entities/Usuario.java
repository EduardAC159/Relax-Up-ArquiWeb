package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name ="nombre" ,nullable = false,length =50 )
    private String Nombres;

    @Column(name ="email" ,nullable = false,length = 50)
    private String Email;

    @Column(name = "direccion",nullable = false,length = 50)
    private String Direccion;

    @Column(name = "usuario",nullable = false,length =50 )
    private String Usuario;

    @Column(name = "contraseña",nullable = false,length = 50)
    private String Contraseña;

    @Column(name = "celular",nullable = false)
    private int Celular;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombres, String email, String direccion, String usuario, String contraseña, int celular) {
        this.idUsuario = idUsuario;
        Nombres = nombres;
        Email = email;
        Direccion = direccion;
        Usuario = usuario;
        Contraseña = contraseña;
        Celular = celular;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int celular) {
        Celular = celular;
    }
}


