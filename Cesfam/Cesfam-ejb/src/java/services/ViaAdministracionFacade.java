/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.ViaAdministracion;

/**
 *
 * @author Sebastian
 */
@javax.ejb.Stateless
public class ViaAdministracionFacade extends AbstractFacade<ViaAdministracion> implements ViaAdministracionFacadeLocal {

    @PersistenceContext(unitName = "Cesfam-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViaAdministracionFacade() {
        super(ViaAdministracion.class);
    }
    
}
