/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import pojos.TipoFunc;

/**
 *
 * @author Sebastian
 */
@javax.ejb.Local
public interface TipoFuncFacadeLocal {

    void create(TipoFunc tipoFunc);

    void edit(TipoFunc tipoFunc);

    void remove(TipoFunc tipoFunc);

    TipoFunc find(Object id);

    List<TipoFunc> findAll();

    List<TipoFunc> findRange(int[] range);

    int count();
    
}
