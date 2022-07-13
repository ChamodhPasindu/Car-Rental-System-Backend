package lk.easycar.spring.service;

import lk.easycar.spring.dto.AdminDTO;

public interface AdminService {

    AdminDTO checkAdminLogIn(String id, String password);
}
