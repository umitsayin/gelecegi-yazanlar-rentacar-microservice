package com.turkcell.commonpackage.event.maintenance;

import com.turkcell.commonpackage.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDeletedEvent implements Event {
    UUID carId;
}
