package br.com.emprestimos.controller;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/colaboradores") public class ColaboradorController {
    private final ColaboradorService s;
    private final EmprestimoService es;
    public ColaboradorController(ColaboradorService s,EmprestimoService es) {
        this.s=s;
        this.es=es;
    }
    @GetMapping public List<Colaborador> listar() {
        return s.listar();
    }
    @GetMapping("/{id}")public Colaborador buscar(@PathVariable Long id) {
        return s.buscar(id);
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)public Colaborador criar(@Valid @RequestBody Colaborador c) {
        return s.criar(c);
    }
    @GetMapping("/{id}/emprestimos")public List<Emprestimo> emp(@PathVariable Long id) {
        return es.doColaborador(id);
    }
}
