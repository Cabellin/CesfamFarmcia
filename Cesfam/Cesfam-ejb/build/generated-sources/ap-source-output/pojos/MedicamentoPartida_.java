package pojos;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;
import pojos.MedicamentoPartidaPK;
import pojos.Partida;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-19T14:38:42")
@StaticMetamodel(MedicamentoPartida.class)
public class MedicamentoPartida_ { 

    public static volatile SingularAttribute<MedicamentoPartida, Date> fechaVencimiento;
    public static volatile SingularAttribute<MedicamentoPartida, Medicamento> medicamento;
    public static volatile SingularAttribute<MedicamentoPartida, BigInteger> cantidad;
    public static volatile SingularAttribute<MedicamentoPartida, MedicamentoPartidaPK> medicamentoPartidaPK;
    public static volatile SingularAttribute<MedicamentoPartida, Partida> partida;

}