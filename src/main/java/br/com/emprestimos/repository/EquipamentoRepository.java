package br.com.emprestimos.repository;
import br.com.emprestimos.entity.Equipamento;
import br.com.emprestimos.enums.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface EquipamentoRepository extends JpaRepository<Equipamento,Long> {
    boolean existsByPatrimonio(String p);
    List<Equipamento> findByTipo(TipoEquipamento t);
    List<Equipamento> findByDisponivel(boolean d);
    List<Equipamento> findByTipoAndDisponivel(TipoEquipamento t,boolean d);
}
