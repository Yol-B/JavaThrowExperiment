/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static javathrowexperiment.Accounts.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class Personal_Information_Menu {

    ArrayList<PersonalInformation> pi;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Personal_Information_Menu() {
        pi = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("Profile Infos : ");
        pi = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                pi.add(new PersonalInformation(Integer.parseInt(partsA[0]), Integer.parseInt(partsA[1]), partsA[2], partsA[3], partsA[4]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file has found!!!");
        }
    }

    public void create() throws IOException {
        String fname, lname, age;
        System.out.print("First Name : ");
        fname = input1.nextLine();
        while (!Check.isString(fname)) {
            System.out.println("First name must not be a number");
            create();
        }
        System.out.print("Last name : ");
        lname = input2.nextLine();
        while (!Check.isString(lname)) {
            System.out.println("Last name must not be a number.");
            create();
        }
        System.out.print("Age : ");
        age = input3.nextLine();
        while (Check.isString(age)) {
            System.out.println("Age must be a number!");
            create();
        }
        if (pi.isEmpty()) {
            pi.add(new PersonalInformation(1, account_id - 1, fname, lname, age));
        } else {
            pi.add(new PersonalInformation(pi.get(pi.size() - 1).getId() + 1, account_id, fname, lname, age));
        }
    }

    public void update(int acc_id) throws IOException {
        String fname = null, lname = null, age = null;
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                System.out.println("Profile Infos : ");
                System.out.println(pi.get(i).getId() + "\t" + pi.get(i).getAccount_id() + "\t" + pi.get(i).getFirstname() + "\t" + pi.get(i).getLasName() + "\t" + pi.get(i).getAge());
                System.out.print("\nFirst Name : ");
                fname = input1.next();
                while (!Check.isString(fname)) {
                    System.out.println("First name must not be a number");
                    update(acc_id);
                }
                System.out.print("Last Name : ");
                lname = input2.nextLine();
                while (!Check.isString(lname)) {
                    System.out.println("Last name must not be a number.");
                    update(acc_id);
                }
                System.out.print("Age : ");
                age = input2.next();
                while (Check.isString(age)) {
                    System.out.println("Age must be a number.");
                    update(acc_id);
                }
                pi.get(i).setFirstname(fname);
                pi.get(i).setLastName(lname);
                pi.get(i).setAge(age);
            } else if (pi.get(i).getAccount_id() != acc_id && i == pi.size() - 1 && fname == null && lname == null && age == null) {
                System.out.print("First Name : ");
                fname = input1.nextLine();
                while (!Check.isString(fname)) {
                    System.out.println("First name must not be a number.");
                    update(acc_id);
                }
                System.out.print("Last Name : ");
                lname = input2.nextLine();
                while (!Check.isString(lname)) {
                    System.out.println("Last name must not be a number.");
                    update(acc_id);
                }
                System.out.print("Age : ");
                age = input3.nextLine();
                while (Check.isString(age)) {
                    System.out.println("Age ust be a number.");
                    update(acc_id);
                }
                if (pi.isEmpty()) {
                    pi.add(new PersonalInformation(1, acc_id, fname, lname, age));
                } else {
                    pi.add(new PersonalInformation(pi.get(pi.size() - 1).getId() + 1, acc_id, fname, lname, age));
                }
                break;
            }
        }

    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            String str;
            writer.flush();
            for (PersonalInformation pi1 : pi) {
                str = pi1.getId() + "\t\t" + pi1.getAccount_id() + "\t\t" + pi1.getFirstname() + "\t\t" + pi1.getLasName() + "\t\t" + pi1.getAge();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("No file has found!!!");
        }
    }

    public void delete(int acc_id) {
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                pi.remove(i);
                System.out.println("Account ID " + acc_id + " is deleted!");
            }
        }
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getId() != i) {
                pi.get(i).setId(i + 1);
            }
            if (i == pi.size() - 1) {
                break;
            }
        }
    }

    public void search(int acc_id) {
        pi.stream().filter((pi1) -> (pi1.getAccount_id() == acc_id)).forEach((pi1) -> {
            System.out.println(pi1.getId() + "\t" + pi1.getAccount_id() + "\t" + pi1.getFirstname() + "\t" + pi1.getLasName() + "\t" + pi1.getAge());
        });
    }
}
