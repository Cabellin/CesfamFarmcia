/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByRut", query = "SELECT p FROM Paciente p WHERE p.rut = :rut"),
    @NamedQuery(name = "Paciente.findByDv", query = "SELECT p FROM Paciente p WHERE p.dv = :dv"),
    @NamedQuery(name = "Paciente.findByNombres", query = "SELECT p FROM Paciente p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Paciente.findByApellidoPat", query = "SELECT p FROM Paciente p WHERE p.apellidoPat = :apellidoPat"),
    @NamedQuery(name = "Paciente.findByApellidoMat", query = "SELECT p FROM Paciente p WHERE p.apellidoMat = :apellidoMat"),
    @NamedQuery(name = "Paciente.findByFechaNaci", query = "SELECT p FROM Paciente p WHERE p.fechaNaci = :fechaNaci"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Paciente.findByFono", query = "SELECT p FROM Paciente p WHERE p.fono = :fono"),
    @NamedQuery(name = "Paciente.findByFono2", query = "SELECT p FROM Paciente p WHERE p.fono2 = :fono2"),
    @NamedQuery(name = "Paciente.findByCorreo", query = "SELECT p FROM Paciente p WHERE p.correo = :correo"),
    @NamedQuery(name = "Paciente.findByCorreo2", query = "SELECT p FROM Paciente p WHERE p.correo2 = :correo2"),
    @NamedQuery(name = "Paciente.findByPrevision", query = "SELECT p FROM Paciente p WHERE p.prevision = :prevision")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT")
    private BigDecimal rut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DV")
    private Character dv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APELLIDO_PAT")
    private String apellidoPat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APELLIDO_MAT")
    private String apellidoMat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNaci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEXO")
    private Character sexo;
    @Size(max = 15)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Size(max = 50)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "FONO")
    private BigInteger fono;
    @Column(name = "FONO2")
    private BigInteger fono2;
    @Size(max = 50)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 50)
    @Column(name = "CORREO2")
    private String correo2;
    @Size(max = 25)
    @Column(name = "PREVISION")
    private String prevision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteRut")
    private List<Receta> recetaList;

    public Paciente() {
    }

    public Paciente(BigDecimal rut) {
        this.rut = rut;
    }

    public Paciente(BigDecimal rut, Character dv, String nombres, String apellidoPat, String apellidoMat, Date fechaNaci, Character sexo) {
        this.rut = rut;
        this.dv = dv;
        this.nombres = nombres;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.fechaNaci = fechaNaci;
        this.sexo = sexo;
    }

    public BigDecimal getRut() {
        return rut;
    }

    public void setRut(BigDecimal rut) {
        this.rut = rut;
    }

    public Character getDv() {
        return dv;
    }

    public void setDv(Character dv) {
        this.dv = dv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public Date getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(Date fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigInteger getFono() {
        return fono;
    }

    public void setFono(BigInteger fono) {
        this.fono = fono;
    }

    public BigInteger getFono2() {
        return fono2;
    }

    public void setFono2(BigInteger fono2) {
        this.fono2 = fono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo2() {
        return correo2;
    }

    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    @XmlTransient
    public List<Receta> getRecetaList() {
        return recetaList;
    }

    public void setRecetaList(List<Receta> recetaList) {
        this.recetaList = recetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut != null ? rut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Paciente[ rut=" + rut + " ]";
    }
    
}
