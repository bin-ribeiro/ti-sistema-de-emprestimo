package br.com.emprestimos.service;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.enums.*;
import br.com.emprestimos.exception.*;
import br.com.emprestimos.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class EquipamentoService {
    private final EquipamentoRepository repo;
    private final EmprestimoRepository er;
    public EquipamentoService(EquipamentoRepository r,EmprestimoRepository e) {
        repo=r;
        er=e;
    }
    public List<Equipamento> listar(TipoEquipamento t,Boolean d) {
        if(t!=null&&d!=null)return repo.findByTipoAndDisponivel(t,d);
        if(t!=null)return repo.findByTipo(t);
        if(d!=null)return repo.findByDisponivel(d);
        return repo.findAll();
    }
    public Equipamento buscar(Long id) {
        return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Equipamento não encontrado"));
    }
    public Equipamento criar(Equipamento e) {
        if(repo.existsByPatrimonio(e.getPatrimonio()))throw new ApiException(HttpStatus.CONFLICT,"Patrimônio já cadastrado");
        e.setDisponivel(true);
        return repo.save(e);
    }
    public Equipamento atualizar(Long id,Equipamento dados) {
        Equipamento e=buscar(id);
        e.setNome(dados.getNome());
        e.setTipo(dados.getTipo());
        return repo.save(e);
    }
    public void remover(Long id) {
        buscar(id);
        if(er.existsByEquipamentoId(id))throw new ApiException(HttpStatus.CONFLICT,"Equipamento tem empréstimos registrados");
        repo.deleteById(id);
    }
}
