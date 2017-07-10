package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-10T00:30:16")
@StaticMetamodel(Laboratorio.class)
public class Laboratorio_ { 

    public static volatile SingularAttribute<Laboratorio, BigDecimal> id;
    public static volatile SingularAttribute<Laboratorio, String> descripcion;
    public static volatile ListAttribute<Laboratorio, Medicamento> medicamentoList;

}