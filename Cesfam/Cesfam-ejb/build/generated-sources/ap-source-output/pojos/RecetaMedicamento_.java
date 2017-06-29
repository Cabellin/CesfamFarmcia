package pojos;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;
import pojos.Receta;
import pojos.RecetaMedicamentoPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T04:19:13")
@StaticMetamodel(RecetaMedicamento.class)
public class RecetaMedicamento_ { 

    public static volatile SingularAttribute<RecetaMedicamento, BigInteger> periodicidad;
    public static volatile SingularAttribute<RecetaMedicamento, BigInteger> extension;
    public static volatile SingularAttribute<RecetaMedicamento, Receta> receta;
    public static volatile SingularAttribute<RecetaMedicamento, Medicamento> medicamento;
    public static volatile SingularAttribute<RecetaMedicamento, String> unidadE;
    public static volatile SingularAttribute<RecetaMedicamento, RecetaMedicamentoPK> recetaMedicamentoPK;
    public static volatile SingularAttribute<RecetaMedicamento, BigInteger> cantTotal;
    public static volatile SingularAttribute<RecetaMedicamento, String> unidadP;

}