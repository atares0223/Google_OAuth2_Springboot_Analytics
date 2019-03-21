package com.lff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ServletComponentScan
public class Application {
	public static void main(String[] args) {
		// this is for vpn port
		System.setProperty("https.proxyHost", "127.0.0.1");
		System.setProperty("https.proxyPort", "1080");
		SpringApplication.run(Application.class, args);
	}
}
