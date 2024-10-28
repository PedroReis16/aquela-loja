package br.edu.fesa.aquela_loja.database.contracts;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.edu.fesa.aquela_loja.models.entities.BaseEntity;

@NoRepositoryBean
public interface IRepositoryDao<T extends BaseEntity> extends JpaRepository<T, UUID> {

}
