package pojos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-07T03:55:54")
@StaticMetamodel(RegistroMerma.class)
public class RegistroMerma_ { 

    public static volatile SingularAttribute<RegistroMerma, String> descripcion;
    public static volatile SingularAttribute<RegistroMerma, Date> fecha;
    public static volatile SingularAttribute<RegistroMerma, String> motivo;
    public static volatile SingularAttribute<RegistroMerma, String> estado;
    public static volatile SingularAttribute<RegistroMerma, Date> hora;
    public static volatile SingularAttribute<RegistroMerma, Medicamento> medicamentoCodigo;
    public static volatile SingularAttribute<RegistroMerma, BigInteger> cantidad;
    public static volatile SingularAttribute<RegistroMerma, BigDecimal> idReg;

}