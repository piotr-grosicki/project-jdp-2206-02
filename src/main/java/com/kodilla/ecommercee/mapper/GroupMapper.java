package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.entity.Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto){
        return Group.builder()
                .id(groupDto.getId())
                .name(groupDto.getName())
                .build();
    }

    public GroupDto mapToGroupDto(final Group group){
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList){
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

}