/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.TipoFunc;

/**
 *
 * @author Sebastian
 */
@javax.ejb.Stateless
public class TipoFuncFacade extends AbstractFacade<TipoFunc> implements TipoFuncFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoFuncFacade() {
        super(TipoFunc.class);
    }
    
}
