package springapp.domain;

import java.io.Serializable;

public class Product implements Serializable {

    private String description;
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
        return buffer.toString();
    }
}