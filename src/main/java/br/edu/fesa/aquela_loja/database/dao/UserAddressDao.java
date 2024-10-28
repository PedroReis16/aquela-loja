package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IUserAddressDao;
import br.edu.fesa.aquela_loja.models.entities.UserAddress;

public class UserAddressDao extends BaseDao<UserAddress> implements IUserAddressDao {

    @Override
    public Task validateBeforeInsert(UserAddress[] obj) {
        throw new UnsupportedOperationException("Unimplemented method 'validateBeforeInsert'");
    }

    @Override
    public Task validateBeforeUpdate(UserAddress[] obj) {
        throw new UnsupportedOperationException("Unimplemented method 'validateBeforeUpdate'");
    }

}
