package com.turkcell.rentalservice.business.abstracts;

import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.responses.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.turkcell.rentalservice.business.dto.responses.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.responses.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(UUID id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);
    void delete(UUID id);
}
