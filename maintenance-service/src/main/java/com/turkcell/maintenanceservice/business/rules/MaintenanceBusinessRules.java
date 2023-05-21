package com.turkcell.maintenanceservice.business.rules;

import com.turkcell.commonpackage.utils.constants.Messages;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import com.turkcell.maintenanceservice.api.clients.CarClient;
import com.turkcell.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;
    private final CarClient client;

    public void checkIfMaintenanceExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Maintenance.NotExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(UUID carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarNotExists);
        }
    }


    public void checkIfCarUnderMaintenance(UUID carId) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.CarExists);
        }
    }

    public void checkCarAvailabilityForMaintenance(UUID carId){
        var response = client.checkIfCarAvailable(carId);
        if((!response.isSuccess())){
            throw new BusinessException(response.getMessage());
        }
    }


//    public void checkCarAvailabilityForMaintenance(State state) {
//        if (state.equals(State.RENTED)) {
//            throw new BusinessException(Messages.Maintenance.CarIsRented);
//        }
//    }
}