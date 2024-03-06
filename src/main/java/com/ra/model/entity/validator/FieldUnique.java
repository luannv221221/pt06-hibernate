package com.ra.model.entity.validator;

import com.ra.model.dao.CategoryDAO;
import com.ra.model.dao.CategoryDAOImpl;
import com.ra.model.service.CategoryService;
import com.ra.model.service.CategoryServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldUnique  implements ConstraintValidator<Unique,String> {

       private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(categoryService.checkNameExits(value));
        return true;
    }
}
