package br.com.emprestimos.dto;
import jakarta.validation.constraints.NotNull;
public record EmprestimoRequest(@NotNull Long equipamentoId,@NotNull Long colaboradorId) {
}
