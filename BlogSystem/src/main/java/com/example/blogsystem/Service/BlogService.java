package com.example.blogsystem.Service;


import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getMyBlogs(Integer user_id) {
        User user =authRepository.findUserById(user_id );
        return   blogRepository.findAllByUser(user);
    }


    public Blog getBlogByID(Integer id , Integer userid){
        User user =authRepository.findUserById(userid );
        return blogRepository.findBlogByIdAndUser(id,user);
    }

    public Blog getBlogBytitel(String titel,Integer userid){
        User user =authRepository.findUserById(userid );
        return blogRepository.findBlogByTitleAndUser(titel ,user);
    }




    public void addBlog(Integer user_id ,Blog blog) {
        User user =authRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }


    public void updateBlog(Integer id,Blog blog ,Integer user_id ) {
        User user =authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogByIdAndUser(id,user);
        if (blog1 == null) {
            throw new ApiException("Error,the Blog not found");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());

        blogRepository.save(blog1);

    }

    public void deleteBlog(Integer id) {
        Blog blog1 = blogRepository.findBlogById(id);
        if (blog1 == null) {
            throw new ApiException("Error,the Blog not found");
        }
        blogRepository.delete(blog1);
    }

}