package vader.lab.demo;

import javax.annotation.Resource;

import vader.lab.demo.property.FileStorageProperties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vader.lab.demo.service.FilesStorageService;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class DemoApplication implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//		storageService.deleteAll();
        storageService.init();
    }
}
