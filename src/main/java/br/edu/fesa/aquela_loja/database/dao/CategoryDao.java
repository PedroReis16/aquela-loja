package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.ICategoryDao;
import br.edu.fesa.aquela_loja.models.entities.Category;

public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

    @Override
    public Task validateBeforeInsert(Category[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Task validateBeforeUpdate(Category[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
