package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Insert("INSERT INTO FILES WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Insert("INSERT INTO FILES WHERE userId = #{userID")
    File getFileByUserId(Integer fileId);

    @Insert("INSERT INTO FILES(fileName) VALUES (#{fileName}")
    @Options (useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(String fileName, String contentType, String fileSize, Integer userId, Byte[] fileData);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void Delete(Integer fileId);



}
