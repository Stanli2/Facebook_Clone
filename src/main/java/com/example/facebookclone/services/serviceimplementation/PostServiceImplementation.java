package com.example.facebookclone.services.serviceimplementation;


import com.example.facebookclone.model.Comments;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.repository.PostRepository;
import com.example.facebookclone.services.PostServices;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

@org.springframework.stereotype.Service
public class PostServiceImplementation implements PostServices{

    private PostRepository postRepository;

    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Iterable<Post> getAllPost() {
        return postRepository.findAll();
    }



    public void viewHomePage(Model model){
        Post post = new Post();
        Comments comments = new Comments();
        Iterable<Post> posts = this.getAllPost();

        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("comment", comments);

    }
}
