package no.hvl.dat250.jpa.basicexample;

import no.hvl.dat250.jpa.models.creditcard.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        // insertPerson();
        printPerson();
    }

    private static void insertTodo() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todoList = q.getResultList();
        for (Todo todo : todoList) {
            System.out.println(todo);
        }
        System.out.println("Size: " + todoList.size());
        em.getTransaction().begin();
        Todo todo = new Todo();
        todo.setSummary("This is a test");
        todo.setDescription("This is a test");
        em.persist(todo);
        em.getTransaction().commit();

        em.close();
    }

    private static void insertPerson() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person();
        person.setName("Max Mustermann");

        Address address = new Address();
        address.setStreet("Inndalsveien");
        address.setNumber(28);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        person.setAddress(addresses);

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        address.setPersons(personList);

        Pincode pincode = new Pincode();
        pincode.setPincode("123");
        pincode.setCount(1);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setNumber(12345);
        creditCard1.setBalance(-5000);
        creditCard1.setLimit(-10000);
        creditCard1.setPincode(pincode);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(12345);
        creditCard2.setBalance(-5000);
        creditCard2.setLimit(-10000);
        creditCard2.setPincode(pincode);

        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(creditCard1);
        creditCards.add(creditCard2);

        person.setCreditCards(creditCards);

        Bank bank = new Bank();
        bank.setName("Pengebank");
        bank.setCreditCards(creditCards);

        creditCard1.setBank(bank);
        creditCard2.setBank(bank);

        em.persist(person);
        em.persist(address);
        em.persist(pincode);
        em.persist(creditCard1);
        em.persist(creditCard2);
        em.persist(bank );

        em.getTransaction().commit();

        em.close();
    }

    private static void printPerson() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select p from Person p where p.name = 'Max Mustermann'");
        List<Person> personList = q.getResultList();
        for (Person person : personList) {
            person.getCreditCards().isEmpty();
            person.getAddress().isEmpty();
            System.out.println(person);
        }
    }
}
