package br.edu.fesa.aquela_loja.database.contracts;

import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entities.UserAddress;

@Repository
public interface IUserAddressDao extends IRepositoryDao<UserAddress> {

}
