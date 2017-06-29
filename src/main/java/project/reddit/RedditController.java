package project.reddit;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedditController {

	@Autowired
	RedditRepository repository;
	
	@RequestMapping("/")
	public String landingPage(){
		return "login";
	}
	
/*	@RequestMapping("/home")
	public String homePage(){
		//HomeModel.addAttribute("homeObject", new RedditUsers());
		return "homepage";
	}*/
	
	@RequestMapping("/add")
	public String addPost(Model differentModel){
		differentModel.addAttribute("homeObject", new RedditUsers());
		return "postings";
	}
	
	@RequestMapping("/addposting")
	public String submitPost(@Valid RedditUsers homeObject, BindingResult resultThatBinds, Model anotherModel){
		
		if(resultThatBinds.hasErrors()){
			return "errorpage";
		}
		
		repository.save(homeObject);
		anotherModel.addAttribute("homeObject", homeObject);
		return "results";
	}
	
	@RequestMapping("/somehthingelse")
	public String index(Principal p,Model model){
	String username = p.getName();
	model.addAttribute("principalname",username);
	model.addAttribute("principalObject",p);
	return "homepage";
	}
	
	
	@RequestMapping("/homepage")
	public String homepage(Principal p, Model someModel){
		someModel.addAttribute("principalObject", p);
		return "homepage";
	}
	/*
	@RequestMapping("/homepage")
	public void catchUserName(Principal p, Model m){
		String principalName = p.getName(); 
		//m.addAttribute("principalObject", p);
		System.out.println(principalName);
		
		return; //"homepage";
	}*/
	
	@RequestMapping("/allpostings")
	public String allPosts(Model allPostModel){
		Iterable<RedditUsers> usersIterate = repository.findAll();
		allPostModel.addAttribute("homeObject", repository.findAll());
		return "results";
	}
	
	
	/*@RequestMapping("/search")
	public String */
	
}
