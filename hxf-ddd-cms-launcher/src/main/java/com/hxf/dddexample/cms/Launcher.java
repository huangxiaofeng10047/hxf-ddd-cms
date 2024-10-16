package com.hxf.dddexample.cms;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGracefulResponse
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}