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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "REGISTRO_MERMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroMerma.findAll", query = "SELECT r FROM RegistroMerma r"),
    @NamedQuery(name = "RegistroMerma.findByIdReg", query = "SELECT r FROM RegistroMerma r WHERE r.idReg = :idReg"),
    @NamedQuery(name = "RegistroMerma.findByCantidad", query = "SELECT r FROM RegistroMerma r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RegistroMerma.findByMotivo", query = "SELECT r FROM RegistroMerma r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "RegistroMerma.findByDescripcion", query = "SELECT r FROM RegistroMerma r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RegistroMerma.findByFecha", query = "SELECT r FROM RegistroMerma r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroMerma.findByHora", query = "SELECT r FROM RegistroMerma r WHERE r.hora = :hora"),
    @NamedQuery(name = "RegistroMerma.findByEstado", query = "SELECT r FROM RegistroMerma r WHERE r.estado = :estado")})
public class RegistroMerma implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REG")
    private BigDecimal idReg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOTIVO")
    private String motivo;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "MEDICAMENTO_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Medicamento medicamentoCodigo;

    public RegistroMerma() {
    }

    public RegistroMerma(BigDecimal idReg) {
        this.idReg = idReg;
    }

    public RegistroMerma(BigDecimal idReg, BigInteger cantidad, String motivo, Date fecha, Date hora, String estado) {
        this.idReg = idReg;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public BigDecimal getIdReg() {
        return idReg;
    }

    public void setIdReg(BigDecimal idReg) {
        this.idReg = idReg;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medicamento getMedicamentoCodigo() {
        return medicamentoCodigo;
    }

    public void setMedicamentoCodigo(Medicamento medicamentoCodigo) {
        this.medicamentoCodigo = medicamentoCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReg != null ? idReg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroMerma)) {
            return false;
        }
        RegistroMerma other = (RegistroMerma) object;
        if ((this.idReg == null && other.idReg != null) || (this.idReg != null && !this.idReg.equals(other.idReg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.RegistroMerma[ idReg=" + idReg + " ]";
    }
    
}
