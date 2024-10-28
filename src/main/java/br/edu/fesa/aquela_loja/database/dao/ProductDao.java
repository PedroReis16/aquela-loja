package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IProductDao;
import br.edu.fesa.aquela_loja.models.entities.Product;

public class ProductDao extends BaseDao<Product> implements IProductDao {

    @Override
    public Task validateBeforeInsert(Product[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(Product[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
