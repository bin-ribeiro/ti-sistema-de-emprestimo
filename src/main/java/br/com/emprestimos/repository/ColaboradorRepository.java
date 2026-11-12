package br.com.emprestimos.repository;
import br.com.emprestimos.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long> {
    boolean existsByEmail(String email);
}
