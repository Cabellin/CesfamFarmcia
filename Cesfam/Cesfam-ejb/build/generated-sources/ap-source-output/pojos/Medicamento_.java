package pojos;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.AccionFarm;
import pojos.Laboratorio;
import pojos.MedicamentoCompuesto;
import pojos.MedicamentoPartida;
import pojos.NomGenerico;
import pojos.Presentacion;
import pojos.RecetaMedicamento;
import pojos.RegistroMerma;
import pojos.ViaAdministracion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-10T00:30:16")
@StaticMetamodel(Medicamento.class)
public class Medicamento_ { 

    public static volatile ListAttribute<Medicamento, MedicamentoCompuesto> medicamentoCompuestoList;
    public static volatile SingularAttribute<Medicamento, String> nomComercial;
    public static volatile ListAttribute<Medicamento, RegistroMerma> registroMermaList;
    public static volatile SingularAttribute<Medicamento, Laboratorio> laboratorioId;
    public static volatile SingularAttribute<Medicamento, NomGenerico> nomGenericoId;
    public static volatile SingularAttribute<Medicamento, BigInteger> contenido;
    public static volatile SingularAttribute<Medicamento, BigInteger> stockFisico;
    public static volatile SingularAttribute<Medicamento, Presentacion> presentacionId;
    public static volatile SingularAttribute<Medicamento, BigInteger> stockDisponible;
    public static volatile SingularAttribute<Medicamento, String> unidadCont;
    public static volatile ListAttribute<Medicamento, RecetaMedicamento> recetaMedicamentoList;
    public static volatile ListAttribute<Medicamento, AccionFarm> accionFarmList;
    public static volatile SingularAttribute<Medicamento, String> codigo;
    public static volatile SingularAttribute<Medicamento, BigInteger> uPorCaja;
    public static volatile SingularAttribute<Medicamento, ViaAdministracion> viaAdministracionId;
    public static volatile ListAttribute<Medicamento, MedicamentoPartida> medicamentoPartidaList;

}