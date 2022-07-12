package lk.easycar.spring.service.impl;

import lk.easycar.spring.dto.AdminDTO;
import lk.easycar.spring.entity.Admin;
import lk.easycar.spring.repo.AdminRepo;
import lk.easycar.spring.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public AdminDTO checkAdminLogIn(String name, String password) {
        Admin admin = adminRepo.checkAdminLogIn(name, password);
        if (!(admin ==null)){
            return mapper.map(admin, AdminDTO.class);
        }else {
            return null;
        }
    }
}
