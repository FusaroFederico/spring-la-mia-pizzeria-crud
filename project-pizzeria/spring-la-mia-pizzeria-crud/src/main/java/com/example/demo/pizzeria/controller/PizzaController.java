package com.example.demo.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pizzeria.model.Pizza;
import com.example.demo.pizzeria.repo.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	// repository field con autowired per d.i.
	@Autowired
	private PizzaRepository repo;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "Menù");
		
		// prendo i dati da consegnare a /pizzas/index
		List<Pizza> pizzas = repo.findAll();
		// li inserisco nel model
		model.addAttribute("pizzas", pizzas);
		
		model.addAttribute("curPage", "pizzas");
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
