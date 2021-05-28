package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @Author: Duma Bianca
 * @Since: May 17, 2021
 * Abstract class which defines a menu Item. It is extende by businessLayer.BaseProduct and businessLayer.CompositeProduct
 */
public abstract class MenuItem implements Serializable {
    protected String title;
    protected double rating;
    protected int calories;
    protected int proteins;
    protected int fats;
    protected int sodium;
    protected int price;
    public MenuItem()
    {

    }
    public MenuItem(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super();
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price=price;
    }

    public abstract String getTitle() ;


    public  void setTitle(String title) {
        this.title = title;
    }

    public abstract double getRating() ;


    public void setRating(double rating) {
        this.rating = rating;
    }
    public abstract int getCalories() ;
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public abstract int getProteins() ;

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }
    public abstract int getFats() ;

    public void setFats(int fats) {
        this.fats = fats;
    }
    public abstract int getSodium() ;
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    public abstract int getPrice() ;
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "businessLayer.MenuItem{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
    public abstract boolean equals(Object obj);

}
