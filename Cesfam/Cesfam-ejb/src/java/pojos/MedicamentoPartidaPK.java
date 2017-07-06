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
public class MedicamentoPartidaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PARTIDA_ID")
    private BigInteger partidaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MEDICAMENTO_CODIGO")
    private String medicamentoCodigo;

    public MedicamentoPartidaPK() {
    }

    public MedicamentoPartidaPK(BigInteger partidaId, String medicamentoCodigo) {
        this.partidaId = partidaId;
        this.medicamentoCodigo = medicamentoCodigo;
    }

    public BigInteger getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(BigInteger partidaId) {
        this.partidaId = partidaId;
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
        hash += (partidaId != null ? partidaId.hashCode() : 0);
        hash += (medicamentoCodigo != null ? medicamentoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoPartidaPK)) {
            return false;
        }
        MedicamentoPartidaPK other = (MedicamentoPartidaPK) object;
        if ((this.partidaId == null && other.partidaId != null) || (this.partidaId != null && !this.partidaId.equals(other.partidaId))) {
            return false;
        }
        if ((this.medicamentoCodigo == null && other.medicamentoCodigo != null) || (this.medicamentoCodigo != null && !this.medicamentoCodigo.equals(other.medicamentoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.MedicamentoPartidaPK[ partidaId=" + partidaId + ", medicamentoCodigo=" + medicamentoCodigo + " ]";
    }
    
}
