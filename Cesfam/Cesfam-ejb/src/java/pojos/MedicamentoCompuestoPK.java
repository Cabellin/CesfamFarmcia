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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sebastian
 */
@Embeddable
public class MedicamentoCompuestoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPUESTO_ID")
    private BigInteger compuestoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MEDICAMENTO_CODIGO")
    private String medicamentoCodigo;

    public MedicamentoCompuestoPK() {
    }

    public MedicamentoCompuestoPK(BigInteger compuestoId, String medicamentoCodigo) {
        this.compuestoId = compuestoId;
        this.medicamentoCodigo = medicamentoCodigo;
    }

    public BigInteger getCompuestoId() {
        return compuestoId;
    }

    public void setCompuestoId(BigInteger compuestoId) {
        this.compuestoId = compuestoId;
    }

    public String getMedicamentoCodigo() {
        return medicamentoCodigo;
    }

    public void setMedicamentoCodigo(String medicamentoCodigo) {
        this.medicamentoCodigo = medicamentoCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compuestoId != null ? compuestoId.hashCode() : 0);
        hash += (medicamentoCodigo != null ? medicamentoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoCompuestoPK)) {
            return false;
        }
        MedicamentoCompuestoPK other = (MedicamentoCompuestoPK) object;
        if ((this.compuestoId == null && other.compuestoId != null) || (this.compuestoId != null && !this.compuestoId.equals(other.compuestoId))) {
            return false;
        }
        if ((this.medicamentoCodigo == null && other.medicamentoCodigo != null) || (this.medicamentoCodigo != null && !this.medicamentoCodigo.equals(other.medicamentoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.MedicamentoCompuestoPK[ compuestoId=" + compuestoId + ", medicamentoCodigo=" + medicamentoCodigo + " ]";
    }
    
}
