package com.controle.tarefas.controller;

import com.controle.tarefas.model.Tarefa;
import com.controle.tarefas.repository.TarefaRepository;
import com.controle.tarefas.service.TarefaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Permite requisições do frontend
@AllArgsConstructor
@RequestMapping("/api/tarefas")
public class TarefasController {

    private final TarefaRepository tarefasRepository;

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/paginado")
    public Page<Tarefa> buscarTarefasPaginadas(@RequestParam String nome,
                                      @RequestParam(defaultValue = "0") int pagina,
                                      @RequestParam(defaultValue = "10") int tamanho) {
        return tarefaService.buscarTarefasPaginadas(nome, pagina, tamanho);
    }


    @GetMapping("/{id}")
    public Tarefa buscarTarefaPorId(@PathVariable Long id) {
        return tarefaService.buscarTarefaPorId(id);
    }

    @PutMapping("/{id}")
    public Tarefa editarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.editarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
    }

    @PutMapping("/{id}/concluir")
    public Tarefa concluirTarefa(@PathVariable Long id) {
        return tarefaService.concluirTarefa(id);
    }

    @PutMapping("/{id}/pendente")
    public Tarefa pendenteTarefa(@PathVariable Long id) {
        return tarefaService.pendenteTarefa(id);
    }


}
