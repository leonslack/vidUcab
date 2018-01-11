package com.ucab.restful.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface CustomBaseJpaRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

  T findById(ID id);
  
  void delete(T entity);
  

}	
