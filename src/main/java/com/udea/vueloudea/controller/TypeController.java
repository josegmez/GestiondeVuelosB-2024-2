package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Type;
import com.udea.vueloudea.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;
    @QueryMapping
    public List<Type> getAllFamilies() {
        return typeService.getAllTypes();
    }
}