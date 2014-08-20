package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.inject.Inject;

@Aspect
@Component
public class CategoryAspect {


    @Qualifier("mysql")
    private ProductRepository productRepo;

    @Inject
    public CategoryAspect(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

/*    @Before("execution(* com.sportsstore.web.controllers.ProductController.*(..))")
    public void logBeforePlay(JoinPoint joinPoint){
        System.out.println("--- :) thank you for playing ---(:");
    }*/

/*
    @Pointcut("execution(* com.sportsstore.web.controllers.ProductController.*(..)) && args(category, model)")
    public void addCat(String category, Model model){

    }

    @Before("addCat(category, model)")
    public void addCategories(String category, Model model){
        System.out.println("From aspect: " + category);
    }*/
}
