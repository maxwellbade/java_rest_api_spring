package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController
{	
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
	
	//send data (requestbody to send raw data (postman>post>/alien>body>raw>json)
	@PostMapping("/alien")
	public Alien addAlien1(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid)
	{
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		
		//post to console
		System.out.println(repo.findByTech("java"));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByTechSorted("java"));
		
		mv.addObject(alien);
		return mv;
	}
	
	//list data in json
	@RequestMapping("/aliens")
	@ResponseBody
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}
	
	//add find by id from forum (forum within .jsp)
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien1(@PathVariable("aid")int aid)
	{
		return repo.findById(aid);
	}
	
	//delete id
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid)
	{
		//pass in the alien a object
		Alien a = repo.getOne(aid);
		
		repo.delete(a);
		
		//once obj deleted, can't return it
		return "deleted";
	}

	//update data (postman>/alien>put>paste in updated json>send>run sql select all
	@PutMapping("/alien")
	public Alien saveOrUpdateAlien1(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
}
