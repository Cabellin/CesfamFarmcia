/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Laboratorio;

/**
 *
 * @author Sebastian
 */
@Local
public interface LaboratorioFacadeLocal {

    void create(Laboratorio laboratorio);

    void edit(Laboratorio laboratorio);

    void remove(Laboratorio laboratorio);

    Laboratorio find(Object id);

    List<Laboratorio> findAll();

    List<Laboratorio> findRange(int[] range);

    int count();
    
}
