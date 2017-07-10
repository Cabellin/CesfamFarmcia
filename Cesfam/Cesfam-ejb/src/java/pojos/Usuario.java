/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNomUsu", query = "SELECT u FROM Usuario u WHERE u.nomUsu = :nomUsu"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOM_USU")
    private String nomUsu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @JoinColumn(name = "FUNCIONARIO_RUT", referencedColumnName = "RUT")
    @OneToOne(optional = false)
    private Funcionario funcionarioRut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioNomUsu")
    private List<Receta> recetaList;

    public Usuario() {
    }

    public Usuario(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public Usuario(String nomUsu, String contrasena) {
        this.nomUsu = nomUsu;
        this.contrasena = contrasena;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Funcionario getFuncionarioRut() {
        return funcionarioRut;
    }

    public void setFuncionarioRut(Funcionario funcionarioRut) {
        this.funcionarioRut = funcionarioRut;
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
        hash += (nomUsu != null ? nomUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nomUsu == null && other.nomUsu != null) || (this.nomUsu != null && !this.nomUsu.equals(other.nomUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Usuario[ nomUsu=" + nomUsu + " ]";
    }
    
}
