package com.controle.tarefas.service;

import com.controle.tarefas.model.Tarefa;
import com.controle.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Page<Tarefa> buscarTarefasPaginadas(String nome, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return tarefaRepository.findByNomeContaining(nome, pageable);
    }


    public Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow();
    }


    public Tarefa editarTarefa(Long id, Tarefa tarefa) {
        Tarefa tarefaExistente = buscarTarefaPorId(id);
        tarefaExistente.setNome(tarefa.getNome());
        tarefaExistente.setDescricao(tarefa.getDescricao());
        tarefaExistente.setPrioridade(tarefa.getPrioridade());
        tarefaExistente.setDataPrevistaConclusao(tarefa.getDataPrevistaConclusao());
        return tarefaRepository.save(tarefaExistente);
    }

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}


