package pojos;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.MedicamentoPartida;
import pojos.Proveedor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T08:34:08")
@StaticMetamodel(Partida.class)
public class Partida_ { 

    public static volatile SingularAttribute<Partida, Proveedor> proveedorId;
    public static volatile SingularAttribute<Partida, Date> fechaEntrega;
    public static volatile SingularAttribute<Partida, BigDecimal> id;
    public static volatile ListAttribute<Partida, MedicamentoPartida> medicamentoPartidaList;

}