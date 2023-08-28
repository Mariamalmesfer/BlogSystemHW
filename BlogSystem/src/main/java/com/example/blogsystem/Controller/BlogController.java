package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlog()
    {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyTodo(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));

    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity getAllBlogbyid(@AuthenticationPrincipal User user,@PathVariable Integer id)
    {
        return ResponseEntity.status(200).body(blogService.getBlogByID(id,user.getId()));
    }

    @GetMapping("/get-by-ti/{titel}")
    public ResponseEntity getAllBlogbytitel(@AuthenticationPrincipal User user,@PathVariable String titel)
    {
        return ResponseEntity.status(200).body(blogService.getBlogBytitel(titel ,user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("The Blog is added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user,@PathVariable Integer id ,@Valid @RequestBody Blog blog)
    {
        blogService.updateBlog(id, blog , user.getId());
        return ResponseEntity.status(200).body("The Blog is updated");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer id)
    {
        blogService.deleteBlog(id);
        return ResponseEntity.status(200).body("The Blog is deleted");
    }
}