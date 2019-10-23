package com.g33kzone.kinesis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.g33kzone.kinesis.model.Claims;

@Repository
public interface ClaimsRepository extends CrudRepository<Claims,Integer>{

	List<Claims> findAll();
}
