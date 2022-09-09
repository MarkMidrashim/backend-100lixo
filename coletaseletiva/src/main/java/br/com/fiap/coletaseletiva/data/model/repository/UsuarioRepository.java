package br.com.fiap.coletaseletiva.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.coletaseletiva.data.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String username);

	/*@Query("SELECT u FROM Usuario u WHERE u.DS_EMAIL =:email")
	Usuario findByUserName(@Param("email") String email);*/

}
