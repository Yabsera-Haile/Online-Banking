package bank_system;

import java.util.Date;
import java.util.Scanner;

public class DateList {
    protected int day, month, year;
    public void setDateList(int d, int m, int y)
    {
        this.day=d;
        this.month=m;
        this.year=y;
    }  
    
    public int  getAge() {
        Date d = new Date();
 //       System.out.println((d.getYear() + 1900));
        int age = -(year-(d.getYear() + 1900));
        if (month > (d.getMonth() + 1))
            age++;
        if (month == d.getMonth())
            if (day >= d.getDate())
                age++;
        return age;
    }
    public void getCurrDate()
    {
        Date d = new Date();
        this.year=d.getYear()+1900;
        this.month=d.getMonth()+1;
        this.day=d.getDate();
    }
}
