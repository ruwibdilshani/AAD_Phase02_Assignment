package lk.ijse.gdse.aad.posusingspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderDetails")
@Entity
public class OrderDetails implements SuperEntity{
    @Id
    private String orderDetailsId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "itemCode")
    private Item item;
    private int qty;
    private double unitPrice;
    private String description;
}
