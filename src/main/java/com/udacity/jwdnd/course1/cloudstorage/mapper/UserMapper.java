package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    AppUser getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}," +
            " #{password}, #{firstName}, #{lastName})")
    int insert(String username, String salt, String password, String firstName, String lastName);

    @Update("UPDATE USERS SET username=#{username}, firstname=#{firstName}, lastname=#{lastName}")
    int update(String username, String firstName, String lastName);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    int Delete(int userid);
}
