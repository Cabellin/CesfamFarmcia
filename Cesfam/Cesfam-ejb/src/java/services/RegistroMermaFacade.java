/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.RegistroMerma;

/**
 *
 * @author Sebastian
 */
@javax.ejb.Stateless
public class RegistroMermaFacade extends AbstractFacade<RegistroMerma> implements RegistroMermaFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroMermaFacade() {
        super(RegistroMerma.class);
    }
    
}
