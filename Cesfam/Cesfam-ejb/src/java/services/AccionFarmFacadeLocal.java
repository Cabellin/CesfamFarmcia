/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.AccionFarm;

/**
 *
 * @author Sebastian
 */
@Local
public interface AccionFarmFacadeLocal {

    void create(AccionFarm accionFarm);

    void edit(AccionFarm accionFarm);

    void remove(AccionFarm accionFarm);

    AccionFarm find(Object id);

    List<AccionFarm> findAll();

    List<AccionFarm> findRange(int[] range);

    int count();
    
}
