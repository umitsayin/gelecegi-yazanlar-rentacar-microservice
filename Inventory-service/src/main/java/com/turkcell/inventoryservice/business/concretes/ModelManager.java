package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.ModelService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateModelRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateModelRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateModelResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetModelResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    // TODO: update methods
    @Override
    public List<GetAllModelsResponse> getAll() {
        return null;
    }

    @Override
    public GetModelResponse getById(int id) {
        return null;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        return null;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
