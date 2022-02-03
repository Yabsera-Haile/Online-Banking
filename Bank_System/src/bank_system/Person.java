package bank_system;

import java.util.Scanner;

public class Person {
    Scanner sc = new Scanner(System.in);
    private Name full_name = new Name();
    private DateList DoB = new DateList();
    private String sex, email, tel;

    public String setPerson(String fname, String mname, String lname, int d, int m, int y, String sex, String email,
            String tel) {
        this.email = email;
        this.tel = tel;
        this.sex = sex;
        this.DoB.setDateList(d, m, y);
        return this.full_name.setName(fname, mname, lname);

    }

    public void setFull_name(Name full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return DoB.getAge();
    }

    public Name getName() {
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
