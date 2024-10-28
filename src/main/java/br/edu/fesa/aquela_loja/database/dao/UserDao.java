package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IUserDao;
import br.edu.fesa.aquela_loja.models.entities.User;

public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public Task validateBeforeInsert(User[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(User[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
