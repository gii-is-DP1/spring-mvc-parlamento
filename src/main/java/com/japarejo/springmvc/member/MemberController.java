package com.japarejo.springmvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.japarejo.springmvc.board.BoardService;

@Controller
@RequestMapping("/members")
public class MemberController<ParalamentarioSevice> {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping
	public ModelAndView showMembers(){
		Iterable<Member> parlamentarios=memberService.findAll();
		ModelAndView result=new ModelAndView("ListadoParlamentarios");
		result.addObject("parlamentarios", parlamentarios);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearteMember(){		
		ModelAndView result=new ModelAndView("EditarParlamentario");	
		result.addObject("parlamentario", new Member());
		result.addObject("todosOrganos", boardService.findAll());
		return result;
	}

	@PostMapping(path="/create")
	public ModelAndView saveNewMember(@ModelAttribute("parlamentario")  Member member) {
		memberService.save(member);
		ModelAndView result=showMembers();	
		result.addObject("mensaje", "Parlamentario creado con éxito");
		result.addObject("tipomensaje", "sucess");
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarParlamentario(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("EditarParlamentario");	
		result.addObject("parlamentario", memberService.findById(id));
		result.addObject("todosOrganos", boardService.findAll());
		return result;
	}
	
	@PostMapping(path="/edit/{id}")
	public ModelAndView grabarParlamentario(@ModelAttribute("member")  Member member, @PathVariable("id") long id) {
		memberService.save(member);
		ModelAndView result=showMembers();	
		result.addObject("mensaje", "Member sucessfully updated");
		result.addObject("tipomensaje", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarParlamentario(@PathVariable("id") long id){
		memberService.deleteById(id);
		ModelAndView result=showMembers();	
		result.addObject("mensaje", "Parlamentario borrado con éxito");
		result.addObject("tipomensaje", "sucess");
		return result;
	}
}
