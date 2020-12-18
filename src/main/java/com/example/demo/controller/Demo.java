package com.example.demo.controller;

import javax.persistence.Id;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;

@Controller
public class Demo {
	private StudentRepo sr;
	public Demo(StudentRepo sr) {
		this.sr = sr;
	}
	@RequestMapping("/")
	String home(Model m)
	{
		m.addAttribute("stud", sr.findAll());
		return "home";
	}
	@GetMapping("AddData")
	String add(Model m)
	{
		m.addAttribute("stud", new Student());
		return "AddData";
	}
	@PostMapping("data")
	String data(@ModelAttribute Student s)
	{
		sr.save(s);
		return "redirect:/";
	}
	
	@GetMapping("edit")
	String edit(Model m, @RequestParam("id") int id)
	{
		Student s=sr.findById(id).get();
		m.addAttribute("stud", s);
		return "AddData";
	}
	@GetMapping("delete")
	String delete(@RequestParam("id") int id)
	{
		sr.deleteById(id);
		return "redirect:/";
	}
}
