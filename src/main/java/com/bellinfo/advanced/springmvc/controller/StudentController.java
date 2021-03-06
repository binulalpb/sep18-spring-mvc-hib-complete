package com.bellinfo.advanced.springmvc.controller;


import com.bellinfo.advanced.springmvc.model.Student;
import com.bellinfo.advanced.springmvc.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    UserRespository respository;

    @RequestMapping(value = "/student", method= RequestMethod.GET)
    public String getStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "login";
    }


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("message", "Hey Sorry, We have some issue. please come back");
            return "login";
        }

        String welcome = "Hey" + student.getName() +" Welcome to Spring MVC Learning";


        try {
            respository.save(student);
        } catch (Exception e) {
            model.addAttribute("message", "Hey Sorry, We have some issue. please come back");
            e.printStackTrace();
        }
        finally {
            model.addAttribute("message", welcome);
            return "success";
        }

    }

}
