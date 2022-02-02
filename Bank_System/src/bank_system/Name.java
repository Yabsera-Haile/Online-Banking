package bank_system;

import java.util.Scanner;

public class Name
{
    protected String first_name, middle_name, last_name;
    public void setName()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter First Name: ");
        this.first_name=sc.nextLine();
        System.out.print("Enter Middle Name: ");
        this.middle_name=sc.nextLine();
        System.out.print("Enter Last Name: ");
        this.last_name=sc.nextLine();
    }

}