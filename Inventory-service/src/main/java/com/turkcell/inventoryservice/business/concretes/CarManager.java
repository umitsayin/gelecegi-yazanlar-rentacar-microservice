package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.CarService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    // TODO: update methods
    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        return null;
    }

    @Override
    public GetCarResponse getById(int id) {
        return null;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        return null;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
