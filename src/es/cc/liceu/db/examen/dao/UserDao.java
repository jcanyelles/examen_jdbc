package es.cc.liceu.db.examen.dao;

import es.cc.liceu.db.examen.domain.User;

public interface UserDao {

    void inserta (User user);

    void modifica (User user);
}
