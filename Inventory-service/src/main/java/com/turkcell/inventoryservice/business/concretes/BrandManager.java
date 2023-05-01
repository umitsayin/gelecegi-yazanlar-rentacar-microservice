package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.BrandService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    // TODO: update methods
    @Override
    public List<GetAllBrandsResponse> getAll() {
        return null;
    }

    @Override
    public GetBrandResponse getById(int id) {
        return null;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        return null;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
