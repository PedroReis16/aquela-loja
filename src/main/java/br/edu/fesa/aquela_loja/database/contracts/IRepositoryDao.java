package br.edu.fesa.aquela_loja.database.contracts;

import java.util.UUID;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fesa.aquela_loja.models.entities.BaseEntity;

@Repository
public interface IRepositoryDao<T extends BaseEntity> extends JpaRepository<T, UUID> {

}
