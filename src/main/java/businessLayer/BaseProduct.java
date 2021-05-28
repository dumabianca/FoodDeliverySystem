package businessLayer;

public class BaseProduct extends MenuItem {


    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title,rating,calories,proteins,fats,sodium,price);

    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public double getRating() {
        return rating;
    }
    @Override
    public int getCalories() {
        return calories;
    }
    @Override
    public int getProteins() {
        return proteins;
    }
    @Override
    public int getFats() {
        return fats;
    }
    @Override
    public int getSodium() {
        return sodium;
    }


    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "businessLayer.BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BaseProduct)) {
            return false;
        }
        BaseProduct b=(BaseProduct) o;
        if(this.getTitle().equals(b.getTitle()))
            return true;
        return false;
    }
    @Override
    public int hashCode()
    {
        return 1;
    }
}
