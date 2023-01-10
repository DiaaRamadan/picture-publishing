package com.picture_publishing.mapper;

import com.picture_publishing.dto.UserDto;
import com.picture_publishing.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserDto userDto);
    UserDto map(User user);

}
