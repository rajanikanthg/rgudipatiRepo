package com.wip.sample;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class DataValidation implements Validator{
 
   
 
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.startDate", "Start Date is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.endDate", "End Date is required.");
        PersonForm personForm = (PersonForm) target;
   	  if (personForm .getStartDate()!=null && personForm .getEndDate()!=null) {
        if (personForm .getStartDate().after(personForm .getEndDate()))
        {
        errors.rejectValue("endDate", "error.endDate", new Object[]{"'endDate'"}, "End Date should be greater than start date");
          }
         
        	  }
    }
	@Override
	public boolean supports(Class<?> clazz) {
	
		return PersonForm.class.isAssignableFrom(clazz);
	}

}
