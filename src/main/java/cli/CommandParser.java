package cli;

import exception.ExpenseTrackerException;
import model.Expense;
import service.ExpenseService;

public class CommandParser {
    private String[] input ;
    private ExpenseService expenseService;
    public CommandParser(String[] args, ExpenseService expenseService){
        input = args;
        if(args == null || args.length == 0) {
            throw new ExpenseTrackerException("Input isnt valid");
        }
        this.expenseService = expenseService;
        if(input[0].equals("add")){
            if(args.length != 7) throw new ExpenseTrackerException("Input not valid");
            expenseService.addExpense(args[2],Double.parseDouble(args[4]),args[6]);
        }else if(input[0].equals("delete")){
            System.out.println("# Expense deleted successfully");
            if(input[1].contains("id")){
                expenseService.deleteExpenseById(Integer.parseInt(input[2]));
            }else if (input[1].contains("description")){
                expenseService.deleteExpenseByDescription(input[2]);
            }
        }else if(input[0].equals("update")){
            expenseService.updateExpenseById(Integer.parseInt(input[2]), new Expense(Integer.parseInt(input[2]),input[4],Double.parseDouble(input[6]),input[8]));
        }else if(input[0].equals("summary")){
            if(input.length > 1) {
                System.out.println("# Total expense for: " + expenseService.getSummary(input[2]));
            }else{
                System.out.println("# Total expenses: " + expenseService.getSummary(null));
            }
        }else if(input[0].equals("list")){
            if(input.length > 1) {
                expenseService.displayExpense(input[2]);
            }else{
                expenseService.displayExpense(null);
            }
        }
    }
}
