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
    @PostMapping(value = "/admin/create/appeal")
    public ResponseEntity<?> createAppeal(@RequestBody Appeal appeal) {
        appealService.create(appeal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/appeal/{appealId}")
    public ResponseEntity<Appeal> readAppeal(@PathVariable(name = "appealId") Long appealId) {
        final Appeal appeal = appealService.read(appealId);
        return appeal != null
                ? new ResponseEntity<>(appeal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/appeal/{appealId}")
    public ResponseEntity<?> updateAppeal(@PathVariable(name = "appealId") Long appealId, @RequestBody Appeal appeal) {
        final boolean updated = appealService.update(appealId, appeal);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/appeal/{appealId}")
    public ResponseEntity<?> deleteAppeal(@PathVariable(name = "appealId") Long appealId) {
        final boolean deleted = appealService.delete(appealId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/application")
    public ResponseEntity<List<Application>> readApplications() {
        final List<Application> applications = applicationService.readAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/application")
    public ResponseEntity<?> createApplication(@RequestBody Application application) {
        application.setClosingDate(null);
        applicationService.create(application);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/application/{applicationId}")
    public ResponseEntity<Application> readApplication(@PathVariable(name = "applicationId") Long applicationId) {
        final Application application = applicationService.read(applicationId);
        return application != null
                ? new ResponseEntity<>(application, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/application/{applicationId}")
    public ResponseEntity<?> updateApplication(@PathVariable(name = "applicationId") Long applicationId, @RequestBody Application application) {
        final boolean updated = applicationService.update(applicationId, application);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/application/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable(name = "applicationId") Long applicationId) {
        final boolean deleted = applicationService.delete(applicationId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/budget")
    public ResponseEntity<List<Budget>> readBudgets() {
        final List<Budget> budgets = budgetService.readAll();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/budget")
    public ResponseEntity<?> createBudget(@RequestBody Budget budget) {
        budgetService.create(budget);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/budget/{budgetId}")
    public ResponseEntity<Budget> readBudget(@PathVariable(name = "budgetId") Long budgetId) {
        final Budget budget = budgetService.read(budgetId);
        return budget != null
                ? new ResponseEntity<>(budget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/budget/{budgetId}")
    public ResponseEntity<?> updateBudget(@PathVariable(name = "budgetId") Long budgetId, @RequestBody Budget budget) {
        final boolean updated = budgetService.update(budgetId, budget);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/budget/{budgetId}")
    public ResponseEntity<?> deleteBudget(@PathVariable(name = "budgetId") Long budgetId) {
        final boolean deleted = budgetService.delete(budgetId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/category")
    public ResponseEntity<List<Category>> readCategorys() {
        final List<Category> categories = categoryService.readAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/category/{categoryId}")
    public ResponseEntity<Category> readCategory(@PathVariable(name = "categoryId") Long categoryId) {
        final Category category = categoryService.read(categoryId);
        return category != null
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody Category category) {
        final boolean updated = categoryService.update(categoryId, category);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
        final boolean deleted = categoryService.delete(categoryId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/department")
    public ResponseEntity<List<Department>> readDepartments() {
        final List<Department> departments = departmentService.readAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/department")
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        System.out.println(department);
        departmentService.create(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/department/{departmentId}")
    public ResponseEntity<Department> readDepartment(@PathVariable(name = "departmentId") Long departmentId) {
        final Department department = departmentService.read(departmentId);
        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/department/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "departmentId") Long departmentId, @RequestBody Department department) {
        final boolean updated = departmentService.update(departmentId, department);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/department/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "departmentId") Long departmentId) {
        final boolean deleted = departmentService.delete(departmentId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/management")
    public ResponseEntity<List<Management>> readManagements() {
        final List<Management> managements = managementService.readAll();
        return new ResponseEntity<>(managements, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/management")
    public ResponseEntity<?> createManagement(@RequestBody Management management) {
        managementService.create(management);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/management/{managementId}")
    public ResponseEntity<Management> readManagement(@PathVariable(name = "managementId") Long managementId) {
        final Management management = managementService.read(managementId);
        return management != null
                ? new ResponseEntity<>(management, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/management/{managementId}")
    public ResponseEntity<?> updateManagement(@PathVariable(name = "managementId") Long managementId, @RequestBody Management management) {
        final boolean updated = managementService.update(managementId, management);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/management/{managementId}")
    public ResponseEntity<?> deleteManagement(@PathVariable(name = "managementId") Long managementId) {
        final boolean deleted = managementService.delete(managementId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/order")
    public ResponseEntity<List<Order>> readOrders() {
        final List<Order> orders = orderService.readAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/order/{orderId}")
    public ResponseEntity<Order> readOrder(@PathVariable(name = "orderId") Long orderId) {
        final Order order = orderService.read(orderId);
        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/order/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "orderId") Long orderId, @RequestBody Order order) {
        final boolean updated = orderService.update(orderId, order);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/order/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "orderId") Long orderId) {
        final boolean deleted = orderService.delete(orderId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
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
    @GetMapping(value = "/admin/get/position/{positionId}")
    public ResponseEntity<Position> readPosition(@PathVariable(name = "positionId") Long positionId) {
        final Position position = positionService.read(positionId);
        return position != null
                ? new ResponseEntity<>(position, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/position/{positionId}")
    public ResponseEntity<?> updatePosition(@PathVariable(name = "positionId") Long positionId, @RequestBody Position position) {
        final boolean updated = positionService.update(positionId, position);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/position/{positionId}")
    public ResponseEntity<?> deletePosition(@PathVariable(name = "positionId") Long positionId) {
        final boolean deleted = positionService.delete(positionId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/procurementarchive")
    public ResponseEntity<List<ProcurementArchive>> readProcurementArchives() {
        final List<ProcurementArchive> procurementArchives = procurementArchiveService.readAll();
        return new ResponseEntity<>(procurementArchives, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/procurementarchive")
    public ResponseEntity<?> createProcurementArchive(@RequestBody ProcurementArchive procurementArchive) {
        procurementArchiveService.create(procurementArchive);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/procurementarchive/{procurementArchiveId}")
    public ResponseEntity<ProcurementArchive> readProcurementArchive(@PathVariable(name = "procurementArchiveId") Long procurementArchiveId) {
        final ProcurementArchive procurementArchive = procurementArchiveService.read(procurementArchiveId);
        return procurementArchive != null
                ? new ResponseEntity<>(procurementArchive, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/procurementarchive/{procurementArchiveId}")
    public ResponseEntity<?> updateProcurementArchive(@PathVariable(name = "procurementArchiveId") Long procurementArchiveId, @RequestBody ProcurementArchive procurementArchive) {
        final boolean updated = procurementArchiveService.update(procurementArchiveId, procurementArchive);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/procurementarchive/{procurementArchiveId}")
    public ResponseEntity<?> deleteProcurementArchive(@PathVariable(name = "procurementArchiveId") Long procurementArchiveId) {
        final boolean deleted = procurementArchiveService.delete(procurementArchiveId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping(value = "/admin/getAll/role")
    public ResponseEntity<List<Role>> readRoles() {
        final List<Role> roles = roleService.readAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/role")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        roleService.create(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/role/{roleId}")
    public ResponseEntity<Role> readRole(@PathVariable(name = "roleId") Long roleId) {
        final Role role = roleService.read(roleId);
        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping(value = "/admin/getAll/status")
    public ResponseEntity<List<Status>> readStatuses() {
        final List<Status> statuses = statusService.readAll();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
    @PostMapping(value = "/admin/create/status")
    public ResponseEntity<?> createStatus(@RequestBody Status status) {
        statusService.create(status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/admin/get/status/{statusId}")
    public ResponseEntity<Status> readStatus(@PathVariable(name = "statusId") Long statusId) {
        final Status status = statusService.read(statusId);
        return status != null
                ? new ResponseEntity<>(status, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/status/{statusId}")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "statusId") Long statusId, @RequestBody Status status) {
        final boolean updated = statusService.update(statusId, status);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/status/{statusId}")
    public ResponseEntity<?> deleteStatus(@PathVariable(name = "statusId") Long statusId) {
        final boolean deleted = statusService.delete(statusId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
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
    @GetMapping(value = "/admin/get/user/{userId}")
    public ResponseEntity<User> readUser(@PathVariable(name = "userId") Long userId) {
        final User user = userService.read(userId);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/admin/update/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "userId") Long userId, @RequestBody User user) {
        final boolean updated = userService.update(userId, user);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/admin/delete/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
        final boolean deleted = userService.delete(userId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }




}
