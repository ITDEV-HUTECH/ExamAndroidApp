package com.example.libs.Model;

import java.util.List;
import java.util.UUID;

public class GetProductCallBackModel {
    private boolean status;
    private String message;
    private List<Product> data;

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public List<Product> getData() { return data; }
    public void setData(List<Product> value) { this.data = value; }
}

// Datum.java



