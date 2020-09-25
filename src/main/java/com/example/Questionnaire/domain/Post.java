package com.example.Questionnaire.domain;

import javax.persistence.*;

@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String question, answer1, answer2, answer3;

    public Post(){
    }

    public Post(String question, String answer1, String answer2, String answer3){
        this.question =question;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3=answer3;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

}
