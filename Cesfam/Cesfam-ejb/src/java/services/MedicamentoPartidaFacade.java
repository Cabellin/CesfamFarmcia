/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.MedicamentoPartida;

/**
 *
 * @author Sebastian
 */
@Stateless
public class MedicamentoPartidaFacade extends AbstractFacade<MedicamentoPartida> implements MedicamentoPartidaFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoPartidaFacade() {
        super(MedicamentoPartida.class);
    }
    
}
