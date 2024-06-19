public class Employee {
    private String name;
    private double salary;
    private int workHours;
    private int hireYear;
    private final double TAX_PERCENTAGE;
    private final double BONUS_FEE;
    private final int YEAR;
    Employee(String name, double salary, int workHours, int hireYear){
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
        TAX_PERCENTAGE = 0.03;
        BONUS_FEE = 30;
        YEAR = 2021;
    }
    public double tax(){
        if (this.salary > 1000){
            return (this.salary * TAX_PERCENTAGE);
        }
        else {
            return 0;
        }
    }
    public double bonus(){
        if (this.workHours > 40){
            return ((this.workHours - 40) * BONUS_FEE);
        }else {
            return 0;
        }
    }
    public double raiseSalary(){
        if (YEAR - this.hireYear < 10){
            return (this.salary * 0.05);
        }else if ((YEAR - this.hireYear > 9) && (YEAR - this.hireYear < 20)){
            return (this.salary * 0.10);
        } else if ((YEAR - this.hireYear > 19)){
            return (this.salary * 0.15);
        }else {
            return 0;
        }
    }
    public String toString(){
        return "Employee's Name: " + this.name +
                "\nEmployee's Salary: " + this.salary +
                "\nEmployee's Working Hours: " + this.workHours +
                "\nYear The Employee Started Work: " + this.hireYear +
                "\nTax: "+ tax() +
                "\nBonus Fee: " + bonus() +
                "\nRaise Salary: " + raiseSalary() +
                "\nSalary along with Taxes and Bonuses: " + (salary + bonus() - tax()) +
                "\nEmployee's Total Salary: " + (salary + bonus() + raiseSalary() - tax());
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }
}
