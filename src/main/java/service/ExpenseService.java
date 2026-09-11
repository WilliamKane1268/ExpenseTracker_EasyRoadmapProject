package service;

import exception.ExpenseTrackerException;
import model.Expense;
import repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class ExpenseService {

    private  ExpenseRepository repository;
    private List<Expense> list ;
    public ExpenseService(ExpenseRepository repository){
        this.repository = repository;
        this.list = this.repository.findAll();
    }

    public void addExpense(String description, double amount, String catagory){
        if(list.isEmpty()) {
            list.add(new Expense(1, LocalDate.now(), description, amount, catagory));
        } else {
            list.add(new Expense(list.get(list.size() - 1).getId() + 1, LocalDate.now(), description, amount, catagory));
        }
        System.out.println("# Expense added successfully (ID: " + list.get(list.size() - 1).getId() + ")");
        this.repository.saveAll(list);
    }

    private void deleteExpenseByCondition(Predicate<Expense> condition, String notFoundMessage){
        boolean isRemoved = list.removeIf(condition);
        if(!isRemoved){
            throw new ExpenseTrackerException(notFoundMessage);
        }
        this.repository.saveAll(list);
    }

    public void deleteExpenseById(int id){
        deleteExpenseByCondition(expense -> expense.getId() == id, "Id not found");
    }

    public void deleteExpenseByDescription(String description){
        deleteExpenseByCondition(expense -> expense.getDescription().equalsIgnoreCase(description) , "Description not found");
    }

    public void updateExpenseById(int id, Expense newExpense){
        boolean found = false;
        for (Expense expense : list) {
            if (expense.getId() == id) {
                expense.setDescription(newExpense.getDescription());
                expense.setAmount(newExpense.getAmount());
                expense.setCategory(newExpense.getCategory());
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ExpenseTrackerException("Id not found: " + id);
        }
        this.repository.saveAll(list);
    }

    public double getSummary(String month){
        double sum = 0;
        if(month == null || month.isBlank()){
            for(Expense expense: list){
                sum += expense.getAmount();
            }
        }else{
            for(Expense expense: list){
                if(expense.getDate().getMonthValue() == Integer.parseInt(month) && LocalDate.now().getYear() == expense.getDate().getYear()){
                    sum+= expense.getAmount();
                }
            }
        }
        return sum;
    }

    public void displayExpense(String month){
        System.out.println("#ID  Date        Description   Amount");
        if(month == null || month.isBlank()){
            for(Expense expense : list) {
                System.out.printf("%-4d %-12s %-15s $%.2f%n",
                        expense.getId(),
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getAmount());
            }
        }else{
            for(Expense expense: list){
                if(expense.getDate().getMonthValue() == Integer.parseInt(month) && LocalDate.now().getYear() == expense.getDate().getYear()){
                    System.out.printf("%-4d %-12s %-15s $%.2f%n",
                            expense.getId(),
                            expense.getDate(),
                            expense.getDescription(),
                            expense.getAmount());
                }
            }
        }
    }


}
