package br.com.fiap.coletaseletiva.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.coletaseletiva.data.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}
