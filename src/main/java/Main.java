import cli.CommandParser;
import repository.ExpenseCSVRepository;
import service.ExpenseService;

public class Main {
    public static void main(String[] args) {
        ExpenseCSVRepository expenseCSVRepository = new ExpenseCSVRepository();
        ExpenseService expenseService = new ExpenseService(expenseCSVRepository);
        CommandParser commandParser = new CommandParser(args, expenseService);
    }
}
