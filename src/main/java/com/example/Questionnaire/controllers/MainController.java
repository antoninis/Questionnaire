package com.example.Questionnaire.controllers;

import com.example.Questionnaire.domain.Post;
import com.example.Questionnaire.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;

    String finish;

    @GetMapping("/")
    public String start() {
        if(!postRepository.existsById((long)1)) {
            Post post1 = new Post("Выберите любимый цвет?", "Красный", "Синий", "Зеленый");
            Post post2 = new Post("Выберите любимые предметы?", "Математика", "Физика", "Обществознание");
            postRepository.save(post1);
            postRepository.save(post2);
        }

        return "start";
    }
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/go")
    public String go(Model model, Principal principal) {
        String name =principal.getName();
        if (principal.getName()=="Admin") {
            return "redirect:/home";
        }
        else if (principal.getName()=="User") {
            return "redirect:/user";
        }
        else
            return "/";

    }


    @GetMapping("/user")
    public String main(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "user";
    }
    @PostMapping("/user")
    public String check (@RequestParam(defaultValue = " ", required = false) String result,
            Model model) {
        finish = result;
        model.addAttribute("thanks", "Спасибо, ваши результаты записаны!");
        return "user";
    }

    @GetMapping("/results")
    public String results(Model model) {

        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("finish", finish);
        return "results";
    }

}