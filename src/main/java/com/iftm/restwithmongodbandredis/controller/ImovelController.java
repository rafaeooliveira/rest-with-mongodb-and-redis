package com.iftm.restwithmongodbandredis.controller;

import com.iftm.restwithmongodbandredis.model.Imovel;
import com.iftm.restwithmongodbandredis.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService imovelService;

    @Autowired
    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping
    public ResponseEntity<Imovel> criarImovel(@RequestBody Imovel imovel) {
        Imovel novoImovel = imovelService.criarImovel(imovel);
        return ResponseEntity.ok(novoImovel);
    }

    @GetMapping
    public ResponseEntity<List<Imovel>> listarTodos() {
        return ResponseEntity.ok(imovelService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscarPorId(@PathVariable String id) {
        Optional<Imovel> imovel = imovelService.buscarPorId(id);
        return imovel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualizarImovel(@PathVariable String id, @RequestBody Imovel imovel) {
        if (!imovelService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imovel.setId(id);
        Imovel imovelAtualizado = imovelService.atualizarImovel(imovel);
        return ResponseEntity.ok(imovelAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImovel(@PathVariable String id) {
        if (!imovelService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imovelService.deletarImovel(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        imovelService.deletarTodos();
        return ResponseEntity.ok().build();
    }
}