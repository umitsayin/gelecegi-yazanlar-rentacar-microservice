package com.turkcell.inventoryservice.configuration;

import com.turkcell.commonpackage.utils.mappers.ModelMapperManager;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper modelMapper){
        return new ModelMapperManager(modelMapper);
    }
}
