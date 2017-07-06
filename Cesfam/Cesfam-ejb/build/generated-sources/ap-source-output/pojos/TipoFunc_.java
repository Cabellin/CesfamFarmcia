package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Funcionario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-05T20:46:43")
@StaticMetamodel(TipoFunc.class)
public class TipoFunc_ { 

    public static volatile SingularAttribute<TipoFunc, String> descripcion;
    public static volatile ListAttribute<TipoFunc, Funcionario> funcionarioList;
    public static volatile SingularAttribute<TipoFunc, BigDecimal> id;

}