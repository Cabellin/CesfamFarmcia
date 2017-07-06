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
public class RecetaMedicamentoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MEDICAMENTO_CODIGO")
    private String medicamentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECETA_ID")
    private BigInteger recetaId;

    public RecetaMedicamentoPK() {
    }

    public RecetaMedicamentoPK(String medicamentoCodigo, BigInteger recetaId) {
        this.medicamentoCodigo = medicamentoCodigo;
        this.recetaId = recetaId;
    }

    public String getMedicamentoCodigo() {
        return medicamentoCodigo;
    }

    public void setMedicamentoCodigo(String medicamentoCodigo) {
        this.medicamentoCodigo = medicamentoCodigo;
    }

    public BigInteger getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(BigInteger recetaId) {
        this.recetaId = recetaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentoCodigo != null ? medicamentoCodigo.hashCode() : 0);
        hash += (recetaId != null ? recetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaMedicamentoPK)) {
            return false;
        }
        RecetaMedicamentoPK other = (RecetaMedicamentoPK) object;
        if ((this.medicamentoCodigo == null && other.medicamentoCodigo != null) || (this.medicamentoCodigo != null && !this.medicamentoCodigo.equals(other.medicamentoCodigo))) {
            return false;
        }
        if ((this.recetaId == null && other.recetaId != null) || (this.recetaId != null && !this.recetaId.equals(other.recetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.RecetaMedicamentoPK[ medicamentoCodigo=" + medicamentoCodigo + ", recetaId=" + recetaId + " ]";
    }
    
}
