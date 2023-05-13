package com.turkcell.commonpackage.utils.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{
    private final ModelMapper modelMapper;

    @Override
    public ModelMapper forResponse() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return modelMapper;
    }
}
