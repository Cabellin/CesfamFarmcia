/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Presentacion;

/**
 *
 * @author Sebastian
 */
@Local
public interface PresentacionFacadeLocal {

    void create(Presentacion presentacion);

    void edit(Presentacion presentacion);

    void remove(Presentacion presentacion);

    Presentacion find(Object id);

    List<Presentacion> findAll();

    List<Presentacion> findRange(int[] range);

    int count();
    
}
