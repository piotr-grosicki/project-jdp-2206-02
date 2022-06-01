package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto(1L, new ArrayList<>(), "Group");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1L, new ArrayList<>(), "EditedGroup");
    }

    @DeleteMapping(value = "{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
    }
}
