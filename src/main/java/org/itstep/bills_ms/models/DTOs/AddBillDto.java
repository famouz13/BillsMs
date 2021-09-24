package org.itstep.bills_ms.models.DTOs;

import lombok.Data;


@Data
public class AddBillDto {

    private Double amount;
    private Long userId;

}
