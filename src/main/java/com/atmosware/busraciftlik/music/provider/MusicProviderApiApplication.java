package com.atmosware.busraciftlik.music.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MusicProviderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicProviderApiApplication.class, args);
    }

}
