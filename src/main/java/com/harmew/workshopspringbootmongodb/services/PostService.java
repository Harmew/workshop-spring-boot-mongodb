package com.harmew.workshopspringbootmongodb.services;

import com.harmew.workshopspringbootmongodb.domain.Post;
import com.harmew.workshopspringbootmongodb.repositories.PostRepository;
import com.harmew.workshopspringbootmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = this.repository.findById(id);
        return post.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(text, minDate, maxDate);
    }

}
