/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "MEDICAMENTO_PARTIDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoPartida.findAll", query = "SELECT m FROM MedicamentoPartida m"),
    @NamedQuery(name = "MedicamentoPartida.findByPartidaId", query = "SELECT m FROM MedicamentoPartida m WHERE m.medicamentoPartidaPK.partidaId = :partidaId"),
    @NamedQuery(name = "MedicamentoPartida.findByMedicamentoCodigo", query = "SELECT m FROM MedicamentoPartida m WHERE m.medicamentoPartidaPK.medicamentoCodigo = :medicamentoCodigo"),
    @NamedQuery(name = "MedicamentoPartida.findByCantidad", query = "SELECT m FROM MedicamentoPartida m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MedicamentoPartida.findByFechaVencimiento", query = "SELECT m FROM MedicamentoPartida m WHERE m.fechaVencimiento = :fechaVencimiento")})
public class MedicamentoPartida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentoPartidaPK medicamentoPartidaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @JoinColumn(name = "MEDICAMENTO_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicamento medicamento;
    @JoinColumn(name = "PARTIDA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partida partida;

    public MedicamentoPartida() {
    }

    public MedicamentoPartida(MedicamentoPartidaPK medicamentoPartidaPK) {
        this.medicamentoPartidaPK = medicamentoPartidaPK;
    }

    public MedicamentoPartida(MedicamentoPartidaPK medicamentoPartidaPK, BigInteger cantidad, Date fechaVencimiento) {
        this.medicamentoPartidaPK = medicamentoPartidaPK;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public MedicamentoPartida(BigInteger partidaId, String medicamentoCodigo) {
        this.medicamentoPartidaPK = new MedicamentoPartidaPK(partidaId, medicamentoCodigo);
    }

    public MedicamentoPartidaPK getMedicamentoPartidaPK() {
        return medicamentoPartidaPK;
    }

    public void setMedicamentoPartidaPK(MedicamentoPartidaPK medicamentoPartidaPK) {
        this.medicamentoPartidaPK = medicamentoPartidaPK;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentoPartidaPK != null ? medicamentoPartidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoPartida)) {
            return false;
        }
        MedicamentoPartida other = (MedicamentoPartida) object;
        if ((this.medicamentoPartidaPK == null && other.medicamentoPartidaPK != null) || (this.medicamentoPartidaPK != null && !this.medicamentoPartidaPK.equals(other.medicamentoPartidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.MedicamentoPartida[ medicamentoPartidaPK=" + medicamentoPartidaPK + " ]";
    }
    
}
