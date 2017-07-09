package pojos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Funcionario;
import pojos.Receta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-08T01:08:14")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Funcionario> funcionarioRut;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile ListAttribute<Usuario, Receta> recetaList;
    public static volatile SingularAttribute<Usuario, String> nomUsu;

}