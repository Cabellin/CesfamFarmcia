package pojos;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Paciente;
import pojos.RecetaMedicamento;
import pojos.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-19T14:38:42")
@StaticMetamodel(Receta.class)
public class Receta_ { 

    public static volatile SingularAttribute<Receta, Paciente> pacienteRut;
    public static volatile SingularAttribute<Receta, Date> fecha;
    public static volatile SingularAttribute<Receta, String> estado;
    public static volatile ListAttribute<Receta, RecetaMedicamento> recetaMedicamentoList;
    public static volatile SingularAttribute<Receta, Usuario> usuarioNomUsu;
    public static volatile SingularAttribute<Receta, Date> hora;
    public static volatile SingularAttribute<Receta, BigDecimal> id;

}