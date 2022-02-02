package bank_system;
import java.util.Scanner;
public class Person {
    Scanner sc=new Scanner(System.in);
    private Name full_name=new Name();
    private DateList DoB=new DateList();
    private String sex,email,tel;
    public void setPerson()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the Sex: ");
        this.sex = sc.nextLine();
        System.out.print("Enter the Email: ");
        this.email=sc.nextLine();
        System.out.print("Enter the Tel: ");
        this.tel = sc.nextLine();  
    }
    public void setFull_name(Name full_name) {
        this.full_name = full_name;
    }
    public int getAge()
    {
       return DoB.getAge();
    }
    public Name getName()
    {
        return full_name;
    }
    public DateList getDoB() {
        return DoB;
    }
    public String getsex() {
        return sex;
    }
    public String getEmail() {
        return email;
    }
    public String getTel() {
        return tel;
    }
}
