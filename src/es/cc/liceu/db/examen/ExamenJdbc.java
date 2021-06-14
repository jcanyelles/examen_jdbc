package es.cc.liceu.db.examen;

import es.cc.liceu.db.examen.dao.ProductDao;
import es.cc.liceu.db.examen.dao.UserDao;
import es.cc.liceu.db.examen.dao.impl.ProductDaoImpl;
import es.cc.liceu.db.examen.dao.impl.UserDaoImpl;
import es.cc.liceu.db.examen.domain.Product;
import es.cc.liceu.db.examen.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class ExamenJdbc {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://172.16.6.12:3306/examen_jdbc_model";
        Connection connection = DriverManager.getConnection(url,"root","root");

        UserDao userDao = new UserDaoImpl(connection);
        ProductDao productDao = new ProductDaoImpl(connection);

        User usuari = new User(null,"Pep","Guardiola","pepguardiona@gmail.com");
        userDao.inserta(usuari);
        userDao.inserta(new User(null,"Pep","Guardiola","pepguardiona@gmail.com"));
        userDao.inserta(new User(null,"Pep","Guardiola","pepguardiona@gmail.com"));

        usuari.setEmail("prova@gmail.com");
        userDao.modifica(usuari);

        Product producte = productDao.getById(1);
        System.out.println(producte);

        Collection<Product> productes = productDao.find(null,null,null,null);
        for (Product product : productes) {
            System.out.println(product);
        }
        connection.close();



    }
}
