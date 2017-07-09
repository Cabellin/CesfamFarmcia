package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-08T01:08:14")
@StaticMetamodel(AccionFarm.class)
public class AccionFarm_ { 

    public static volatile SingularAttribute<AccionFarm, String> descripcion;
    public static volatile ListAttribute<AccionFarm, Medicamento> medicamentoList;
    public static volatile SingularAttribute<AccionFarm, BigDecimal> id;

}