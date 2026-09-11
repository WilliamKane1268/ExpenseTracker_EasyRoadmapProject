package model;

import exception.ExpenseTrackerException;

import java.text.DateFormat;
import java.time.LocalDate;

public class Expense {
    private int id;
    private LocalDate date;
    private String description;
    private double amount;
    private String category;

    public String toCSV(){
        return id + "," + date + "," + description + "," + amount + ","  + category;
    }



    // Standard method (Cac ham tieu chuan)
    public Expense() {
    }

    public Expense(int id, LocalDate date, String description, double amount, String category) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Expense(int id, String description, double amount, String category) {
        this.id = id;
        this.date = LocalDate.now();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.isBlank()){
            throw new ExpenseTrackerException("Description cant be empty");
        }
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if(amount <= 0){
            throw new ExpenseTrackerException("Amount must be greater than 0");
        }
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(category == null || category.isBlank()){
            throw new ExpenseTrackerException("Category cant be empty");
        }
        this.category = category;
    }
}
