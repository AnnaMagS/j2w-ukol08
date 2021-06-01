package cz.czechitas.java2webapps.ukol8.controller;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import cz.czechitas.java2webapps.ukol8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PostController {
    private final PostService serivce;

    @Autowired
    public PostController(PostService serivce) {
        this.serivce = serivce;
    }

    /*@GetMapping("/")
    public Object seznam() {
        return new ModelAndView("seznam")
                .addObject("zapisky", serivce.list());
        }
    }*/

    @GetMapping("/")
    public ModelAndView seznam(@PageableDefault() Pageable pageable) {
        return new ModelAndView("seznam")
                .addObject("zapisky", serivce.list(pageable));
    }

    @GetMapping("/{slug}")
    public Object detail(@PathVariable String slug) {
        Optional<Post> post = Optional.ofNullable(serivce.singlePost(slug));
        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ModelAndView("detail")
                .addObject("post", post.get());
    }
}
