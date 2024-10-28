package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IUserWalletDao;
import br.edu.fesa.aquela_loja.models.entities.UserWallet;

public class UserWalletDao extends BaseDao<UserWallet> implements IUserWalletDao {

    @Override
    public Task validateBeforeInsert(UserWallet[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(UserWallet[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
