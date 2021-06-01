package cz.czechitas.java2webapps.ukol8.service;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import cz.czechitas.java2webapps.ukol8.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Původní varianta metody
    /*public List<Post> list(){
        return postRepository.findAll();
    }*/

    public Page<Post> list(Pageable pageable) {
        pageable = PageRequest.of(0,20);
        //return postRepository.findAll(pageable);
        return postRepository.findByPublished(LocalDate.now(), pageable);
    }

    public Post singlePost(String slug){
        return postRepository.findBySlug(slug);
    }
}
