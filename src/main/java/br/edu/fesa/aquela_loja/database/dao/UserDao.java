package br.edu.fesa.aquela_loja.database.dao;

import br.edu.fesa.aquela_loja.database.contracts.IUserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private IUserDao userDao;
}
