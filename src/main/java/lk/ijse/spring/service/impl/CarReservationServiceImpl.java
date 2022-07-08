package lk.ijse.spring.service.impl;

import lk.ijse.spring.repo.CarReservationRepo;
import lk.ijse.spring.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;

@Service
@Transactional
public class CarReservationServiceImpl implements CarReservationService {
    @Autowired
    CarReservationRepo carReservationRepo;


    @Override
    public String generateReservationId() {
        String id = carReservationRepo.generateReservationId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "RID-000" + tempId;

            } else if (tempId <= 99) {
                return "RID-00" + tempId;

            } else if (tempId <= 999) {
                return "RID-0" + tempId;

            } else {
                return "RID-" + tempId;
            }
        } else {
            return "RID-001";
        }
    }
}
