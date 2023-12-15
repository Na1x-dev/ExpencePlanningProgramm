package com.example.server.controllers.user;

import com.example.server.models.Appeal;
import com.example.server.models.User;
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

public class CustomerController {
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

    @GetMapping(value = "/appeals/getByUser/{userName}")
    public ResponseEntity<?> getAppealsOfUser(@PathVariable(name = "userName") String userName) {
        List<Appeal> appeals = appealService.findByUserName(userName);
        return new ResponseEntity<>(appeals, HttpStatus.OK);
    }

    @PostMapping(value = "/appeals/create")
    public ResponseEntity<?> createAppeal(@RequestBody Appeal appeal) {
        appealService.create(appeal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PutMapping(value = "/clients/{id}")
    //public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
    //   final boolean updated = clientService.update(client, id);
    //
    //   return updated
    //           ? new ResponseEntity<>(HttpStatus.OK)
    //           : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    //}

    //@DeleteMapping(value = "/clients/{id}")
    //public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
    //   final boolean deleted = clientService.delete(id);
    //
    //   return deleted
    //           ? new ResponseEntity<>(HttpStatus.OK)
    //           : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    //}





}
