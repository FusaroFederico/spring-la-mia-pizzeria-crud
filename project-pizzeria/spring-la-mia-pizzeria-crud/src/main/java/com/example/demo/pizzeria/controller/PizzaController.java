package com.example.demo.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pizzeria.model.Pizza;
import com.example.demo.pizzeria.repo.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	// repository field con autowired per d.i.
	@Autowired
	private PizzaRepository repo;
	
	@GetMapping
	public String index(Model model, @RequestParam(name = "name", required = false) String name) {
		model.addAttribute("title", "Menù");
		
		List<Pizza> pizzas;
		
		if ( name!=null && !name.isEmpty()) {
			model.addAttribute("name", name);
			pizzas = repo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
			
		} else {
			pizzas = repo.findAll();
		}
		
		// li inserisco nel model
		model.addAttribute("pizzas", pizzas);
		
		return "/pizzas/index";
	}
	
	@GetMapping("/{id}")
	public String pizzaDetails(Model model, @PathVariable("id") Integer pizzaId) {
		model.addAttribute("title", "Dettagli Pizza");
		
		Optional<Pizza> pizzaOpt = repo.findById(pizzaId);
		Pizza pizza = null;
		if(pizzaOpt.isPresent()) {
			// Perform operations with the user object
		    pizza = pizzaOpt.get();
		}
		
		model.addAttribute("pizza", pizza);
		return "/pizzas/show";
	}
}
