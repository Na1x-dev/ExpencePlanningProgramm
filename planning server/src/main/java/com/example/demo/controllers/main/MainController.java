package com.example.demo.controllers.main;

import com.example.demo.models.*;
import com.example.demo.services.application.ApplicationService;
import com.example.demo.services.status.StatusService;
import com.example.demo.services.category.CategoryService;
import com.example.demo.services.position.PositionService;
import com.example.demo.services.order.OrderService;

import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.util.*;

@Controller
public class MainController {
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
    ShiftService shiftService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    ProductService productService;
    @Autowired
    CourierService courierService;
    @Autowired
    PositionService positionService;
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    CountryService countryService;

    TableMode tableMode = new TableMode();

    AppMode appMode = new AppMode();


    @GetMapping({"/mainPage/index/reports"})
    public String reportPageMode(Model model, Principal user) {
        appMode.setMode(2);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/adminTables"})
    public String mainPageMode(Model model, Principal user) {
        appMode.setMode(1);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/supplyReport"})
    public String mainPageSupplyReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(8);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/costReport"})
    public String mainPageCostReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(9);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/closeApplicationReport"})
    public String mainPageCloseApplicationReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(10);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/yearReport"})
    public String mainPageYearReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(11);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/deliveryReport"})
    public String mainPageDeliveryReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(12);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/reports/processingReport"})
    public String mainPageProcessingReport(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(13);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index"})
    public String mainPage(Model model, Principal user) {
        model.addAttribute("appMode", appMode);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("shifts", shiftService.readAll());
        model.addAttribute("applications", applicationService.readAll());
        model.addAttribute("categories", categoryService.readAll());
        model.addAttribute("couriers", courierService.readAll());
        model.addAttribute("products", productService.readAll());
        model.addAttribute("suppliers", supplierService.readAll());
        model.addAttribute("supplies", supplyService.readAll());
        model.addAttribute("users", userService.readAll());
        model.addAttribute("tableMode", tableMode);
        model.addAttribute("countries", countryService.readAll());
        model.addAttribute("statuses", statusService.readAll());
        model.addAttribute("suppliesProducts", getSupplyProductList());
        model.addAttribute("admins", getAdmins());
        return "mainPage/index";
    }

    @GetMapping({"/mainPage/index/shiftList"})
    public String mainPageShift(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(1);
        return "redirect:/mainPage/index";
    }


    @PostMapping("/mainPage/index/shiftList/update/{id}")
    public String mainPageShiftUpdate(Model model, @ModelAttribute("shift") Shift shift, Principal user, @PathVariable("id") Long shiftId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        shift.setUser(userService.readById(shift.getUser().getUserId()));
        if (shift.getUser().getUserId() != null)
            shift.setUser(userService.readById(shift.getUser().getUserId()));
        shiftService.update(shiftId, shift);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/shiftList/delete/{id}")
    public String mainPageShiftDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        shiftService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/shiftList/add")
    public String mainPageShiftAdd(Model model, @ModelAttribute("shift") Shift shift, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));

        shift.setUser(userService.readById(shift.getUser().getUserId()));
        if (shift.getUser().getUserId() != null)
            shift.setUser(userService.readById(shift.getUser().getUserId()));
        shiftService.create(shift);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/productList"})
    public String mainPageProduct(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(2);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/productList/update/{id}")
    public String mainPageProductUpdate(Model model, @ModelAttribute("product") Product product, Principal user, @PathVariable("id") Long productId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        productService.update(productId, product);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/productList/delete/{id}")
    public String mainPageProductDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        productService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/productList/add")
    public String mainPageProductAdd(Model model, @ModelAttribute("product") Product product, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        productService.create(product);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/categoryList"})
    public String mainPageCategory(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(3);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/categoryList/update/{id}")
    public String mainPageCategoryUpdate(Model model, @ModelAttribute("category") Category category, Principal user, @PathVariable("id") Long categoryId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        categoryService.update(categoryId, category);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/categoryList/delete/{id}")
    public String mainPageCategoryDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        categoryService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/categoryList/add")
    public String mainPageCategoryAdd(Model model, @ModelAttribute("category") Category category, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        categoryService.create(category);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/supplierList"})
    public String mainPageSupplier(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(4);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/supplierList/update/{id}")
    public String mainPageSupplierUpdate(Model model, @ModelAttribute("supplier") Supplier supplier, Principal user, @PathVariable("id") Long supplierId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplierService.update(supplierId, supplier);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/supplierList/delete/{id}")
    public String mainPageSupplierDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplierService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/supplierList/add")
    public String mainPageSupplierAdd(Model model, @ModelAttribute("supplier") Supplier supplier, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplierService.create(supplier);
        return "redirect:/mainPage/index";
    }


    @GetMapping({"/mainPage/index/supplyList"})
    public String mainPageSupply(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(5);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/supplyList/update/{id}")
    public String mainPageSupplyUpdate(Model model, @ModelAttribute("supply") Supply supply, Principal user, @PathVariable("id") Long supplyId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplyService.update(supplyId, supply);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/supplyList/delete/{id}")
    public String mainPageSupplyDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplyService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/supplyList/add")
    public String mainPageSupplyAdd(Model model, @ModelAttribute("supply") Supply supply, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        supplyService.create(supply);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/mainPage/index/courierList"})
    public String mainPageCourier(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(6);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/courierList/update/{id}")
    public String mainPageCourierUpdate(Model model, @ModelAttribute("courier") Courier courier, Principal user, @PathVariable("id") Long courierId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        courierService.update(courierId, courier);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/courierList/delete/{id}")
    public String mainPageCourierDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        courierService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/courierList/add")
    public String mainPageCourierAdd(Model model, @ModelAttribute("courier") Courier courier, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        courierService.create(courier);
        return "redirect:/mainPage/index";
    }


    @GetMapping({"/mainPage/index/applicationList"})
    public String mainPageApplication(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(7);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/applicationList/update/{id}")
    public String mainPageApplicationUpdate(Model model, @ModelAttribute("application") Application application, Principal user, @PathVariable("id") Long applicationId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        System.out.println(application);
        applicationService.update(applicationId, application);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/applicationList/delete/{id}")
    public String mainPageApplicationDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        applicationService.delete(unionMemberId);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/mainPage/index/applicationList/add")
    public String mainPageApplicationAdd(Model model, @ModelAttribute("application") Application application, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        applicationService.create(application);
        return "redirect:/mainPage/index";
    }


    Shift shift;

    @GetMapping({"/workPage/index/openShift"})
    public String workPageOpenShift(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        shift = new Shift(userService.findByUsername(user.getName()));
        tableMode.setMode(1);
        shift = shiftService.create(shift);
        return "redirect:/workPage/index";
    }

    @GetMapping({"/workPage/index"})
    public String workPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("tableMode", tableMode);
        model.addAttribute("products", productService.readAll());
        model.addAttribute("orders", orderService.readAll());
        model.addAttribute("suppliesProducts", getSupplyProductList());
        model.addAttribute("supplies", supplyService.readAll());
        model.addAttribute("deliveryOrders", getDeliveryOrderList());
        model.addAttribute("couriers", courierService.readAll());
        model.addAttribute("nonDeliveredOrders", getNonDeliveredOrders());

        return "workPage/index";
    }

    public List<User> getAdmins() {
        List<User> users = userService.readAll();
        List<User> admins = new ArrayList<>();
        for (User user : users) {
            if (user.isAdmin()) {
                admins.add(user);
            }
        }
        return admins;
    }

    @GetMapping({"/workPage/index/order"})
    public String workPageOrder(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(1);
        return "redirect:/workPage/index";
    }

    @GetMapping({"/workPage/index/supply"})
    public String workPageSupply(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(2);
        return "redirect:/workPage/index";
    }

    @GetMapping({"/workPage/index/delivery"})
    public String workPageDelivery(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(3);
        return "redirect:/workPage/index";
    }

    @GetMapping({"/workPage/index/close"})
    public String workPageClose(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        shift.closeShift();
        shiftService.update(shift.getShiftId(), shift);
        return "redirect:/mainPage/index";
    }

    @PostMapping({"/workPage/index/delivery/add"})
    public String workPageDeliveryAdd(Model model, @ModelAttribute("newDeliveryOrder") DeliveryOrder deliveryOrder, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Delivery delivery = deliveryOrder.getDelivery();
        Order order = orderService.readById(deliveryOrder.getOrder().getOrderId());
        delivery.getOrders().add(order);
        delivery = deliveryService.create(delivery);
        order.setDelivery(delivery);
        orderService.update(order.getOrderId(), order);
        return "redirect:/workPage/index";
    }

    @PostMapping({"/workPage/index/delivery/add/{id}"})
    public String workPageDeliveryAddById(Model model, @ModelAttribute("newDeliveryOrder") DeliveryOrder deliveryOrder, @PathVariable("id") Long deliveryId, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Delivery delivery = deliveryService.readById(deliveryId);
        Order order = orderService.readById(deliveryOrder.getOrder().getOrderId());
        delivery.getOrders().add(order);
        deliveryService.update(delivery.getDeliveryId(), delivery);
        order.setDelivery(delivery);
        orderService.update(order.getOrderId(), order);
        return "redirect:/workPage/index";
    }


    public List<SupplyProduct> getSupplyProductList() {
        List<Supply> supplies = supplyService.readAll();
        List<SupplyProduct> supplyProducts = new ArrayList<>();
        for (Supply supply : supplies) {
            //          if (!unionMember.getName().equals("")) {
            Set<Product> products = supply.getProducts();
            for (Product product : products) {
                supplyProducts.add(new SupplyProduct(supply, product));
            }
        }
        //    }
        return supplyProducts;
    }

    public List<Order> getNonDeliveredOrders() {
        List<Order> orders = orderService.readAll();
        List<Order> newOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getDelivery() == null) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    @GetMapping({"/applicationPage/index"})
    public String applicationPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("application", new Application());

        return "applicationPage/index";
    }

    @PostMapping("/applicationPage/index")
    public String applicationPage(Model model, @ModelAttribute("application") Application application, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        application.setStatus(statusService.readByTitle("Запрос"));
        application.setUserOpen(userService.findByUsername(user.getName()));
        application.setUserClose(userService.findByUsername("-"));
        applicationService.create(application);
        return "redirect:/mainPage/index";
    }


    @GetMapping({"/mainPage/index/productSupplyList"})
    public String productSupplyList(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        tableMode.setMode(14);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/referencePage/index"})
    public String referencePage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        return "referencePage/index";
    }

    @PostMapping("/mainPage/index/productSupplyList/add")
    public String productSupplyListAdd(Model model, @ModelAttribute("newSupplyProduct") SupplyProduct supplyProduct, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));

        System.out.println(supplyProduct.toString());

        Supply supply = supplyService.readById(supplyProduct.getSupply().getSupplyId());
        Product product = productService.readById(supplyProduct.getProduct().getProductId());
        product.getSupplies().add(supply);
        supply.getProducts().add(product);

        System.out.println(supply.toString());
        System.out.println(product.toString());

//        productService.update(product.getProductId(), product);
        supplyService.update(supply.getSupplyId(), supply);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/productSupplyList/delete/{supplyId}/{productId}")
    public String productSupplyListDelete(Model model, Principal user, @PathVariable("supplyId") Long supplyId, @PathVariable("productId") Long productId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Supply supply = supplyService.readById(supplyId);
        Product product = productService.readById(productId);
        supply.getProducts().remove(product);
        supplyService.update(supplyId, supply);
        return "redirect:/mainPage/index";
    }

    @PostMapping("/workPage/index/productSupplyList/add")
    public String productSupplyListAddWorkPage(Model model, @ModelAttribute("newSupplyProduct") SupplyProduct supplyProduct, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Supply supply = supplyService.readById(supplyProduct.getSupply().getSupplyId());
        supply.getProducts().add(supplyProduct.getProduct());
        supplyService.update(supply.getSupplyId(), supply);
        return "redirect:/workPage/index";
    }

    @PostMapping("/workPage/index/orderList/add")
    public String orderListAddWorkPage(Model model, @ModelAttribute("newOrder") Order order, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Product product = productService.readById(order.getProduct().getProductId());
        product.setProductCount(product.getProductCount() - order.getOrderCount());
        if (product.getProductCount() >= 0) {
            productService.update(product.getProductId(), product);
            order = orderService.create(order);
            order.setProduct(productService.readById(order.getProduct().getProductId()));
            model.addAttribute("order", order);
            someOrder = order;
            return "redirect:/workPage/specification";
        }
        return "redirect:/workPage/index";
    }

    Order someOrder;

    @GetMapping({"/workPage/specification"})
    public String specificationPage(Model model, Principal user) {
        System.out.println(someOrder);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("order", someOrder);
        return "workPage/specification";
    }


    public List<SupplyProduct> getProductSupplyList() {
        List<Product> products = productService.readAll();
        List<SupplyProduct> supplyProducts = new ArrayList<>();
        for (Product product : products) {
            Set<Supply> supplies = product.getSupplies();
            for (Supply supply : supplies) {
                supplyProducts.add(new SupplyProduct(supply, product));
            }
        }
        //    }
        return supplyProducts;
    }


    public List<DeliveryOrder> getDeliveryOrderList() {
        List<Delivery> deliveries = deliveryService.readAll();
        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            List<Order> orders = delivery.getOrders();
            for (Order order : orders) {
                deliveryOrders.add(new DeliveryOrder(delivery, order));
            }
        }
        return deliveryOrders;
    }

//
//    @PostMapping("/mainPage/index/update/{id}")
//    public String mainPageUpdate(Model model, @ModelAttribute("updateUnionMember") UnionMember updateUnionMember, Principal user, @PathVariable("id") Long unionMemberId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        updateUnionMember.setGender(categoryService.readById(updateUnionMember.getGender().getGenderId()));
//        if (updateUnionMember.getPosition().getPositionId() != null)
//            updateUnionMember.setPosition(positionService.readById(updateUnionMember.getPosition().getPositionId()));
//        updateUnionMember.getPhoneNumbers().get(0).setUnionMember(updateUnionMember);
//        if (!Objects.equals(orderService.readByName("").getUnionMemberId(), unionMemberId)) {
//            orderService.update(unionMemberId, updateUnionMember);
//        }
//        savePhoneNumber(updateUnionMember);
//        return "redirect:/mainPage/index";
//    }
//
//    @GetMapping("/mainPage/index/delete/{id}")
//    public String mainPageDelete(Model model, Principal user, @PathVariable("id") Long unionMemberId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        if (!Objects.equals(orderService.readByName("").getUnionMemberId(), unionMemberId)) {
//            orderService.delete(unionMemberId);
//        }
//        return "redirect:/mainPage/index";
//    }
//
//    @GetMapping({"/childrenPage/index"})
//    public String childrenPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("genders", categoryService.readAll());
//        model.addAttribute("newChild", new Child());
//        model.addAttribute("updateChild", new Child());
//        model.addAttribute("unionMembers", orderService.readAll());
//        model.addAttribute("parentsChildren", getParentChildList());
//        List<UnionMember> unionMembersTable = orderService.readAll();
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        return "childrenPage/index";
//    }
//
//    @PostMapping("/childrenPage/index/add")
//    public String childrenPageAdd(Model model, @ModelAttribute("newChild") Child newChild, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        UnionMember unionMember = new UnionMember();
//        unionMember.setUnionMemberId(0L);
//        newChild.getUnionMembers().remove(unionMember);
//        countryService.create(newChild);
//        return "redirect:/childrenPage/index";
//    }
//
//    @PostMapping("/childrenPage/index/update/{id}")
//    public String childrenPageUpdate(Model model, @ModelAttribute("updateChild") Child updateChild, Principal user, @PathVariable("id") Long childId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        updateChild.setGender(categoryService.readById(updateChild.getGender().getGenderId()));
//        countryService.update(childId, updateChild);
//        return "redirect:/childrenPage/index";
//    }
//
//    @GetMapping("/childrenPage/index/delete/{id}")
//    public String childrenPageDelete(Model model, Principal user, @PathVariable("id") Long childId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        countryService.delete(childId);
//        return "redirect:/childrenPage/index";
//    }
//
//    @GetMapping({"/unionMemPubOrgPage/index"})
//    public String unionMemPubOrgPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("unionMembersPublicOrganizations", getPublicOrgUnionMemberList());
//        model.addAttribute("unionMembers", orderService.readAll());
//        List<UnionMember> unionMembersTable = orderService.readAll();
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        model.addAttribute("newMemberOrg", new PublicOrgUnionMember());
//        model.addAttribute("updateMemberOrg", new PublicOrgUnionMember());
//        model.addAttribute("publicOrganizations", courierService.readAll());
//        return "unionMemPubOrgPage/index";
//    }
//
//    @PostMapping({"/unionMemPubOrgPage/index/add"})
//    public String unionMemPubOrgPageAdd(Model model, @ModelAttribute("newMemberOrg") PublicOrgUnionMember newMemberOrg, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        UnionMember unionMember = orderService.readById(newMemberOrg.getUnionMember().getUnionMemberId());
//        unionMember.getPublicOrganizations().add(newMemberOrg.getPublicOrganization());
//        orderService.update(unionMember.getUnionMemberId(), unionMember);
//        return "redirect:/unionMemPubOrgPage/index";
//    }
//
//    @GetMapping("/unionMemPubOrgPage/index/delete/{unionMemberId}/{publicOrganizationId}")
//    public String unionMemPubOrgPageDelete(Model model, Principal user, @PathVariable("unionMemberId") Long unionMemberId, @PathVariable("publicOrganizationId") Long publicOrganizationId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        UnionMember unionMember = orderService.readById(unionMemberId);
//        PublicOrganization publicOrganization = courierService.readById(publicOrganizationId);
//        unionMember.getPublicOrganizations().remove(publicOrganization);
//        orderService.update(unionMemberId, unionMember);
//        return "redirect:/unionMemPubOrgPage/index";
//    }
//
//    @GetMapping({"/publicOrganizationsPage/index"})
//    public String publicOrganizationsPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("publicOrganizations", courierService.readAll());
//        model.addAttribute("newPublicOrganization", new PublicOrganization());
//        model.addAttribute("updatePublicOrganization", new PublicOrganization());
//        return "publicOrganizationsPage/index";
//    }
//
//    @PostMapping("/publicOrganizationsPage/index/add")
//    public String publicOrganizationsPageAdd(Model model, @ModelAttribute("newPublicOrganization") PublicOrganization newPublicOrganization, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        courierService.create(newPublicOrganization);
//        return "redirect:/publicOrganizationsPage/index";
//    }
//
//    @PostMapping("/publicOrganizationsPage/index/update/{id}")
//    public String publicOrganizationsPageUpdate(Model model, @ModelAttribute("updatePublicOrganization") PublicOrganization updatePublicOrganization, Principal user, @PathVariable("id") Long publicOrganizationId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
////        updatePublicOrganization.setGender(genderService.readById(updateChild.getGender().getGenderId()));
//        courierService.update(publicOrganizationId, updatePublicOrganization);
//        return "redirect:/publicOrganizationsPage/index";
//    }
//
//    @GetMapping("/publicOrganizationsPage/index/delete/{id}")
//    public String publicOrganizationsPageDelete(Model model, Principal user, @PathVariable("id") Long publicOrganizationId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        courierService.delete(publicOrganizationId);
//        return "redirect:/publicOrganizationsPage/index";
//    }
//
//    @GetMapping({"/positionsPage/index"})
//    public String positionsPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("newPosition", new Position());
//        model.addAttribute("updatePosition", new Position());
//        model.addAttribute("positions", positionService.readAll());
//        List<Position> positionsTable = positionService.readAll();
//        positionsTable.remove(positionService.readByTitle(""));
//        model.addAttribute("positionsTable", positionsTable);
//        return "positionsPage/index";
//    }
//
//    @PostMapping("/positionsPage/index/add")
//    public String positionsPageAdd(Model model, @ModelAttribute("newPosition") Position newPosition, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        positionService.create(newPosition);
//        return "redirect:/positionsPage/index";
//    }
//
//    @PostMapping("/positionsPage/index/update/{id}")
//    public String positionsPageUpdate(Model model, @ModelAttribute("updatePosition") Position updatePosition, Principal user, @PathVariable("id") Long positionId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
////        updatePosition.ssetGender(genderService.readById(updateChild.getGender().getGenderId()));
//        if (!Objects.equals(positionService.readByPositionTitle("").getPositionId(), positionId)) {
//            positionService.update(positionId, updatePosition);
//        }
//        return "redirect:/positionsPage/index";
//    }
//
//    @GetMapping("/positionsPage/index/delete/{id}")
//    public String positionsPageDelete(Model model, Principal user, @PathVariable("id") Long positionId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        positionService.delete(positionId);
//        return "redirect:/positionsPage/index";
//    }
//
//    List<Application> applications;
//
//    boolean inSearch = false;
//
//    @GetMapping({"/unionMembersApplicationsPage/index"})
//    public String unionMembersApplicationsPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        if(applications == null || !inSearch){
//            applications = applicationService.readAll();
//        }
//        model.addAttribute("applications", applications);
//        model.addAttribute("newApplication", new Application());
//        model.addAttribute("updateApplication", new Application());
//        model.addAttribute("searchUnionMember", new UnionMember());
//        model.addAttribute("unionMembers", orderService.readAll());
//        model.addAttribute("applicationTypes", statusService.readAll());
//        model.addAttribute("materialPayments", supplierService.readAll());
//        model.addAttribute("meetingMinutes", supplyService.readAll());
//        List<UnionMember> unionMembersTable = orderService.readAll();
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        inSearch = false;
//        return "unionMembersApplicationsPage/index";
//    }
//
//    @PostMapping("/unionMembersApplicationsPage/index/add")
//    public String unionMembersApplicationsPageAdd(Model model, @ModelAttribute("newApplication") Application newApplication, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        applicationService.create(newApplication);
//        return "redirect:/unionMembersApplicationsPage/index";
//    }
//
//    @PostMapping("/unionMembersApplicationsPage/index/update/{id}")
//    public String unionMembersApplicationsPageUpdate(Model model, @ModelAttribute("updateApplication") Application updateApplication, Principal user, @PathVariable("id") Long applicationId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        applicationService.update(applicationId, updateApplication);
//        return "redirect:/unionMembersApplicationsPage/index";
//    }
//
//    @GetMapping("/unionMembersApplicationsPage/index/delete/{id}")
//    public String unionMembersApplicationsPageDelete(Model model, Principal user, @PathVariable("id") Long applicationId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        applicationService.delete(applicationId);
//        return "redirect:/unionMembersApplicationsPage/index";
//    }
//
//    @GetMapping({"/searchApplicationPage/index/findBySurname"})
//    public String searchApplicationPageBySurname(Model model, Principal user, @ModelAttribute("searchUnionMember") UnionMember searchUnionMember) {
//        inSearch=true;
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        applications = applicationService.readByUnionMemberSurname(searchUnionMember.getSurname());
//        model.addAttribute("applications", applications);
//        return "redirect:/unionMembersApplicationsPage/index";
//    }
//
//    @GetMapping({"/applicationTypesPage/index"})
//    public String applicationTypesPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("applicationTypes", statusService.readAll());
//        model.addAttribute("newApplicationType", new ApplicationType());
//        model.addAttribute("updateApplicationType", new ApplicationType());
//        return "applicationTypesPage/index";
//    }
//
//    @PostMapping("/applicationTypesPage/index/add")
//    public String applicationTypesPageAdd(Model model, @ModelAttribute("newApplicationType") ApplicationType newApplicationType, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        statusService.create(newApplicationType);
//        return "redirect:/applicationTypesPage/index";
//    }
//
//    @PostMapping("/applicationTypesPage/index/update/{id}")
//    public String applicationTypesPageUpdate(Model model, @ModelAttribute("updateApplicationType") ApplicationType updateApplicationType, Principal user, @PathVariable("id") Long applicationTypeId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        statusService.update(applicationTypeId, updateApplicationType);
//        return "redirect:/applicationTypesPage/index";
//    }
//
//    @GetMapping("/applicationTypesPage/index/delete/{id}")
//    public String applicationTypesPageDelete(Model model, Principal user, @PathVariable("id") Long applicationTypeId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        applicationService.delete(applicationTypeId);
//        return "redirect:/applicationTypesPage/index";
//    }
//
//    @GetMapping({"/paymentsAmountPage/index"})
//    public String paymentsAmountPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("materialPayments", supplierService.readAll());
//        model.addAttribute("grounds", shiftService.readAll());
//        model.addAttribute("newMaterialPayment", new MaterialPayment());
//        model.addAttribute("updateMaterialPayment", new MaterialPayment());
//        return "paymentsAmountPage/index";
//    }
//
//    @PostMapping("/paymentsAmountPage/index/add")
//    public String paymentsAmountPageAdd(Model model, @ModelAttribute("newMaterialPayment") MaterialPayment newMaterialPayment, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        newMaterialPayment.setGroundsForFinPayment(shiftService.readById(newMaterialPayment.getGroundsForFinPayment().getGroundId()));
//        supplierService.create(newMaterialPayment);
//        return "redirect:/paymentsAmountPage/index";
//    }
//
//    @PostMapping("/paymentsAmountPage/index/update/{id}")
//    public String paymentsAmountPageUpdate(Model model, @ModelAttribute("updateMaterialPayment") MaterialPayment updateMaterialPayment, Principal user, @PathVariable("id") Long materialPaymentId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        supplierService.update(materialPaymentId, updateMaterialPayment);
//        return "redirect:/paymentsAmountPage/index";
//    }
//
//    @GetMapping("/paymentsAmountPage/index/delete/{id}")
//    public String paymentsAmountPageDelete(Model model, Principal user, @PathVariable("id") Long materialPaymentId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        supplierService.delete(materialPaymentId);
//        return "redirect:/paymentsAmountPage/index";
//    }
//
//    @GetMapping({"/groundsForFinPayPage/index"})
//    public String groundsForFinPayPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("groundsForFinPayments", shiftService.readAll());
//        model.addAttribute("newPayGround", new GroundsForFinPayment());
//        model.addAttribute("updatePayGround", new GroundsForFinPayment());
//        return "groundsForFinPayPage/index";
//    }
//
//    @PostMapping("/groundsForFinPayPage/index/add")
//    public String groundsForFinPayPageAdd(Model model, @ModelAttribute("newPayGround") GroundsForFinPayment newPayGround, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        shiftService.create(newPayGround);
//        return "redirect:/groundsForFinPayPage/index";
//    }
//
//    @PostMapping("/groundsForFinPayPage/index/update/{id}")
//    public String groundsForFinPayPageUpdate(Model model, @ModelAttribute("updatePayGround") GroundsForFinPayment updatePayGround, Principal user, @PathVariable("id") Long groundId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        shiftService.update(groundId, updatePayGround);
//        return "redirect:/groundsForFinPayPage/index";
//    }
//
//    @GetMapping("/groundsForFinPayPage/index/delete/{id}")
//    public String groundsForFinPayPageDelete(Model model, Principal user, @PathVariable("id") Long groundId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        shiftService.delete(groundId);
//        return "redirect:/groundsForFinPayPage/index";
//    }
//
//    @GetMapping({"/meetingMinutesPage/index"})
//    public String meetingMinutesPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("newMeetingMinute", new MeetingMinute());
//        model.addAttribute("updateMeetingMinute", new MeetingMinute());
//        model.addAttribute("meetingMinutes", supplyService.readAll());
//        List<MeetingMinute> meetingMinutesTable = supplyService.readAll();
//        meetingMinutesTable.remove(supplyService.readByMeetingMinuteNumber(0));
//        model.addAttribute("meetingMinutesTable", meetingMinutesTable);
//        return "meetingMinutesPage/index";
//    }
//
//    @PostMapping("/meetingMinutesPage/index/add")
//    public String meetingMinutesPageAdd(Model model, @ModelAttribute("newMeetingMinute") MeetingMinute newMeetingMinute, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        supplyService.create(newMeetingMinute);
//        return "redirect:/meetingMinutesPage/index";
//    }
//
//    @PostMapping("/meetingMinutesPage/index/update/{id}")
//    public String meetingMinutesPageUpdate(Model model, @ModelAttribute("updateMeetingMinute") MeetingMinute updateMeetingMinute, Principal user, @PathVariable("id") Long meetingMinuteId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        supplyService.update(meetingMinuteId, updateMeetingMinute);
//        return "redirect:/meetingMinutesPage/index";
//    }
//
//    @GetMapping("/meetingMinutesPage/index/delete/{id}")
//    public String meetingMinutesPageDelete(Model model, Principal user, @PathVariable("id") Long meetingMinuteId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        supplyService.delete(meetingMinuteId);
//        return "redirect:/meetingMinutesPage/index";
//    }
//
//    List<UnionMember> unionMembersTable;
//
//    @GetMapping({"/searchMemberPage/index"})
//    public String searchMemberPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("positions", positionService.readAll());
//        model.addAttribute("searchUnionMember", new UnionMember());
//        model.addAttribute("unionMembers", orderService.readAll());
//        List<Position> positionsTable = positionService.readAll();
//        positionsTable.remove(positionService.readByTitle(""));
//        model.addAttribute("positionsTable", positionsTable);
//        if(unionMembersTable == null){
//            unionMembersTable = new ArrayList<>();
//        }
//        unionMembersTable.remove(orderService.readByName(""));
//        System.out.println(unionMembersTable);
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        return "searchMemberPage/index";
//    }
//
//    @GetMapping({"/searchMemberPage/index/findByPosition"})
//    public String searchMemberPageByPosition(Model model, Principal user, @ModelAttribute("searchUnionMember") UnionMember searchUnionMember) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("positions", positionService.readAll());
//        unionMembersTable = orderService.readByPosition(searchUnionMember.getPosition().getPositionId());
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        return "redirect:/searchMemberPage/index";
//    }
//
//    @GetMapping({"/searchMemberPage/index/findBySurname"})
//    public String searchMemberPageBySurname(Model model, Principal user, @ModelAttribute("searchUnionMember") UnionMember searchUnionMember) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("positions", positionService.readAll());
//        unionMembersTable = orderService.readBySurname(searchUnionMember.getSurname());
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        return "redirect:/searchMemberPage/index";
//    }
//
//    TableMode tableMode;
//
//    @GetMapping({"/reportsPage/index"})
//    public String reportsPage(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("parentsChildren", getChildren13());
//        if(tableMode ==null){
//            tableMode = new TableMode();
//        }
//        model.addAttribute("tableMode", tableMode);
//        List<UnionMember> unionMembersTable = orderService.readPensioners();
//        unionMembersTable.remove(orderService.readByName(""));
//        model.addAttribute("unionMembersTable", unionMembersTable);
//        return "reportsPage/index";
//    }
//
//    @GetMapping({"/reportsPage/index/children13"})
//    public String reportsPageChildren13(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        tableMode.setMode(1);
//        return "redirect:/reportsPage/index";
//    }
//
//    @GetMapping({"/reportsPage/index/pensioners"})
//    public String reportsPagePensioners(Model model, Principal user) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        tableMode.setMode(2);
//        return "redirect:/reportsPage/index";
//    }
//
//    public List<ParentChild> getChildren13(){
//        List<ParentChild> parentsChildren = getParentChildList();
//        List<ParentChild> result = new ArrayList<>();
//        for(ParentChild parentChild : parentsChildren){
//            if(new Date().getTime() - parentChild.getChild().getBirthdate().getTime() < 441806400000L){
//                result.add(parentChild);
//            }
//        }
//        return result;
//    }
//
//    public List<ParentChild> getParentChildList() {
//        List<UnionMember> unionMembers = orderService.readAll();
//        List<ParentChild> parentsChildren = new ArrayList<>();
//        for (UnionMember unionMember : unionMembers) {
//            if (!unionMember.getName().equals("")) {
//                Set<Child> children = unionMember.getChildren();
//                for (Child child : children) {
//                    parentsChildren.add(new ParentChild(unionMember, child));
//                }
//            }
//        }
//        return parentsChildren;
//    }
//
//    public List<PublicOrgUnionMember> getPublicOrgUnionMemberList() {
//        List<PublicOrganization> publicOrganizations = courierService.readAll();
//        List<PublicOrgUnionMember> publicOrgUnionMemberList = new ArrayList<>();
//        for (PublicOrganization publicOrganization : publicOrganizations) {
//            Set<UnionMember> unionMembers = publicOrganization.getUnionMembers();
//            for (UnionMember unionMember : unionMembers) {
//                publicOrgUnionMemberList.add(new PublicOrgUnionMember(publicOrganization, unionMember));
//            }
//        }
//        return publicOrgUnionMemberList;
//    }
//
//    public PhoneNumber savePhoneNumber(UnionMember unionMember) {
//        return productService.create(unionMember.getPhoneNumbers().get(0));
//    }

}
