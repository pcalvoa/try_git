package springapp.domain;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String description;
    private Double price;
    
    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n   Id: " + id);
        buffer.append("\n   Description: " + description);
        buffer.append("\n   Price: " + price);
        return buffer.toString();
    }
}