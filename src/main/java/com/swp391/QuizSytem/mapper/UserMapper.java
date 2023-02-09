package com.swp391.QuizSytem.mapper;

import com.swp391.QuizSytem.entity.Lession;
import com.swp391.QuizSytem.entity.User;
import com.swp391.QuizSytem.model.dto.LessionDto;
import com.swp391.QuizSytem.model.dto.UserDto;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);

    List<LessionDto> mapLstLession(List<Lession> lessions);
}
