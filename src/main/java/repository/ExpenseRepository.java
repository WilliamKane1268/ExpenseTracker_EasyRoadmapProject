package repository;

import model.Expense;

import java.util.List;

public interface ExpenseRepository {
    public List<Expense> findAll();
    public void saveAll(List<Expense> expenses);
    public Expense findById(int Id);

}
