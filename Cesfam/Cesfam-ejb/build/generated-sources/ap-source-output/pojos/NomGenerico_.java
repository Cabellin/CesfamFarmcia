package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-07T03:55:54")
@StaticMetamodel(NomGenerico.class)
public class NomGenerico_ { 

    public static volatile SingularAttribute<NomGenerico, String> descripcion;
    public static volatile ListAttribute<NomGenerico, Medicamento> medicamentoList;
    public static volatile SingularAttribute<NomGenerico, BigDecimal> id;

}