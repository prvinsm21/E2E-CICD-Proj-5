package com.gaming.gamingsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class GamingsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingsiteApplication.class, args);
	}

	@GetMapping("/")
    public String home() {
        return "index.html";
    }

}
