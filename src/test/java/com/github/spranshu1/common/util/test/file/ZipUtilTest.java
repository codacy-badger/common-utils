package com.github.spranshu1.common.util.test.file;


import com.github.spranshu1.common.util.file.ZipUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Zip util test.
 */
@DisabledOnOs({OS.LINUX,OS.MAC})
public class ZipUtilTest {

    private static final Path resourceDirectory = Paths.get("src","test","resources");

    private static final String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    private static final String SRC_FILE_PATH = absolutePath + "\\test_data\\dummy.txt";

    private static final String SRC_DIR_PATH = absolutePath + "\\test_data";

    private static final String DESTINATION = absolutePath + "\\test_data\\";

    /**
     * Cleanup.
     *
     * @throws IOException the io exception
     */
    @AfterEach
    public static void cleanup() throws IOException {
        Path file1 = Paths.get("src/test/resources/test_data/dummy.zip");
        Path file2 = Paths.get("src/test/resources/test_data.zip");
        Path file3 = Paths.get("src/test/resources/test_data/stream.zip");

        Files.delete(file1);
        Files.delete(file2);
        Files.delete(file3);
    }


    /**
     * Test file zip.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFileZip() throws IOException {
        boolean result = ZipUtil.zip(SRC_FILE_PATH,DESTINATION+"\\dummy.zip");
        Assertions.assertTrue(result);
    }

    /**
     * Test directory zip.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testDirZip() throws IOException {
        boolean result = ZipUtil.zip(SRC_DIR_PATH,absolutePath+"\\test_data.zip");
        Assertions.assertTrue(result);
    }

    /**
     * Test stream zip.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testStreamZip() throws IOException {
        List<InputStream> lis = new ArrayList<>();

        InputStream is = new FileInputStream(SRC_FILE_PATH);
        lis.add(is);

        ZipUtil.zipStream(lis,DESTINATION+"\\stream.zip");
    }

}