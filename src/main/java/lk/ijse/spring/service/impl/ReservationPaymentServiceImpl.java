package lk.ijse.spring.service.impl;

import lk.ijse.spring.repo.ReservationPaymentRepo;
import lk.ijse.spring.service.ReservationPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReservationPaymentServiceImpl implements ReservationPaymentService {

    @Autowired
    ReservationPaymentRepo reservationPaymentRepo;

    @Override
    public String generateReservationBillIdId() {
        String id = reservationPaymentRepo.generateReservationBillId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "BID-000" + tempId;

            } else if (tempId <= 99) {
                return "BID-00" + tempId;

            } else if (tempId <= 999) {
                return "BID-0" + tempId;

            } else {
                return "BID-" + tempId;
            }
        } else {
            return "BID-0001";
        }
    }
}