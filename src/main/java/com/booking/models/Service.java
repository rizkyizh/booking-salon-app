package com.booking.models;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Service {
    private String serviceId;
    private String serviceName;
    private double price;
}
