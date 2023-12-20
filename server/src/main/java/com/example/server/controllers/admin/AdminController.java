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
        return appeals != null &&  !appeals.isEmpty()
                ? new ResponseEntity<>(appeals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/application")
    public ResponseEntity<List<Application>> readApplications() {
        final List<Application> applications = applicationService.readAll();
        return applications != null &&  !applications.isEmpty()
                ? new ResponseEntity<>(applications, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/budget")
    public ResponseEntity<List<Budget>> readBudgets() {
        final List<Budget> budgets = budgetService.readAll();
        return budgets != null &&  !budgets.isEmpty()
                ? new ResponseEntity<>(budgets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/category")
    public ResponseEntity<List<Category>> readCategorys() {
        final List<Category> categories = categoryService.readAll();
        return categories != null &&  !categories.isEmpty()
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/department")
    public ResponseEntity<List<Department>> readDepartments() {
        final List<Department> departments = departmentService.readAll();
        return departments != null &&  !departments.isEmpty()
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/management")
    public ResponseEntity<List<Management>> readManagements() {
        final List<Management> managements = managementService.readAll();
        return managements != null &&  !managements.isEmpty()
                ? new ResponseEntity<>(managements, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/order")
    public ResponseEntity<List<Order>> readOrders() {
        final List<Order> orders = orderService.readAll();
        return orders != null &&  !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/position")
    public ResponseEntity<List<Position>> readPositions() {
        final List<Position> positions = positionService.readAll();
        return positions != null &&  !positions.isEmpty()
                ? new ResponseEntity<>(positions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/procurementArchive")
    public ResponseEntity<List<ProcurementArchive>> readProcurementArchives() {
        final List<ProcurementArchive> procurementArchives = procurementArchiveService.readAll();
        return procurementArchives != null &&  !procurementArchives.isEmpty()
                ? new ResponseEntity<>(procurementArchives, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin/getAll/user")
    public ResponseEntity<List<User>> readUsers() {
        final List<User> users = userService.readAll();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
