package br.edu.fesa.aquela_loja.database.dao;

import br.edu.fesa.aquela_loja.database.contracts.IRepositoryDao;
import br.edu.fesa.aquela_loja.models.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> implements IRepositoryDao<T> {

}
