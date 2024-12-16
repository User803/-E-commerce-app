package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();
    Admin getAdminById(Long id);
    void updateAdmin(Admin admin);
    void createAdmin(Admin admin);
    void deleteAdmin(Long id);
    boolean verifyCredentials(String email, String password);
}
