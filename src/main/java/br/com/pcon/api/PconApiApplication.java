package br.com.pcon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@EnableConfigurationProperties(PconApiApplication.class)
@SpringBootApplication
public class PconApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PconApiApplication.class, args);
	}
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
}
