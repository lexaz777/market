package ru.zakharov.market.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "shop_order")
@Data
public class ShopOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "shopOrder", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<CartItem> cartItemList;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "address")
    private String address;

    @Column(name = "payway")
    private String payway;

    @Column(name = "status_id")
    private String status;

    @Override
    public String toString() {
        return "ShopOrder{" +
                "id=" + id +
                ", cartItemList=" + cartItemList +
                ", address='" + address + '\'' +
                ", payway='" + payway + '\'' +
                '}';
    }
}
