package br.com.emprestimos.repository;
import br.com.emprestimos.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    boolean existsByEquipamentoId(Long id);
    List<Emprestimo> findByColaboradorId(Long id);
}
