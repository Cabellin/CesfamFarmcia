package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.TipoFunc;
import pojos.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-08T01:08:14")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, BigDecimal> rut;
    public static volatile SingularAttribute<Funcionario, Character> dv;
    public static volatile SingularAttribute<Funcionario, TipoFunc> tipoFuncId;
    public static volatile SingularAttribute<Funcionario, String> apellidoMat;
    public static volatile SingularAttribute<Funcionario, Usuario> usuario;
    public static volatile SingularAttribute<Funcionario, String> nombres;
    public static volatile SingularAttribute<Funcionario, String> apellidoPat;

}