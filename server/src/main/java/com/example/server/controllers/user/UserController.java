package com.example.server.controllers.user;

import com.example.server.models.*;


import com.example.server.services.appeal.AppealService;
import com.example.server.services.application.ApplicationService;
import com.example.server.services.budget.BudgetService;
import com.example.server.services.category.CategoryService;
import com.example.server.services.department.DepartmentService;
import com.example.server.services.management.ManagementService;
import com.example.server.services.procurementArchive.ProcurementArchiveService;
import com.example.server.services.status.StatusService;
import com.example.server.services.position.PositionService;
import com.example.server.services.role.RoleService;
import com.example.server.services.order.OrderService;
import com.example.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StatusService statusService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    AppealService appealService;
    @Autowired
    BudgetService budgetService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    ManagementService managementService;
    @Autowired
    PositionService positionService;
    @Autowired
    ProcurementArchiveService procurementArchiveService;
    @Autowired
    RoleService roleService;
    
    public void autoRegisterAdmin() {
        if (userService.findByUserName("admin") == null) {
            Role role = roleService.readByRoleName("администратор");
            User admin = new User("admin", "admin", role);
            userService.create(admin);
//            securityService.autoLogin(admin.getUsername(), admin.getPassword());
        }
    }

    public void autoCreateRoles() {
        if (roleService.readByRoleName("администратор") == null) {
            Role role = new Role("администратор");
            roleService.create(role);
        }
        if (roleService.readByRoleName("заказчик") == null) {
            Role role = new Role("заказчик");
            roleService.create(role);
        }
        if (roleService.readByRoleName("исполнитель") == null) {
            Role role = new Role("исполнитель");
            roleService.create(role);
        }
    }

    public void autoCreateStatuses() {
        if (statusService.readByTitle("создано") == null) {
            statusService.create(new Status("создано"));
        }
        if (statusService.readByTitle("зарегистрировано") == null) {
            statusService.create(new Status("зарегистрировано"));
        }
        if (statusService.readByTitle("отклонено") == null) {
            statusService.create(new Status("отклонено"));
        }
        if (statusService.readByTitle("закрыто") == null) {
            statusService.create(new Status("закрыто"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginForm) {
        autoCreateRoles();
        autoRegisterAdmin();
        autoCreateStatuses();

        if (isValidCredentials(loginForm.getUsername(), loginForm.getPassword())) {
            return ResponseEntity.ok("Вход выполнен успешно");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные учетные данные");
    }

    private boolean isValidCredentials(String username, String password) {

        User checkUser = userService.findByUserName(username);
        if (checkUser == null) {
            return false;
        } else {
            return checkUser.getPassword().equals(password);
        }
    }
}

