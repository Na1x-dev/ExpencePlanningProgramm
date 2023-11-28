package com.example.demo.controllers.sale;

import com.example.demo.models.*;
import com.example.demo.services.status.StatusService;
import com.example.demo.services.budget.CountryService;
import com.example.demo.services.category.CategoryService;
import com.example.demo.services.position.PositionService;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class SaleController {
    @Autowired
    PositionService positionService;
    @Autowired
    CountryService countryService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    StatusService statusService;
//    @Autowired
//    SupplyDetailService supplyDetailService;
    @Autowired
    UserService userService;

    @GetMapping({"/salesPage/index"})
    public String salesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("sales", positionService.readAll());
        return "salesPage/index";
    }

    @GetMapping({"/newSalePage/index"})
    public String newSale(Model model, Principal user) {
        Application newApplication = new Application();
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newSale", newApplication);
//        model.addAttribute("showBookList", getBookList());
//        model.addAttribute("bookAuthorList", getAuthorNameList(getBookList()));
//        model.addAttribute("bookPublisherList", getPublisherNameList(getBookList()));
//        model.addAttribute("bookLanguageList", getLanguageNameList(getBookList()));
//        for (int i = 0; i < getBookList().size(); i++) {
//            System.out.println(i + " - " + getBookList().get(i));
//        }
        return "newSalePage/index";
    }

    @PostMapping({"/newSalePage/index"})
    public String newSale(Model model, @ModelAttribute("newSale") Application newApplication, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        saveBook(newSupply.getSupplyDetails().get(0).getBook());
//        if (sellBooks(newApplication, model)) {
//            saveSale(newApplication);
            return "redirect:/salesPage/index";
//        }
//        else {
//            return "/newSalePage/index";
//        }
    }
//
//    private List<UnionMember> getBookList() {
//        List<UnionMember> unionMembers = bookService.readAll();
//        for (int i = 0; i < unionMembers.size(); ) {
//            if (unionMembers.get(i).getBooksAmount() < 1) {
//                unionMembers.remove(i);
//            } else {
//                i++;
//            }
//        }
//        return unionMembers;
//    }
//
//    private List<String> getAuthorNameList(List<UnionMember> unionMemberList) {
//        List<String> authorName = new ArrayList<>();
//        for (UnionMember unionMember : unionMemberList) {
//            authorName.add("\"" + unionMember.getPosition().getAuthorName() + "\"");
//        }
//        return authorName;
//    }
//
//    private List<String> getPublisherNameList(List<UnionMember> unionMemberList) {
//        List<String> publisherName = new ArrayList<>();
//        for (UnionMember unionMember : unionMemberList) {
//            publisherName.add("\"" + unionMember.getMaterialPayment().getPublisherTitle() + "\"");
//        }
//        return publisherName;
//    }
//
//    private List<String> getLanguageNameList(List<UnionMember> unionMemberList) {
//        List<String> languageName = new ArrayList<>();
//        for (UnionMember unionMember : unionMemberList) {
//            languageName.add("\"" + unionMember.getGroundsForFinPayment().getLanguageName() + "\"");
//        }
//        return languageName;
//    }
//
//    @PostMapping("/clientsPage/index/update/{id}")
//    public String updateClient(Model model, @ModelAttribute("updateClient") Child updateChild, Principal user, @PathVariable("id") Long clientId) {
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        Child child = clientService.readById(clientId);
//        updateClient(child, updateChild);
//        clientService.update(clientId, child);
//        return "redirect:/clientsPage/index";
//    }
//
//    @GetMapping("/clientsPage/index/delete/{id}")
//    public String deleteClient(Model model, Principal user, @PathVariable("id") Long clientId) {
//        clientService.delete(clientId);
//        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        return "redirect:/clientsPage/index";
//    }
//
//    public void updateClient(Child child, Child updateChild) {
//        child.setClientAddress(updateChild.getClientAddress().toLowerCase().trim());
//        child.setClientName(updateChild.getClientName().toLowerCase().trim());
//        child.setEmail(updateChild.getEmail().toLowerCase().trim());
//        child.setPublicOrganization(saveCity(updateChild.getPublicOrganization()));
//    }
//
//    public PublicOrganization saveCity(PublicOrganization publicOrganization) {
//        PublicOrganization somePublicOrganization = cityService.readByCityTitle(publicOrganization.getTitle());
//        if (somePublicOrganization == null)
//            return cityService.create(publicOrganization);
//        else
//            return somePublicOrganization;
//    }
//
//    public Child saveClient(Child child) {
//        child.setPublicOrganization((saveCity(child.getPublicOrganization())));
//        Child someChild = clientService.readByClientNameAndAddress(child.getClientName(), child.getClientAddress(), child.getPublicOrganization().getTitle(), child.getEmail());
//        if (someChild == null)
//            return clientService.create(child);
//        else {
//            //clientService.update(client.getClientId(), client);
//            return someChild;
//        }
//    }
//
//    public UnionMember saveBook(UnionMember unionMember) {
//        return bookService.readByBookTitleAndAuthorAndPublisherAndLanguage(unionMember.getTitle().toLowerCase().trim(), unionMember.getPosition().getAuthorName().toLowerCase().trim(), unionMember.getMaterialPayment().getPublisherTitle().toLowerCase().trim(), unionMember.getGroundsForFinPayment().getLanguageName().toLowerCase().trim());
//    }
//
//    private Boolean sellBooks(Application application, Model model) {
//        UnionMember unionMember = bookService.readByBookTitleAndAuthorAndPublisherAndLanguage(application.getUnionMember().getTitle().toLowerCase().trim(), application.getUnionMember().getPosition().getAuthorName().toLowerCase().trim(), application.getUnionMember().getMaterialPayment().getPublisherTitle().toLowerCase().trim(), application.getUnionMember().getGroundsForFinPayment().getLanguageName().toLowerCase().trim());
//        if (unionMember == null) {
//            model.addAttribute("bookError", "Такой книги нет в магазине");
//            return false;
//        }
//        if (unionMember.getBooksAmount() >= application.getAmount()) {
//            int j = 0;
//            SupplyDetail supplyDetail = unionMember.getSupplyDetails().get(j);
//            for (int i = 0; i < application.getAmount(); i++) {
//                supplyDetail.setAmount(supplyDetail.getAmount() - 1);
//                if (supplyDetail.getAmount() < 0) {
//                    j++;
//                    supplyDetailService.update(supplyDetail.getSupplyDetailId(), supplyDetail);
//                    supplyDetail = unionMember.getSupplyDetails().get(j);
//                }
//            }
//            return true;
//        } else {
//            model.addAttribute("bookError", "Такого количества данных книг нет на складе");
//            return false;
//        }
//    }
//
//    public Application saveSale(Application application) {
//        application.setUnionMember((saveBook(application.getUnionMember())));
//        application.setChild((saveClient(application.getChild())));
//        application.setPrice(application.getUnionMember().getBookPrice());
//        return saleService.create(application);
//    }
}
