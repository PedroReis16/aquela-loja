package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IOrderDao;
import br.edu.fesa.aquela_loja.models.entities.Order;

public class OrderDao extends BaseDao<Order> implements IOrderDao {

    @Override
    public Task validateBeforeInsert(Order[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(Order[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
