package br.edu.fesa.aquela_loja.database.contracts;

import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entities.Order;

@Repository
public interface IOrderDao extends IRepositoryDao<Order> {

}
