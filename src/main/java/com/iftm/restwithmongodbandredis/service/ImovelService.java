package com.iftm.restwithmongodbandredis.service;

import com.iftm.restwithmongodbandredis.model.Imovel;
import com.iftm.restwithmongodbandredis.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {
    private final ImovelRepository imovelRepository;

    @Autowired
    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public Imovel criarImovel(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    @Cacheable(value = "imoveis")
    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }

    @Cacheable(value = "imovel", key = "#id")
    public Optional<Imovel> buscarPorId(String id) {
        return imovelRepository.findById(id);
    }

    @CachePut(value = "imovel", key = "#imovel.id")
    public Imovel atualizarImovel(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    @CacheEvict(value = "imovel", key = "#id")
    public void deletarImovel(String id) {
        imovelRepository.deleteById(id);
    }

    @CacheEvict(value = "imoveis", allEntries = true)
    public void deletarTodos() {
        imovelRepository.deleteAll();
    }
}