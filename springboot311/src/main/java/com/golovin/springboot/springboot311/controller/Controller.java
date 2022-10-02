package com.golovin.springboot.springboot311.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;

@org.springframework.stereotype.Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {
    @GetMapping(value = {"/admin","/user/{id}"})
    public String index(@PathVariable(name = "id",required = false) Integer id){
        return "admin-panel";
    }

}
