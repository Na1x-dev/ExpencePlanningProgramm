package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class TableContent {
    private List<String> tableHeader = new ArrayList<>();
    private List<List<String>> tableRowContent = new ArrayList<>();

    public TableContent() {
        tableHeader = new ArrayList<>();
        tableRowContent = new ArrayList<>();
    }

    public List<String> getTableHeader() {
        return tableHeader;
    }

    public List<List<String>> getTableRowContent() {
        return tableRowContent;
    }

    public void setTableRowContent(List<List<String>> tableRowContent) {
        this.tableRowContent = tableRowContent;
    }

    public void setTableHeader(List<String> tableHeader) {
        this.tableHeader = tableHeader;
    }
}
