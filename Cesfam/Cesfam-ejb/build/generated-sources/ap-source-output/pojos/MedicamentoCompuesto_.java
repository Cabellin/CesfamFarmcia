package pojos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Compuesto;
import pojos.Medicamento;
import pojos.MedicamentoCompuestoPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-06T00:56:56")
@StaticMetamodel(MedicamentoCompuesto.class)
public class MedicamentoCompuesto_ { 

    public static volatile SingularAttribute<MedicamentoCompuesto, String> descripcion;
    public static volatile SingularAttribute<MedicamentoCompuesto, Compuesto> compuesto;
    public static volatile SingularAttribute<MedicamentoCompuesto, String> unidad;
    public static volatile SingularAttribute<MedicamentoCompuesto, MedicamentoCompuestoPK> medicamentoCompuestoPK;
    public static volatile SingularAttribute<MedicamentoCompuesto, Medicamento> medicamento;
    public static volatile SingularAttribute<MedicamentoCompuesto, Double> cantidad;

}