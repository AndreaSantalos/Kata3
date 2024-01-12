package Kata3;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.FileNotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Employee implements CSVReader,EmployeeLoader,GenderCounter,ShowerGenders{
    private String id;
    private String job;
    private String gender;

    public Employee(String id, String job, String gender) {
        this.id = id;
        this.job = job;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(job, employee.job) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, job, gender);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
    @Override
    public void csvread() throws FileNotFoundException {
        File f = new File("src/main/resources/employee_data.csv");
        Scanner s = new Scanner(f);
        ArrayList<String> atributes = new ArrayList<>(Arrays.asList(s.nextLine().replace("\uFEFF","").split(",")));
        while(s.hasNext()){
            String[] line = s.nextLine().split(",");
            employeeload(new Employee(line[atributes.indexOf("Id")],line[atributes.indexOf("Job")],line[atributes.indexOf("Gender")]));
        }
    }

    @Override
    public void employeeload(Employee e) {
        employees.add(e);
    }


    @Override
    public int femalecount() {

        return (int) employees.stream().filter(p -> p.getGender().equals("Female")).count();
    }

    @Override
    public int malecount() {
        return (int) employees.stream().filter(p -> p.getGender().equals("Male")).count();
    }

    @Override
    public void totalcount() {
        totalgenderemployees.put("Female",(Integer) femalecount());
        totalgenderemployees.put("Male",(Integer) malecount());
    }
    public void showemployees(){
        employees.stream().forEach(p -> System.out.println(p));
    }


    @Override
    public void showemployeesgender() {
        JFrame frame = new JFrame();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(femalecount(),"FemaleGender","Gender");
        dataset.addValue(malecount(),"MaleGender","Gender");
        JFreeChart chart = ChartFactory.createBarChart(
                "Gender in Business",
                "Gender",
                "Numbers",
                dataset
        );
        ChartPanel chartpanel = new ChartPanel(chart);
        frame.add(chartpanel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
