package com.example.demo.pizzeria.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
	
}
