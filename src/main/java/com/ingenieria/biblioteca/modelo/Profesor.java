/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingenieria.biblioteca.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexis
 */
@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")
    , @NamedQuery(name = "Profesor.findByIdprofesor", query = "SELECT p FROM Profesor p WHERE p.idprofesor = :idprofesor")
    , @NamedQuery(name = "Profesor.findByNombre", query = "SELECT p FROM Profesor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Profesor.findByCorreo", query = "SELECT p FROM Profesor p WHERE p.correo = :correo")
    , @NamedQuery(name = "Profesor.findByDepartamento", query = "SELECT p FROM Profesor p WHERE p.departamento = :departamento")
    , @NamedQuery(name = "Profesor.findByTipoprof", query = "SELECT p FROM Profesor p WHERE p.tipoprof = :tipoprof")
    , @NamedQuery(name = "Profesor.findByContrasena", query = "SELECT p FROM Profesor p WHERE p.contrasena = :contrasena")
    , @NamedQuery(name = "Profesor.findByNumTrabajador", query = "SELECT p FROM Profesor p WHERE p.numTrabajador = :numTrabajador")
    , @NamedQuery(name = "Profesor.findByActivo", query = "SELECT p FROM Profesor p WHERE p.activo = :activo")})
public class Profesor implements Serializable {

    @OneToMany(mappedBy = "idprofesor")
    private Collection<Espaciocultural> espacioculturalCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofesor")
    private Integer idprofesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipoprof")
    private String tipoprof;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "num_trabajador")
    private String numTrabajador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;

    public Profesor() {
    }

    public Profesor(Integer idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Profesor(Integer idprofesor, String nombre, String correo, String departamento, String tipoprof, String contrasena, String numTrabajador, boolean activo) {
        this.idprofesor = idprofesor;
        this.nombre = nombre;
        this.correo = correo;
        this.departamento = departamento;
        this.tipoprof = tipoprof;
        this.contrasena = contrasena;
        this.numTrabajador = numTrabajador;
        this.activo = activo;
    }

    public Integer getIdprofesor() {
        return idprofesor;
    }

    public void setIdprof(Integer idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void cambiaId(int id){
        this.idprofesor = id;
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipoprof() {
        return tipoprof;
    }

    public void setTipoprof(String tipoprof) {
        this.tipoprof = tipoprof;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumTrabajador() {
        return numTrabajador;
    }

    public void setNumTrabajador(String numTrabajador) {
        this.numTrabajador = numTrabajador;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofesor != null ? idprofesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idprofesor == null && other.idprofesor != null) || (this.idprofesor != null && !this.idprofesor.equals(other.idprofesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ingenieria.biblioteca.modelo.Profesor[ idprofesor=" + idprofesor + " ]";
    }

    @XmlTransient
    public Collection<Espaciocultural> getEspacioculturalCollection() {
        return espacioculturalCollection;
    }

    public void setEspacioculturalCollection(Collection<Espaciocultural> espacioculturalCollection) {
        this.espacioculturalCollection = espacioculturalCollection;
    }
    
}
