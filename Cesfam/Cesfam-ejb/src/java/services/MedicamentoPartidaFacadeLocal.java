/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import pojos.MedicamentoPartida;

/**
 *
 * @author Sebastian
 */
@javax.ejb.Local
public interface MedicamentoPartidaFacadeLocal {

    void create(MedicamentoPartida medicamentoPartida);

    void edit(MedicamentoPartida medicamentoPartida);

    void remove(MedicamentoPartida medicamentoPartida);

    MedicamentoPartida find(Object id);

    List<MedicamentoPartida> findAll();

    List<MedicamentoPartida> findRange(int[] range);

    int count();
    
}
