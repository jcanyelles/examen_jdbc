package es.cc.liceu.db.examen.dao.impl;

import es.cc.liceu.db.examen.dao.UserDao;
import es.cc.liceu.db.examen.domain.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserta(User user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                    "insert into user (firstName, lastName, email) values (?,?,?) ",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getEmail());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()){
                user.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    new RuntimeException(throwables.getMessage());
                }
            }
            if (ps!=null ) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    new RuntimeException(throwables.getMessage());
                }
            }

        }


    }

    @Override
    public void modifica(User user) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update user set firstName=?, lastName=?, email=? where id = ? ");
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getEmail());
            ps.setInt(4,user.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps!=null ) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    new RuntimeException(throwables.getMessage());
                }
            }
        }
    }
}
