package com.example.server.controllers.admin;

import com.example.server.models.*;
import com.example.server.services.appeal.AppealService;
import com.example.server.services.application.ApplicationService;
import com.example.server.services.budget.BudgetService;
import com.example.server.services.category.CategoryService;
import com.example.server.services.department.DepartmentService;
import com.example.server.services.management.ManagementService;
import com.example.server.services.order.OrderService;
import com.example.server.services.position.PositionService;
import com.example.server.services.procurementArchive.ProcurementArchiveService;
import com.example.server.services.role.RoleService;
import com.example.server.services.status.StatusService;
import com.example.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", method = { RequestMethod.GET, RequestMethod.POST })

public class AdminController {
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

    @GetMapping(value = "/admin/getAll/appeal")
    public ResponseEntity<List<Appeal>> readAppeals() {
        final List<Appeal> appeals = appealService.readAll();
        return new ResponseEntity<>(appeals, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/application")
    public ResponseEntity<List<Application>> readApplications() {
        final List<Application> applications = applicationService.readAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/budget")
    public ResponseEntity<List<Budget>> readBudgets() {
        final List<Budget> budgets = budgetService.readAll();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/category")
    public ResponseEntity<List<Category>> readCategorys() {
        final List<Category> categories = categoryService.readAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/department")
    public ResponseEntity<List<Department>> readDepartments() {
        final List<Department> departments = departmentService.readAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/management")
    public ResponseEntity<List<Management>> readManagements() {
        final List<Management> managements = managementService.readAll();
        return new ResponseEntity<>(managements, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/order")
    public ResponseEntity<List<Order>> readOrders() {
        final List<Order> orders = orderService.readAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/position")
    public ResponseEntity<List<Position>> readPositions() {
        final List<Position> positions = positionService.readAll();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/create/position")
    public ResponseEntity<?> createPosition(@RequestBody Position position) {
        positionService.create(position);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/admin/getAll/procurementarchive")
    public ResponseEntity<List<ProcurementArchive>> readProcurementArchives() {
        final List<ProcurementArchive> procurementArchives = procurementArchiveService.readAll();
        return new ResponseEntity<>(procurementArchives, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/role")
    public ResponseEntity<List<Role>> readRoles() {
        final List<Role> roles = roleService.readAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/status")
    public ResponseEntity<List<Status>> readStatuses() {
        final List<Status> statuses = statusService.readAll();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getAll/user")
    public ResponseEntity<List<User>> readUsers() {
        final List<User> users = userService.readAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/create/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
