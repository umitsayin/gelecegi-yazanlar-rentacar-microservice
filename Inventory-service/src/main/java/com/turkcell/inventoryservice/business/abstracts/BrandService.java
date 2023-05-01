package com.turkcell.inventoryservice.business.abstracts;

import com.turkcell.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(int id, UpdateBrandRequest request);
    void delete(int id);
}
