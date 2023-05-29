package com.turkcell.invoiceservice;

import com.turkcell.commonpackage.utils.constants.Paths;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableDiscoveryClient
@ComponentScan(Paths.CONFIGURATION_BASE_PACKAGE)
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.turkcell.invoiceservice.repository")
public class InvoiceServiceApplication {
    private final InvoiceRepository repository;

    public InvoiceServiceApplication(InvoiceRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);
    }

    @PostConstruct
    public void add(){
        repository.save(new Invoice());
    }
}
