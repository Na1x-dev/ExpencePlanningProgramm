package com.example.demo.models;

public class SqlRequest {
    private String sqlString;
    private String error;
    public SqlRequest(){
        sqlString = "";
        error = "";
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

