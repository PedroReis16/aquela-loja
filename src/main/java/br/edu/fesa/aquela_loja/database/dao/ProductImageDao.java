package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IProductImageDao;
import br.edu.fesa.aquela_loja.models.entities.ProductImage;

public class ProductImageDao extends BaseDao<ProductImage> implements IProductImageDao {

    @Override
    public Task validateBeforeInsert(ProductImage[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(ProductImage[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
