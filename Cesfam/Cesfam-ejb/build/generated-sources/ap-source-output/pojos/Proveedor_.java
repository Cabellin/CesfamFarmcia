package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Partida;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-08T01:08:14")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> descripcion;
    public static volatile ListAttribute<Proveedor, Partida> partidaList;
    public static volatile SingularAttribute<Proveedor, BigDecimal> id;

}