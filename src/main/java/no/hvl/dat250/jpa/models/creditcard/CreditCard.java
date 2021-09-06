package no.hvl.dat250.jpa.models.creditcard;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private int limit;
    private int balance;
    @ManyToOne
    private Bank bank;
    @OneToOne
    private Pincode pincode;
}
