package com.example.demo.controllers.user;

import com.example.demo.models.*;


import com.example.demo.services.appeal.AppealService;
import com.example.demo.services.application.ApplicationService;
import com.example.demo.services.budget.BudgetService;
import com.example.demo.services.category.CategoryService;
import com.example.demo.services.department.DepartmentService;
import com.example.demo.services.management.ManagementService;
import com.example.demo.services.procurementArchive.ProcurementArchiveService;
import com.example.demo.services.status.StatusService;
import com.example.demo.services.position.PositionService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.order.OrderService;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
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
    public ResponseEntity<String> login(@RequestBody User loginForm) {
        autoCreateRoles();
        autoRegisterAdmin();
        autoCreateStatuses();


        // Ваша логика аутентификации и авторизации здесь
        // ...

        // Пример успешного входа
        if (isValidCredentials(loginForm.getUsername(), loginForm.getPassword())) {
            return ResponseEntity.ok("Вход выполнен успешно");
        }

        // Пример ошибки входа
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

