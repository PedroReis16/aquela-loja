package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.ProductImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImageModel, Long> {
}
