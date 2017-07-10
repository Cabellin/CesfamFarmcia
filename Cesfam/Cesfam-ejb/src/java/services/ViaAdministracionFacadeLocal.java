/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.ViaAdministracion;

/**
 *
 * @author Sebastian
 */
@Local
public interface ViaAdministracionFacadeLocal {

    void create(ViaAdministracion viaAdministracion);

    void edit(ViaAdministracion viaAdministracion);

    void remove(ViaAdministracion viaAdministracion);

    ViaAdministracion find(Object id);

    List<ViaAdministracion> findAll();

    List<ViaAdministracion> findRange(int[] range);

    int count();
    
}
