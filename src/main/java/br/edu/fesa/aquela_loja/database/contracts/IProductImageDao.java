package br.edu.fesa.aquela_loja.database.contracts;

import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entities.ProductImage;

@Repository
public interface IProductImageDao extends IRepositoryDao<ProductImage> {

}
