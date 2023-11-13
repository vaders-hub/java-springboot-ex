package vader.lab.demo.controller;

import org.springframework.core.io.Resource;
import vader.lab.demo.service.FilesStorageService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import vader.lab.demo.service.FilesStorageService;
import com.google.common.io.Files;

@Slf4j
@RestController
@RequestMapping("/static")
public class ImageController {
    @Autowired
    FilesStorageService storageService;

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[in.available()];
        int len;
        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

    @GetMapping("/get-text")
    public @ResponseBody String getText() {
        return "Hello world";
    }

    @GetMapping("/get-image")
    public @ResponseBody byte[] getImage() throws IOException {
        final InputStream in = getClass().getResourceAsStream("/static/image.jpg");
        log.warn("getImage :: 0 ");
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/get-image3")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile() throws Exception {
        try {
            String fileName = "test.png";
            Resource file = storageService.load(fileName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .header("Expires", "1")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(file);

        } catch (Exception e) {
            throw new Exception("Error downloading file" + e);
        }
    }

    @GetMapping(value = "/get-image-with-media-type", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        final InputStream in = getClass().getResourceAsStream("/static/test.png");
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/get-image-dynamic-type")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
        final MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
        final InputStream in = jpg ?
                getClass().getResourceAsStream("/static/image.jpg") :
                getClass().getResourceAsStream("/static/image.png");
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }

    @GetMapping(value = "/get-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getFile() throws IOException {
        final InputStream in = getClass().getResourceAsStream("/static/data.txt");
        return IOUtils.toByteArray(in);
    }

}