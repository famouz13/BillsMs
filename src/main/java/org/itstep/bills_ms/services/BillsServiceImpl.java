package org.itstep.bills_ms.services;

import org.itstep.bills_ms.clients.UsersMsClient;
import org.itstep.bills_ms.models.DTOs.AddBillDto;
import org.itstep.bills_ms.models.entities.Bill;
import org.itstep.bills_ms.models.external.UserDto;
import org.itstep.bills_ms.repos.BillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillsServiceImpl implements BillsService {

    @Autowired
    private BillsRepository billsRepository;

    @Autowired
    private UsersMsClient usersMsClient;

    @Override
    public Bill addBill(AddBillDto newBill) {

        Bill bill = new Bill();
        bill.setAmount(newBill.getAmount());
        bill.setUserId(newBill.getUserId());
        bill.setDateOfIssue(new Date());
        bill.setIsPaid(false);

        UserDto userFromClient = usersMsClient.getUserById(newBill.getUserId());

        bill.setEmail(userFromClient.getEmail());

        return billsRepository.save(bill);
    }

    @Override
    public Bill updateBill(Bill billToUpdate) {
        return billsRepository.save(billToUpdate);
    }

    @Override
    public Bill getBillById(Long id) {
        return billsRepository.getById(id);
    }

    @Override
    public List<Bill> getAllBills() {
        return billsRepository.findAll();
    }

    @Override
    public List<Bill> getBillsByUserId(Long id) {
        return billsRepository.findBillByUserId(id);
    }
}
