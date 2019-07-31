package com.wip.sample;

 


import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

	@Autowired
	DataValidation validator;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult,Model model) {
    	   
    	   validator.validate(personForm, bindingResult);
    	   
        if (bindingResult.hasErrors()) {
            return "form";
        }
        
        long diffInMillies = Math.abs(personForm.getStartDate().getTime() - personForm .getEndDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        personForm.setDateDiff(String.valueOf(diff));
       model.addAttribute("personForm");
        return "results";
    }
}