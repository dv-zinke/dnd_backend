package com.dnd.jachwirus.write.service;

import com.dnd.jachwirus.write.domain.Hashtag;
import com.dnd.jachwirus.write.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    @Autowired
    HashtagRepository hashtagRepository;

    public List<Hashtag> findAll(){
        return hashtagRepository.findAll();
    }

    public List<Hashtag> findAllByNameContain(String name){
        return hashtagRepository.findAllByNameContains(name);
    }
}
