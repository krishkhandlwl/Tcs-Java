package com.example.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private int eid;
    private String ename;
    private double esalary;

    public Employee(int eid, String ename, double esalary) {
        this.eid = eid;
        this.ename = ename;
        this.esalary = esalary;
    }
    public double getEsalary() {
        return esalary;
    }

    @Override
    public String toString() {
        return "Employee [EID=" + eid + ", Name=" + ename + ", Salary=" + esalary + "]";
    }
}

class EmployeeDataReader {
    public List<Employee> helper(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            int eid = (int) row.getCell(0).getNumericCellValue();
            String ename = row.getCell(1).getStringCellValue();
            double esalary = row.getCell(2).getNumericCellValue();

            employees.add(new Employee(eid, ename, esalary));
        }

        workbook.close();
        fis.close();

        return employees;
    }
    public double getAverage(List<Employee> employees) {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.getEsalary();
        }
        if (employees.isEmpty()) {
            return 0;
        }
        return totalSalary / employees.size();

    }
    
}

public class task {
    public static void main(String[] args) throws IOException {
        String path = "D:/filePath.xlsx"; 

        EmployeeDataReader info = new EmployeeDataReader();
        List<Employee> employees = info.helper(path);

        for (Employee employee : employees) {
            System.out.println(employee);
        }
        double average = info.getAverage(employees);
        System.out.println("Average Salary: " + average);
    }
}
