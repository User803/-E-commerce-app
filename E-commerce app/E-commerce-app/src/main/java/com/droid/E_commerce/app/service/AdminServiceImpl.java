package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Admin;
import com.droid.E_commerce.app.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Admin with id %s not found".formatted(id))
                );
    }

    @Transactional
    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.findById(admin.getId())
                .orElseThrow(
                        () -> new RuntimeException("Admin with id %s not found".formatted(admin.getId()))
                );

        adminRepository.save(admin);
    }

    @Override
    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Transactional
    @Override
    public void deleteAdmin(Long id) {
        adminRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Admin with id %s not found", id))
                );

        adminRepository.deleteById(id);
    }

    @Override
    public boolean verifyCredentials(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);

        return Objects.equals(admin.getPassword(), password);
    }
}
