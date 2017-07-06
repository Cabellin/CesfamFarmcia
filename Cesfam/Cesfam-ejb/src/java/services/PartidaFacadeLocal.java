/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Partida;

/**
 *
 * @author Sebastian
 */
@Local
public interface PartidaFacadeLocal {

    void create(Partida partida);

    void edit(Partida partida);

    void remove(Partida partida);

    Partida find(Object id);

    List<Partida> findAll();

    List<Partida> findRange(int[] range);

    int count();
    
}
