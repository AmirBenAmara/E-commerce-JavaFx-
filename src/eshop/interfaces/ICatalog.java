/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Catalog;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface ICatalog extends IService<Catalog, Integer> {

    List<Catalog> findByRaisonSortie(String nameCatalog);

    List<Catalog> findByPeriode(Date dateDebut,Date dateDeFin);

    List<Catalog> findByValide();

}
