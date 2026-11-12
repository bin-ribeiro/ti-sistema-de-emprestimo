package br.com.emprestimos.service;
import br.com.emprestimos.dto.*;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.exception.*;
import br.com.emprestimos.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;
@Service public class EmprestimoService {
    private final EmprestimoRepository repo;
    private final EquipamentoRepository eq;
    private final ColaboradorRepository col;
    public EmprestimoService(EmprestimoRepository r,EquipamentoRepository e,ColaboradorRepository c) {
        repo=r;
        eq=e;
        col=c;
    }
    private Equipamento equipamento(Long id) {
        return eq.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Equipamento não encontrado"));
    }
    private Colaborador colaborador(Long id) {
        return col.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Colaborador não encontrado"));
    }
    private Emprestimo buscar(Long id) {
        return repo.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Empréstimo não encontrado"));
    }
    @Transactional public Emprestimo criar(EmprestimoRequest r) {
        Equipamento e=equipamento(r.equipamentoId());
        if(!e.isDisponivel())throw new ApiException(HttpStatus.CONFLICT,"Equipamento indisponível");
        Emprestimo x=new Emprestimo();
        x.setEquipamento(e);
        x.setColaborador(colaborador(r.colaboradorId()));
        x.setDataEmprestimo(LocalDate.now());
        x.setDataPrevistaDevolucao(LocalDate.now().plusDays(14));
        e.setDisponivel(false);
        eq.save(e);
        return repo.save(x);
    }
    @Transactional public Emprestimo devolver(Long id) {
        Emprestimo x=buscar(id);
        if(x.getDataDevolucao()!=null)throw new ApiException(HttpStatus.CONFLICT,"Empréstimo já devolvido");
        x.setDataDevolucao(LocalDate.now());
        x.getEquipamento().setDisponivel(true);
        eq.save(x.getEquipamento());
        return repo.save(x);
    }
    public List<Emprestimo> listar(Boolean atrasados) {
        List<Emprestimo> a=repo.findAll();
        if(Boolean.TRUE.equals(atrasados))return a.stream().filter(x->x.getDataDevolucao()==null&&x.getDataPrevistaDevolucao().isBefore(LocalDate.now())).toList();
        return a;
    }
    public List<Emprestimo> doColaborador(Long id) {
        colaborador(id);
        return repo.findByColaboradorId(id);
    }
}
