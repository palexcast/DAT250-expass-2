package no.hvl.dat250.jpa.models.creditcard;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany()
    private List<CreditCard> creditCards = new ArrayList<>();
    @ManyToMany
    private List<Address> address = new ArrayList<>();
}
