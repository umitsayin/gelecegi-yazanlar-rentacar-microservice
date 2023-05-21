package com.turkcell.maintenanceservice.business.concretes;

import com.turkcell.commonpackage.event.maintenance.MaintenanceCreatedEvent;
import com.turkcell.commonpackage.event.maintenance.MaintenanceDeletedEvent;
import com.turkcell.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.maintenanceservice.business.abstracts.MaintenanceService;
import com.turkcell.maintenanceservice.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcell.maintenanceservice.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcell.maintenanceservice.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcell.maintenanceservice.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcell.maintenanceservice.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcell.maintenanceservice.business.dto.responses.update.UpdateMaintenanceResponse;
import com.turkcell.maintenanceservice.business.rules.MaintenanceBusinessRules;
import com.turkcell.maintenanceservice.entities.Maintenance;
import com.turkcell.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final MaintenanceBusinessRules rules;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.forResponse().map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(UUID carId) {
        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        rules.checkIfCarIsNotUnderMaintenance(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);


        GetMaintenanceResponse response = mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }


    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkIfCarUnderMaintenance(request.getCarId());
        rules.checkCarAvailabilityForMaintenance(request.getCarId());
        Maintenance maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(null);
        maintenance.setCompleted(false);
        maintenance.setStartedDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
      // -  carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.forResponse().map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }



    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request) {
        rules.checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.forResponse().map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);

        repository.deleteById(id);
    }

    private void sendKafkaMaintenanceCreatedEvent(UUID carId){
        producer.sendMessage("maintenance-created-event",new MaintenanceCreatedEvent(carId));
    }

    private void sendKafkaMaintenanceDeletedEvent(UUID carId){
        producer.sendMessage("maintenance-deleted-event", new MaintenanceDeletedEvent(carId));
    }


    private void makeCarAvailableIfIsCompletedFalse(UUID id) {
//        int carId = repository.findById(id).get().getCar().getId();
//        if (repository.existsByCarIdAndIsCompletedIsFalse(carId)) {
//            carService.changeState(carId, State.AVAILABLE);
//        }
    }
}