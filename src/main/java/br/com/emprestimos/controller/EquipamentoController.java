package br.com.emprestimos.controller;
import br.com.emprestimos.entity.*;
import br.com.emprestimos.enums.*;
import br.com.emprestimos.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/equipamentos") public class EquipamentoController {
    private final EquipamentoService s;
    public EquipamentoController(EquipamentoService s) {
        this.s=s;
    }
    @GetMapping public List<Equipamento> listar(@RequestParam(required=false)TipoEquipamento tipo,@RequestParam(required=false)Boolean disponivel) {
        return s.listar(tipo,disponivel);
    }
    @GetMapping("/{id}")public Equipamento buscar(@PathVariable Long id) {
        return s.buscar(id);
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Equipamento criar(@Valid @RequestBody Equipamento e) {
        return s.criar(e);
    }
    @PutMapping("/{id}")public Equipamento atualizar(@PathVariable Long id,@Valid @RequestBody Equipamento e) {
        return s.atualizar(id,e);
    }
    @DeleteMapping("/{id}")@ResponseStatus(HttpStatus.NO_CONTENT)public void remover(@PathVariable Long id) {
        s.remover(id);
    }
}
