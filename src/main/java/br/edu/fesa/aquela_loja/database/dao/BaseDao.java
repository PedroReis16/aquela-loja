package br.edu.fesa.aquela_loja.database.dao;


import br.edu.fesa.aquela_loja.database.contracts.IRepositoryDao;
import br.edu.fesa.aquela_loja.models.entities.BaseEntity;
import jakarta.persistence.MappedSuperclass;


public abstract class BaseDao<T extends BaseEntity> implements IRepositoryDao<T> {


}
    
