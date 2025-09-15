package iuh.fit.dangkhoinguyen.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private int id;
    private String model;
    private String description;
    private int quantity;
    private double price;
    private String imageUrl;
}
