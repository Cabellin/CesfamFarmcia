/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "RECETA_MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetaMedicamento.findAll", query = "SELECT r FROM RecetaMedicamento r"),
    @NamedQuery(name = "RecetaMedicamento.findByMedicamentoCodigo", query = "SELECT r FROM RecetaMedicamento r WHERE r.recetaMedicamentoPK.medicamentoCodigo = :medicamentoCodigo"),
    @NamedQuery(name = "RecetaMedicamento.findByRecetaId", query = "SELECT r FROM RecetaMedicamento r WHERE r.recetaMedicamentoPK.recetaId = :recetaId"),
    @NamedQuery(name = "RecetaMedicamento.findByCantidad", query = "SELECT r FROM RecetaMedicamento r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RecetaMedicamento.findByUnidadC", query = "SELECT r FROM RecetaMedicamento r WHERE r.unidadC = :unidadC"),
    @NamedQuery(name = "RecetaMedicamento.findByPeriodicidad", query = "SELECT r FROM RecetaMedicamento r WHERE r.periodicidad = :periodicidad"),
    @NamedQuery(name = "RecetaMedicamento.findByUnidadP", query = "SELECT r FROM RecetaMedicamento r WHERE r.unidadP = :unidadP"),
    @NamedQuery(name = "RecetaMedicamento.findByExtension", query = "SELECT r FROM RecetaMedicamento r WHERE r.extension = :extension"),
    @NamedQuery(name = "RecetaMedicamento.findByUnidadE", query = "SELECT r FROM RecetaMedicamento r WHERE r.unidadE = :unidadE"),
    @NamedQuery(name = "RecetaMedicamento.findByCantTotal", query = "SELECT r FROM RecetaMedicamento r WHERE r.cantTotal = :cantTotal")})
public class RecetaMedicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecetaMedicamentoPK recetaMedicamentoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UNIDAD_C")
    private String unidadC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIODICIDAD")
    private BigInteger periodicidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UNIDAD_P")
    private String unidadP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXTENSION")
    private BigInteger extension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UNIDAD_E")
    private String unidadE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_TOTAL")
    private BigInteger cantTotal;
    @JoinColumn(name = "MEDICAMENTO_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicamento medicamento;
    @JoinColumn(name = "RECETA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public RecetaMedicamento() {
    }

    public RecetaMedicamento(RecetaMedicamentoPK recetaMedicamentoPK) {
        this.recetaMedicamentoPK = recetaMedicamentoPK;
    }

    public RecetaMedicamento(RecetaMedicamentoPK recetaMedicamentoPK, BigInteger cantidad, String unidadC, BigInteger periodicidad, String unidadP, BigInteger extension, String unidadE, BigInteger cantTotal) {
        this.recetaMedicamentoPK = recetaMedicamentoPK;
        this.cantidad = cantidad;
        this.unidadC = unidadC;
        this.periodicidad = periodicidad;
        this.unidadP = unidadP;
        this.extension = extension;
        this.unidadE = unidadE;
        this.cantTotal = cantTotal;
    }

    public RecetaMedicamento(String medicamentoCodigo, BigInteger recetaId) {
        this.recetaMedicamentoPK = new RecetaMedicamentoPK(medicamentoCodigo, recetaId);
    }

    public RecetaMedicamentoPK getRecetaMedicamentoPK() {
        return recetaMedicamentoPK;
    }

    public void setRecetaMedicamentoPK(RecetaMedicamentoPK recetaMedicamentoPK) {
        this.recetaMedicamentoPK = recetaMedicamentoPK;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadC() {
        return unidadC;
    }

    public void setUnidadC(String unidadC) {
        this.unidadC = unidadC;
    }

    public BigInteger getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(BigInteger periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getUnidadP() {
        return unidadP;
    }

    public void setUnidadP(String unidadP) {
        this.unidadP = unidadP;
    }

    public BigInteger getExtension() {
        return extension;
    }

    public void setExtension(BigInteger extension) {
        this.extension = extension;
    }

    public String getUnidadE() {
        return unidadE;
    }

    public void setUnidadE(String unidadE) {
        this.unidadE = unidadE;
    }

    public BigInteger getCantTotal() {
        return cantTotal;
    }

    public void setCantTotal(BigInteger cantTotal) {
        this.cantTotal = cantTotal;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recetaMedicamentoPK != null ? recetaMedicamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaMedicamento)) {
            return false;
        }
        RecetaMedicamento other = (RecetaMedicamento) object;
        if ((this.recetaMedicamentoPK == null && other.recetaMedicamentoPK != null) || (this.recetaMedicamentoPK != null && !this.recetaMedicamentoPK.equals(other.recetaMedicamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.RecetaMedicamento[ recetaMedicamentoPK=" + recetaMedicamentoPK + " ]";
    }
    
}
