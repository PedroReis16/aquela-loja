package br.edu.fesa.aquela_loja.database.dao;

import org.springframework.scheduling.config.Task;

import br.edu.fesa.aquela_loja.database.contracts.IRepositoryDao;
import br.edu.fesa.aquela_loja.models.entities.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> implements IRepositoryDao<T> {

    public abstract Task validateBeforeInsert(T[] obj);

    public abstract Task validateBeforeUpdate(T[] obj);
}
