package com.tarento.esearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarento.esearch.controller.OrderController;

//@RestController
@SpringBootApplication
//@ComponentScan(basePackageClasses=SampleController.class)
public class ESearchApplication {

	@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return "Nothing here. Go to <a href='/sample'>/sample</a>";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ESearchApplication.class, args);
	}
}
