/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Compuesto;

/**
 *
 * @author Sebastian
 */
@Local
public interface CompuestoFacadeLocal {

    void create(Compuesto compuesto);

    void edit(Compuesto compuesto);

    void remove(Compuesto compuesto);

    Compuesto find(Object id);

    List<Compuesto> findAll();

    List<Compuesto> findRange(int[] range);

    int count();
    
}
