package springapp.domain;

import java.io.Serializable;

public class Product implements Serializable {

    private String description;
<<<<<<< HEAD
    private Double prize;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrize() {
        return prize;
    }
    
    public void setPrize(Double price) {
        this.prize = prize;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + prize);
=======
    private Double price;
    
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
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
>>>>>>> refs/remotes/origin/master
        return buffer.toString();
    }
}
