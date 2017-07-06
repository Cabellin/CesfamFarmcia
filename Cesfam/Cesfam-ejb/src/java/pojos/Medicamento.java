/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * @author Sebastian
 */
@Entity
@Table(name = "MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m"),
    @NamedQuery(name = "Medicamento.findByCodigo", query = "SELECT m FROM Medicamento m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Medicamento.findByNomComercial", query = "SELECT m FROM Medicamento m WHERE m.nomComercial = :nomComercial"),
    @NamedQuery(name = "Medicamento.findByContenido", query = "SELECT m FROM Medicamento m WHERE m.contenido = :contenido"),
    @NamedQuery(name = "Medicamento.findByUnidadCont", query = "SELECT m FROM Medicamento m WHERE m.unidadCont = :unidadCont"),
    @NamedQuery(name = "Medicamento.findByUPorCaja", query = "SELECT m FROM Medicamento m WHERE m.uPorCaja = :uPorCaja"),
    @NamedQuery(name = "Medicamento.findByStockDisponible", query = "SELECT m FROM Medicamento m WHERE m.stockDisponible = :stockDisponible"),
    @NamedQuery(name = "Medicamento.findByStockFisico", query = "SELECT m FROM Medicamento m WHERE m.stockFisico = :stockFisico")})
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM_COMERCIAL")
    private String nomComercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTENIDO")
    private BigInteger contenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UNIDAD_CONT")
    private String unidadCont;
    @Basic(optional = false)
    @NotNull
    @Column(name = "U_POR_CAJA")
    private BigInteger uPorCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_DISPONIBLE")
    private BigInteger stockDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_FISICO")
    private BigInteger stockFisico;
    @ManyToMany(mappedBy = "medicamentoList")
    private List<AccionFarm> accionFarmList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamentoCodigo")
    private List<RegistroMerma> registroMermaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamento")
    private List<MedicamentoCompuesto> medicamentoCompuestoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamento")
    private List<MedicamentoPartida> medicamentoPartidaList;
    @JoinColumn(name = "LABORATORIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Laboratorio laboratorioId;
    @JoinColumn(name = "NOM_GENERICO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NomGenerico nomGenericoId;
    @JoinColumn(name = "PRESENTACION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Presentacion presentacionId;
    @JoinColumn(name = "VIA_ADMINISTRACION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ViaAdministracion viaAdministracionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamento")
    private List<RecetaMedicamento> recetaMedicamentoList;

    public Medicamento() {
    }

    public Medicamento(String codigo) {
        this.codigo = codigo;
    }

    public Medicamento(String codigo, String nomComercial, BigInteger contenido, String unidadCont, BigInteger uPorCaja, BigInteger stockDisponible, BigInteger stockFisico) {
        this.codigo = codigo;
        this.nomComercial = nomComercial;
        this.contenido = contenido;
        this.unidadCont = unidadCont;
        this.uPorCaja = uPorCaja;
        this.stockDisponible = stockDisponible;
        this.stockFisico = stockFisico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public BigInteger getContenido() {
        return contenido;
    }

    public void setContenido(BigInteger contenido) {
        this.contenido = contenido;
    }

    public String getUnidadCont() {
        return unidadCont;
    }

    public void setUnidadCont(String unidadCont) {
        this.unidadCont = unidadCont;
    }

    public BigInteger getUPorCaja() {
        return uPorCaja;
    }

    public void setUPorCaja(BigInteger uPorCaja) {
        this.uPorCaja = uPorCaja;
    }

    public BigInteger getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(BigInteger stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public BigInteger getStockFisico() {
        return stockFisico;
    }

    public void setStockFisico(BigInteger stockFisico) {
        this.stockFisico = stockFisico;
    }

    @XmlTransient
    public List<AccionFarm> getAccionFarmList() {
        return accionFarmList;
    }

    public void setAccionFarmList(List<AccionFarm> accionFarmList) {
        this.accionFarmList = accionFarmList;
    }

    @XmlTransient
    public List<RegistroMerma> getRegistroMermaList() {
        return registroMermaList;
    }

    public void setRegistroMermaList(List<RegistroMerma> registroMermaList) {
        this.registroMermaList = registroMermaList;
    }

    @XmlTransient
    public List<MedicamentoCompuesto> getMedicamentoCompuestoList() {
        return medicamentoCompuestoList;
    }

    public void setMedicamentoCompuestoList(List<MedicamentoCompuesto> medicamentoCompuestoList) {
        this.medicamentoCompuestoList = medicamentoCompuestoList;
    }

    @XmlTransient
    public List<MedicamentoPartida> getMedicamentoPartidaList() {
        return medicamentoPartidaList;
    }

    public void setMedicamentoPartidaList(List<MedicamentoPartida> medicamentoPartidaList) {
        this.medicamentoPartidaList = medicamentoPartidaList;
    }

    public Laboratorio getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(Laboratorio laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public NomGenerico getNomGenericoId() {
        return nomGenericoId;
    }

    public void setNomGenericoId(NomGenerico nomGenericoId) {
        this.nomGenericoId = nomGenericoId;
    }

    public Presentacion getPresentacionId() {
        return presentacionId;
    }

    public void setPresentacionId(Presentacion presentacionId) {
        this.presentacionId = presentacionId;
    }

    public ViaAdministracion getViaAdministracionId() {
        return viaAdministracionId;
    }

    public void setViaAdministracionId(ViaAdministracion viaAdministracionId) {
        this.viaAdministracionId = viaAdministracionId;
    }

    @XmlTransient
    public List<RecetaMedicamento> getRecetaMedicamentoList() {
        return recetaMedicamentoList;
    }

    public void setRecetaMedicamentoList(List<RecetaMedicamento> recetaMedicamentoList) {
        this.recetaMedicamentoList = recetaMedicamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Medicamento[ codigo=" + codigo + " ]";
    }
    
}
