package hh.swd20.Elokuvasovellus.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.swd20.Elokuvasovellus.domain.Elokuva;
import hh.swd20.Elokuvasovellus.domain.ElokuvaRepository;
import hh.swd20.Elokuvasovellus.domain.CategoryRepository;

@Controller
public class ElokuvaController {

	@Autowired
	private ElokuvaRepository elokuvaRepository; 
	
	@Autowired
	private CategoryRepository crepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

	
	@RequestMapping(value = "/movielist")
	public String elokuvaList(Model model) {
			model.addAttribute("elokuvat", elokuvaRepository.findAll()); 
			return "movielist"; 
	}
	
	 @RequestMapping(value="/movies")
	    public @ResponseBody List<Elokuva> elokuvaListRest() {	
	        return (List<Elokuva>) elokuvaRepository.findAll();
	    }    
	 
	 @RequestMapping(value="/elokuva/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Elokuva> findElokuvaRest(@PathVariable("id") Long elokuvaId) {	
	    	return elokuvaRepository.findById(elokuvaId);
	    }       
	

	@RequestMapping(value = "/addmovie")
	public String addElokuva(Model model) {
		model.addAttribute("elokuva", new Elokuva());
		model.addAttribute("categories", crepository.findAll());
		return "elokuvaform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveElokuva(Elokuva elokuva) {
		elokuvaRepository.save(elokuva);
		return "redirect:/movielist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletemovie/{id}", method = RequestMethod.GET)
	public String deleteElokuva(@PathVariable("id") Long elokuvaId, Model model) {
		elokuvaRepository.deleteById(elokuvaId);
		return "redirect:../movielist";
	}
	
	@RequestMapping(value = "/editmovie/{id}")
	public String editElokuva(@PathVariable("id") Long elokuvaId, Model model) {
		model.addAttribute("elokuva", elokuvaRepository.findById(elokuvaId));
		model.addAttribute("categories", crepository.findAll());
		return "editmovie";
	}
	
}