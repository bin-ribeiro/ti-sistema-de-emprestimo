package br.com.emprestimos.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity public class Emprestimo {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Equipamento equipamento;
    @ManyToOne(optional=false) private Colaborador colaborador;
    private LocalDate dataEmprestimo,dataPrevistaDevolucao,dataDevolucao;
    public Long getId() {
        return id;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(Equipamento v) {
        equipamento=v;
    }
    public Colaborador getColaborador() {
        return colaborador;
    }
    public void setColaborador(Colaborador v) {
        colaborador=v;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate v) {
        dataEmprestimo=v;
    }
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public void setDataPrevistaDevolucao(LocalDate v) {
        dataPrevistaDevolucao=v;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate v) {
        dataDevolucao=v;
    }
}
