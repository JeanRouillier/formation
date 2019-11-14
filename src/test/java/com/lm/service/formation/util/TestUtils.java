package com.lm.service.formation.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class TestUtils {

  public static String getResourceAsString(String fileName) throws IOException {
    InputStream fileInputStream = getFileInputStream(fileName);
    return IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
  }

  public static InputStream getFileInputStream(String fileName) {
    return TestUtils.class.getClassLoader().getResourceAsStream(fileName);
  }
}
