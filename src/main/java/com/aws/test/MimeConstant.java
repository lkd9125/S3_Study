package com.aws.test;

import lombok.Getter;

@Getter
public enum MimeConstant {
    JPG("image/jpeg"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    PDF("application/pdf");

    private final String val;
  
    private MimeConstant(String value) {
      this.val = value;
    }

}
