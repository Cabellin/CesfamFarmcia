/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.RegistroMerma;

/**
 *
 * @author Sebastian
 */
@Local
public interface RegistroMermaFacadeLocal {

    void create(RegistroMerma registroMerma);

    void edit(RegistroMerma registroMerma);

    void remove(RegistroMerma registroMerma);

    RegistroMerma find(Object id);

    List<RegistroMerma> findAll();

    List<RegistroMerma> findRange(int[] range);

    int count();
    
}
