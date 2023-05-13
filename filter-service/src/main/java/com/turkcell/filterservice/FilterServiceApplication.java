package com.turkcell.filterservice;

import com.turkcell.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Paths.CONFIGURATION_BASE_PACKAGE,
		Paths.FilterService.SERVICE_BASE_PACKAGE})
public class FilterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterServiceApplication.class, args);
	}

}
