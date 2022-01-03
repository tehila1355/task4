public class Address {
    private String city;
    private String street;

    public Address (String city, String street){
        this.city = city;
        this.street = street;
    }

    public String getCity (){
        return this.city;
    }

    public String getStreet (){
        return this.street;
    }

    public String toString (){
        return "city: " + this.city + "\n" + "street: " + this.street;
    }

}