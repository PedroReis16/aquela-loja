package br.edu.fesa.aquela_loja.database.contracts;

import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entities.Product;

@Repository
public interface IProductDao extends IRepositoryDao<Product> {

}
