package com.iftm.restwithmongodbandredis.repository;

import com.iftm.restwithmongodbandredis.model.Imovel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImovelRepository extends MongoRepository<Imovel, String> {
}