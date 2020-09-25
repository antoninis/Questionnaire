package com.example.Questionnaire.repository;

import com.example.Questionnaire.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
