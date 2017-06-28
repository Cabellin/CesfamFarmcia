/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.MedicamentoCompuesto;

/**
 *
 * @author Pelao
 */
@Stateless
public class MedicamentoCompuestoFacade extends AbstractFacade<MedicamentoCompuesto> implements MedicamentoCompuestoFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoCompuestoFacade() {
        super(MedicamentoCompuesto.class);
    }
    
}
