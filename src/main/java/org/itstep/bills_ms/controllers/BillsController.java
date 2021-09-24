package org.itstep.bills_ms.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.itstep.bills_ms.models.DTOs.AddBillDto;
import org.itstep.bills_ms.models.entities.Bill;
import org.itstep.bills_ms.models.external.UserDto;
import org.itstep.bills_ms.services.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@Slf4j
@SecurityRequirement(name = "authorization")
public class BillsController {

    @Autowired
    private BillsService billsService;

    @PostMapping("api/v1/bills")
    public Bill addBill(@RequestBody AddBillDto newBill) {
        return billsService.addBill(newBill);
    }

    @GetMapping("api/v1/bills/all")
    public List<Bill> getAllBillsByUserId() {
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return billsService.getBillsByUserId(user.getId());
    }
}
