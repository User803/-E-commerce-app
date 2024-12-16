package com.droid.E_commerce.app.controller;

import com.droid.E_commerce.app.entity.Admin;
import com.droid.E_commerce.app.entity.Order;
import com.droid.E_commerce.app.entity.Product;
import com.droid.E_commerce.app.entity.User;
import com.droid.E_commerce.app.service.AdminService;
import com.droid.E_commerce.app.service.OrderService;
import com.droid.E_commerce.app.service.ProductService;
import com.droid.E_commerce.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private User user;

    public AdminController(AdminService adminService, UserService userService, OrderService orderService, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/verify/credentials")
    public String verifyCredentials(@ModelAttribute("admin") Admin admin, Model model) {
        if (adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
            return "";
        }

        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    @GetMapping("/admin/home")
    public String adminHomePage(Model model) {
        model.addAttribute("adminList", adminService.getAllAdmin());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("orderList", orderService.getAllOrders());
        model.addAttribute("productList", productService.getAllProducts());

        return "AdminHomePage";
    }

    @GetMapping("/add/admin")
    public String createAdmin() {
        return "AddAdmin";
    }

    @PostMapping("/add/admin")
    public String createAdmin(Admin admin) {
        adminService.createAdmin(admin);

        return "/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String updateAdmin(@PathVariable Long id, Model model) {
        model.addAttribute("admin", adminService.getAdminById(id));

        return "UpdateUser";  // Used same page as User to avoid duplication as fields as similar
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);

        return "/admin/home";
    }

    @DeleteMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);

        return "/admin/home";
    }

    @GetMapping("/user/login")
    public String userLogin(User user, Model model) {
        if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
            user = userService.findUserByEmail(user.getEmail());
            model.addAttribute("ordersList", orderService.findOrderByUser(user));

            return "ProductPage";
        }

        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    public String productSearch(String name, Model model) {
        Product product = productService.findProductByName(name);
        if (product != null) {
            model.addAttribute("ordersList", orderService.findOrderByUser(user));
            model.addAttribute("product", product);

            return "ProductPage";
        }

        model.addAttribute("error", "Product not found");
        model.addAttribute("ordersList", orderService.findOrderByUser(user));

        return "ProductPage";
    }

    @GetMapping("/place/order")
    public String placeOrder(Order order, Model model) {
        double totalAmount = order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        order.setUser(user);
        order.setDate(new Date());

        orderService.createOrder(order);

        model.addAttribute("amount", totalAmount);
        return "OrderStatus";
    }

}
