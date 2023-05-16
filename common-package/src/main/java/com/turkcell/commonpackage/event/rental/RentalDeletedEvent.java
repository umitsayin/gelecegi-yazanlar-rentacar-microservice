package com.turkcell.commonpackage.event.rental;

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
public class RentalDeletedEvent implements Event {
    private UUID carId;
}
