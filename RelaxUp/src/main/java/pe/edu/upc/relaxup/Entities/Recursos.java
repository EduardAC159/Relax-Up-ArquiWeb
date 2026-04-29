package pe.edu.upc.relaxup.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recursos")
public class Recursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecursos;

    @Column(name = "titulo",nullable = false,length = 30)
    private String titulo;

    @Column(name = "tipo",nullable = false,length = 30)
    private String tipo;

    @Column(name = "enlace",nullable = false,length = 50)
    private String enlace;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Recursos() {
    }

    public Recursos(int idRecursos, String titulo, String tipo, String enlace, Usuario usuario) {
        this.idRecursos = idRecursos;
        this.titulo = titulo;
        this.tipo = tipo;
        this.enlace = enlace;
        this.usuario = usuario;
    }

    public int getIdRecursos() {
        return idRecursos;
    }

    public void setIdRecursos(int idRecursos) {
        this.idRecursos = idRecursos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
