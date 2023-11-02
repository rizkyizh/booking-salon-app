package com.booking.repositories;

import java.util.Arrays;
import java.util.List;

import com.booking.helper.MembershipNameEnum;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Membership;
import com.booking.models.Person;

public class PersonRepository {

    private List<Person> personList;

    private PersonRepository() {
        initialPersonDataDummy();
    }

    public List<Person> getAll(){
        return personList;
    }

    private void initialPersonDataDummy() {
        if (personList == null) {
            Membership member1 = new Membership("Mem-01", MembershipNameEnum.NONE);
            Membership member2 = new Membership("Mem-02", MembershipNameEnum.SILVER);
            Membership member3 = new Membership("Mem-03", MembershipNameEnum.GOLD);

            Person customer1 = Customer.builder()
                    .id("Cust-01")
                    .name("Budi")
                    .address("Bandung")
                    .member(member1)
                    .wallet(1_000_000)
                    .build();

            Person customer2 = Customer.builder()
                    .id("Cust-02")
                    .name("Aceng")
                    .address("Cimahi")
                    .member(member2)
                    .wallet(1_000_000)
                    .build();

            Person customer3 = Customer.builder()
                    .id("Cust-03")
                    .name("Nur")
                    .address("Garut")
                    .member(member3)
                    .wallet(1_000_000)
                    .build();

            Person customer4 = Customer.builder()
                    .id("Cust-04")
                    .name("Iwan")
                    .address("Sukabumi")
                    .member(member2)
                    .wallet(1_000_000)
                    .build();

            Person employee1 = Employee.builder()
                    .id("Emp-01")
                    .name("Jono")
                    .address("Bandung")
                    .experience(3)
                    .build();

            Person employee2 = Employee.builder()
                    .id("Emp-02")
                    .name("Joni")
                    .address("Cimahi")
                    .experience(1)
                    .build();

            Person employee3 = Employee.builder()
                    .id("Emp-03")
                    .name("Hana")
                    .address("Garut")
                    .experience(5)
                    .build();

            personList = Arrays.asList(customer1, customer2, customer3, customer4, employee1, employee2, employee3);

        }
    }

    private static PersonRepository INSTANCE;

    public static PersonRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new PersonRepository();
        }
        return INSTANCE;
    }
}
