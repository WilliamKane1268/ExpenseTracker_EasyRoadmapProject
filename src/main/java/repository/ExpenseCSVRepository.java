package repository;

import model.Expense;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Need to improve in try catch (try using try-with-resources)


public class ExpenseCSVRepository implements ExpenseRepository{
    private static final String FILE_PATH = "expenses.csv";

    public static Expense fromCSV(String s){
        String[] arr = s.split("~~");
        int id = Integer.parseInt(arr[0]);
        LocalDate date = LocalDate.parse(arr[1]);
        String description = arr[2];
        double amount = Double.parseDouble(arr[3]);
        String category = arr[4];
        return new Expense(id,date,description,amount,category);
    }

    public static String toCSV(Expense expense){
        return expense.getId() + "~~" +
                expense.getDate() + "~~" +
                expense.getDescription() + "~~" +
                expense.getAmount() + "~~" +
                expense.getCategory();
    }

    public List<Expense> findAll(){
        List<Expense> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if(!file.exists()) return list;
        try{
            FileReader fr = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                if(line.isBlank()) continue;
                list.add(fromCSV(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list ;
    }
    public void saveAll(List<Expense> expenses){
        try{
            FileWriter fw = new FileWriter(FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Expense expense : expenses){
                bw.write(toCSV(expense));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Expense findById(int id){
        List<Expense> list = findAll();
        for(Expense expense : list){
            if(expense.getId() == id){
                return expense;
            }
        }
        return null;
    }
}
