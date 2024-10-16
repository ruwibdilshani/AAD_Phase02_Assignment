package lk.ijse.gdse.aad.posusingspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class Customer implements SuperEntity {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}
