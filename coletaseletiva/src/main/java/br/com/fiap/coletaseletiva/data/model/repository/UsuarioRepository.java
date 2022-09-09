package br.com.fiap.coletaseletiva.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.coletaseletiva.data.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM usuario u WHERE u.DS_EMAIL =:username")
	Usuario findByUserName(@Param("username") String username);

}
