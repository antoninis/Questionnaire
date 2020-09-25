package com.example.Questionnaire.controllers;

import com.example.Questionnaire.domain.Post;
import com.example.Questionnaire.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class QuesController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute("posts", postRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String addquestion(@RequestParam String question,
                              @RequestParam String answer1, @RequestParam String answer2, @RequestParam String answer3,
                              Model model){
        Post post = new Post(question,answer1,answer2,answer3);
        postRepository.save(post);
        model.addAttribute("posts", postRepository.findAll());

        return "admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "edit";
    }
    @PostMapping("/admin/edit")
    public String editor (@RequestParam(required = false) ArrayList<String> question,
                          @RequestParam(required = false) ArrayList<String> answer1,
                          @RequestParam(required = false) ArrayList<String> answer2,
                          @RequestParam(required = false) ArrayList<String> answer3,
                          Model model){
        long index = postRepository.count();
        System.out.println(index);
        for(int i=0;i<index;i++) {
            System.out.println("i="+i);
            for (int j=i;j<15;j++){
                System.out.println("j="+j);
                if (postRepository.existsById((long)j+1)) {
                    Post post = postRepository.findById((long) j + 1).orElseThrow(Error::new);
                    System.out.println(question.get(i));
                    post.setQuestion(question.get(i));
                    post.setAnswer1(answer1.get(i));
                    post.setAnswer2(answer2.get(i));
                    post.setAnswer3(answer3.get(i));
                    postRepository.save(post);
                    j=15;
                }
            }
        }
        model.addAttribute("posts", postRepository.findAll());

        return "redirect:/admin";
    }
    @PostMapping("/admin/delete")
    public String delete (@RequestParam Long number, Model model){
        if (number!=null && postRepository.existsById(number)) {
            Post post = postRepository.findById(number).orElseThrow(Error::new);
            postRepository.delete(post);
        }
//        Refresh();
        model.addAttribute("posts", postRepository.findAll());
        return "redirect:/admin";
    }


}
