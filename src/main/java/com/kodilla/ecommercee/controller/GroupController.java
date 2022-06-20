package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupDbService service;
    private final GroupMapper groupMapper;


    @GetMapping
    public List<GroupDto> getGroups() {
        List<Group> groups = service.getAllGroups();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(service.getGroup(groupId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        service.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group savedGroup = service.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(savedGroup));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        service.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }
}
