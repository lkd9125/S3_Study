package com.aws.test.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
    private int idx;
    private String oriname;
    private String sysname;
    private String username;

    private MultipartFile awss3;
}
