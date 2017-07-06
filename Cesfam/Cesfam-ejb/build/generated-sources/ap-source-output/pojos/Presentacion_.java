package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T08:34:08")
@StaticMetamodel(Presentacion.class)
public class Presentacion_ { 

    public static volatile SingularAttribute<Presentacion, String> descripcion;
    public static volatile ListAttribute<Presentacion, Medicamento> medicamentoList;
    public static volatile SingularAttribute<Presentacion, BigDecimal> id;

}