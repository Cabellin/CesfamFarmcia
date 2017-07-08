package pojos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Receta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-08T01:08:14")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Date> fechaNaci;
    public static volatile SingularAttribute<Paciente, String> apellidoMat;
    public static volatile SingularAttribute<Paciente, String> direccion;
    public static volatile SingularAttribute<Paciente, String> estadoCivil;
    public static volatile ListAttribute<Paciente, Receta> recetaList;
    public static volatile SingularAttribute<Paciente, String> prevision;
    public static volatile SingularAttribute<Paciente, String> nombres;
    public static volatile SingularAttribute<Paciente, String> apellidoPat;
    public static volatile SingularAttribute<Paciente, BigDecimal> rut;
    public static volatile SingularAttribute<Paciente, Character> dv;
    public static volatile SingularAttribute<Paciente, String> correo2;
    public static volatile SingularAttribute<Paciente, String> correo;
    public static volatile SingularAttribute<Paciente, BigInteger> fono2;
    public static volatile SingularAttribute<Paciente, BigInteger> fono;
    public static volatile SingularAttribute<Paciente, Character> sexo;

}