package com.turkcell.inventoryservice.business.abstracts;



import com.turkcell.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateCarResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    void delete(int id);
}
