/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.RecetaMedicamento;

/**
 *
 * @author Sebastian
 */
@Stateless
public class RecetaMedicamentoFacade extends AbstractFacade<RecetaMedicamento> implements RecetaMedicamentoFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaMedicamentoFacade() {
        super(RecetaMedicamento.class);
    }
    
}
