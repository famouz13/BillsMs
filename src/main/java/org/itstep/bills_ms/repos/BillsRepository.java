package org.itstep.bills_ms.repos;

import org.itstep.bills_ms.models.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillsRepository extends JpaRepository<Bill, Long> {

    List<Bill> findBillByUserId(Long id);

}
