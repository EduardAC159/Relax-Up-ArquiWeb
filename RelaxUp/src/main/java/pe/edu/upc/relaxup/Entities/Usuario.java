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
    private String nombres;

    @Column(name ="email" ,nullable = false,length = 50)
    private String email;

    @Column(name = "direccion",nullable = false,length = 50)
    private String direccion;

    @Column(name = "celular",nullable = false)
    private int celular;

    @Column(name = "username", unique = true, length = 30)
    private String username;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombres, String email, String direccion, int celular) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.email = email;
        this.direccion = direccion;
        this.celular = celular;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
}


