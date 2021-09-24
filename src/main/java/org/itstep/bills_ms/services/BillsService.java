package org.itstep.bills_ms.services;

import org.itstep.bills_ms.models.DTOs.AddBillDto;
import org.itstep.bills_ms.models.entities.Bill;

import java.util.List;

public interface BillsService {

    Bill addBill(AddBillDto newBill);

    Bill updateBill(Bill billToUpdate);

    Bill getBillById(Long id);

    List<Bill> getAllBills();

    List<Bill> getBillsByUserId(Long id);
}
