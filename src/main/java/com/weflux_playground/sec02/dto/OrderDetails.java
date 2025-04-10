package com.weflux_playground.sec02.dto;

import java.time.Instant;
import java.util.UUID;

public record OrderDetails(UUID orderId,
        String CustomerName,
        String ProductName,
        Integer Amount,
        Instant OrderDate) {

}
