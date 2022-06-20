package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private final GroupRepository repository;

    public List<Group> getAllGroups(){
        return repository.findAll();
    }

    public Group getGroup(Long groupId) throws GroupNotFoundException{
        return repository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    public Group saveGroup(Group group){
        return repository.save(group);
    }

    public void deleteGroup(Long groupId){
        repository.deleteById(groupId);
    }

}
