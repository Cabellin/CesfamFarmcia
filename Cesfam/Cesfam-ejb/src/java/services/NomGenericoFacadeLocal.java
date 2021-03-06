/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.NomGenerico;

/**
 *
 * @author Sebastian
 */
@Local
public interface NomGenericoFacadeLocal {

    void create(NomGenerico nomGenerico);

    void edit(NomGenerico nomGenerico);

    void remove(NomGenerico nomGenerico);

    NomGenerico find(Object id);

    List<NomGenerico> findAll();

    List<NomGenerico> findRange(int[] range);

    int count();
    
}
