package br.edu.ifsp.ifgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.edu.ifsp.ifgram")
public class IfgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(IfgramApplication.class, args);
    }
}
