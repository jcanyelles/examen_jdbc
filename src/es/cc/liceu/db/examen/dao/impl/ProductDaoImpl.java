package es.cc.liceu.db.examen.dao.impl;

import es.cc.liceu.db.examen.dao.ProductDao;
import es.cc.liceu.db.examen.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ProductDaoImpl implements ProductDao {
    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Collection<Product> find(String name, String description, Date init, Date end) {
        return find(null,name,description,init, end);
    }



    @Override
    public Product getById(Integer id) {
        Collection<Product> llista = find(id, null, null, null,null);
        if (!llista.isEmpty()){
            return llista.iterator().next();
        }
        return null;
    }


    private Collection<Product> find(Integer id, String name, String description, Date init, Date end) {
        Collection<Product> resultat = new ArrayList<>();
        String sql = "select id, name, description, price, date_created, date_published from product where 1=1 ";
        int posicioId=0; int posicioName = 0; int posicioDesc = 0; int posicioInit =0; int posicioEnd = 0;
        int posicio = 0;
        if (id!=null){
            sql += " and id = ? ";
            posicio++;
            posicioId = posicio;
        }
        if (name!=null){
            sql += " and name like ( ? )";
            posicio++;
            posicioName = posicio;
        }
        if (description!=null){
            sql += " and description like ( ? )";
            posicio++;
            posicioDesc = posicio;
        }
        if (init!=null){
            sql += " and date_created >= ? ";
            posicio++;
            posicioInit = posicio;
        }
        if (end!=null){
            sql += " and date_created <= ? ";
            posicio++;
            posicioEnd = posicio;
        }
        System.out.println(sql);
        System.out.println(posicioId + " " + posicioName + " " + posicioDesc + " " + posicioInit + " "+ posicioEnd);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            if (posicioId!=0){
                ps.setInt(posicioId, id);
            }
            if (posicioName!=0){
                ps.setString(posicioName, "%" + name + "%");
            }
            if (posicioDesc!=0){
                ps.setString(posicioDesc, "%" + description + "%");
            }
            if (posicioInit!=0){
                ps.setDate(posicioInit, new java.sql.Date(init.getTime()));
            }
            if (posicioEnd!=0){
                ps.setDate(posicioEnd, new java.sql.Date(end.getTime()));
            }
            rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setId(rs.getInt("id"));
                product.setPrice(rs.getFloat("price"));
                product.setCreated(rs.getDate("date_created"));
                product.setPublished(rs.getDate("date_published"));
                resultat.add(product);
            }
            return resultat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
