/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.MedicamentoCompuesto;

/**
 *
 * @author Sebastian
 */
@Local
public interface MedicamentoCompuestoFacadeLocal {

    void create(MedicamentoCompuesto medicamentoCompuesto);

    void edit(MedicamentoCompuesto medicamentoCompuesto);

    void remove(MedicamentoCompuesto medicamentoCompuesto);

    MedicamentoCompuesto find(Object id);

    List<MedicamentoCompuesto> findAll();

    List<MedicamentoCompuesto> findRange(int[] range);

    int count();
    
}
