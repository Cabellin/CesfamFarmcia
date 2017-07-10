/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.RecetaMedicamento;

/**
 *
 * @author Sebastian
 */
@Local
public interface RecetaMedicamentoFacadeLocal {

    void create(RecetaMedicamento recetaMedicamento);

    void edit(RecetaMedicamento recetaMedicamento);

    void remove(RecetaMedicamento recetaMedicamento);

    RecetaMedicamento find(Object id);

    List<RecetaMedicamento> findAll();

    List<RecetaMedicamento> findRange(int[] range);

    int count();
    
}
