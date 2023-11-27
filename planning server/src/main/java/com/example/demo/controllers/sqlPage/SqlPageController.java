package com.example.demo.controllers.sqlPage;

import com.example.demo.models.SqlRequest;
import com.example.demo.models.TableContent;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class SqlPageController {
    @Autowired
    UserService userService;
    SqlRequest mainSqlRequest;
    TableContent mainTableContent;
    private static Connection connection;

    @GetMapping({"/sqlPage/index"})
    public String sqlPageGet(Model model, Principal user) {
        if (mainSqlRequest == null) {
            mainSqlRequest = new SqlRequest();
            connection = getNewConnection();
        }
        if (mainTableContent == null) {
            mainTableContent = new TableContent();
        }
        model.addAttribute("tableContent", mainTableContent);
        model.addAttribute("sqlRequest", mainSqlRequest);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        return "sqlPage/index";
    }

    @PostMapping({"/sqlPage/index"})
    public String sqlPagePost(Model model, @ModelAttribute("sqlRequest") SqlRequest sqlRequest, @ModelAttribute("TableContent") TableContent tableContent) {
        mainSqlRequest = sqlRequest;
        executeUpdate(mainSqlRequest.getSqlString(), tableContent);
        mainTableContent = tableContent;
        model.addAttribute("tableContent", mainTableContent);
        model.addAttribute("sqlRequest", mainSqlRequest);
        return "redirect:/sqlPage/index";
    }

    private Connection getNewConnection() {
        FileInputStream fis;
        File file = new File("src/main/resources/application.properties");
        Properties property = new Properties();
        try {
            fis = new FileInputStream(file);
            System.out.println(file.getAbsolutePath());
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = property.getProperty("spring.datasource.url");
        String user = property.getProperty("spring.datasource.username");
        String password = property.getProperty("spring.datasource.password");
        System.out.println(url);
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeUpdate(String query, TableContent tableContent) {

        try {
            Statement statement = connection.createStatement();
            if (statement.execute(query)) {
                ResultSet resultSet = statement.getResultSet();

                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    tableContent.getTableHeader().add(resultSet.getMetaData().getColumnName(i));
                }

                while (resultSet.next()) {
                    List<String> tableColumnContent = new ArrayList<>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        tableColumnContent.add(resultSet.getString(i));
                    }
                    tableContent.getTableRowContent().add(tableColumnContent);
                }
                mainSqlRequest.setError("");
            } else {
                System.out.println("Успешно выполнено");
                mainSqlRequest.setError("Успешно выполнено");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            mainSqlRequest.setError(e.getMessage());
        }
//            System.out.println(result);
//            return result;

    }
}