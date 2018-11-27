package com.learn.ssm.chapter4.mapper;

import com.learn.ssm.chapter4.pojo.TestFile;

public interface FileMapper {
    int insertFile(TestFile file);
    TestFile getFile(Long id);
}
