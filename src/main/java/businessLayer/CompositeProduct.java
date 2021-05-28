package businessLayer;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class CompositeProduct extends MenuItem {
    public ArrayList<MenuItem> components = new ArrayList<MenuItem>();
    public CompositeProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price, ArrayList<MenuItem> components) {
        super(title,rating,calories,proteins,fats,sodium,price);
        this.components=components;
    }
    public CompositeProduct(ArrayList<MenuItem> components) {
        this.components=components;
        this.proteins=this.getProteins();
        this.fats=this.getFats();
        this.price=this.getPrice();
        this.sodium=this.getSodium();
        this.calories=this.getCalories();
        this.title=this.getTitle();
        this.rating=this.getRating();
    }
    @Override
    public String getTitle() {
        String compositeTitle="";
        for(MenuItem m: components)
        {
            compositeTitle=compositeTitle+m.getTitle();
        }
        return compositeTitle;
    }

    @Override
    public double getRating() {
        double compositeRating=0;
        for(MenuItem m: components)
        {
            compositeRating+=m.getRating();
        }
        return compositeRating/components.size();
    }
    public  void setTitle(String title) {
        this.title = title;
    }
    @Override
    public int getCalories() {
        int compositeCalories=0;
        for(MenuItem m: components)
        {
            compositeCalories+=m.getCalories();
        }
        return compositeCalories;
    }

    @Override
    public int getProteins() {
        int compositeProteins=0;
        for(MenuItem m: components)
        {
            compositeProteins+=m.getProteins();
        }
        return compositeProteins;
    }

    @Override
    public int getFats() {
        int compositeFats=0;
        for(MenuItem m: components)
        {
            compositeFats+=m.getFats();
        }
        return compositeFats;
    }

    @Override
    public int getSodium() {
        int compositeSodium=0;
        for(MenuItem m: components)
        {
            compositeSodium+=m.getSodium();
        }
        return compositeSodium;
    }

    @Override
    public int  getPrice() {
        int sum = 0;
        for (MenuItem m : this.components) {
            sum = sum + m.getPrice();
        }
        return sum;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return components;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.components = menuItems;
    }


    public void add(MenuItem newMenuItem) {
        this.components.add(newMenuItem);

    }

    public void remove(MenuItem toRemoveMenuItem) {
        int toDelete = -1;
        int i;
        for (i = 0; i < this.components.size(); i++) {
            System.out.println("component"+this.components.get(i));
            if (components.get(i).title.equals(toRemoveMenuItem.title) == true)
                toDelete = i;
        }
        if (toDelete != -1) {
            this.components.remove(toDelete);
        } else
            System.out.println("delete operation within businessLayer.CompositeProduct:Item not found");
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CompositeProduct)) {
            return false;
        }
        CompositeProduct b=(CompositeProduct) o;
        if(this.getTitle().equals(b.getTitle()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "businessLayer.CompositeProduct{" +
                "components=" + components +
                '}';
    }


}