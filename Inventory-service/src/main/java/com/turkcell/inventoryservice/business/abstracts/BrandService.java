package com.turkcell.inventoryservice.business.abstracts;

import com.turkcell.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(UUID id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);
    void delete(UUID id);
}
