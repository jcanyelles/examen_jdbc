package es.cc.liceu.db.examen.dao;

import es.cc.liceu.db.examen.domain.Product;

import java.util.Collection;
import java.util.Date;

public interface ProductDao {

    Collection<Product> find(String name, String description, Date init, Date end);

    Product getById(Integer id);

}
