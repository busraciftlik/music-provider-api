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

//    @Bean
//    public CommandLineRunner commandLineRunner(AuthenticationService service){
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .firstname("admin")
//                    .lastname("admin")
//                    .email("admin@mail.com")
//                    .password("123456")
//                    .role(Role.ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getToken());
//
//            var manager = RegisterRequest.builder()
//                    .firstname("manager")
//                    .lastname("manager")
//                    .email("manager@mail.com")
//                    .password("123456")
//                    .role(Role.MANAGER)
//                    .build();
//            System.out.println("Manager token: " + service.register(manager).getToken());
//        };
//    }

}
