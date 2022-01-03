public class Property {

    private static final int REGULAR_APARTMENT = 1;
    private static final int PENTHOUSE = 2;
    private static final int PRIVATE_HOUSE = 3;
    private Address address;
    private int rooms;
    private int price;
    private int type;
    private boolean isForRent;
    private String rentOrSale;
    private int propertyNumber;
    private int floorNumber;
    private User advertiserUser;


    public Property (Address address, int type , int floorNumber, int rooms, int propertyNumber, boolean isForRent, int price,User advertiserUser){
        this.address = address;
        this.type = type;
        this.floorNumber = floorNumber;
        this.rooms = rooms;
        this.propertyNumber = propertyNumber;
        this.isForRent = isForRent;
        this.price = price;
        this.advertiserUser =advertiserUser;

        if (isForRent){
            this.rentOrSale = "for rent";
        }else {
            this.rentOrSale = "for sale";
        }

    }

    public int getType (){
        return this.type;
    }

    public int getPrice () {
        return this.price;
    }

    public boolean isForRent (){
        return this.isForRent;
    }

    public User getAdvertiserUser (){
        return this.advertiserUser;
    }

    public Address getAddress () {
        return this.address;
    }

    public int getRooms () {
        return this.rooms;
    }

    public String toString (){
        String output = "";
        switch (this.type){
            case REGULAR_APARTMENT:
                output = "house building -regular apartment - ";
                break;
            case PENTHOUSE:
                output = "house building -penthouse - ";
                break;

            default:
                output = "Private house - ";
        }
        output += this.rentOrSale + ": " + this.rooms + " rooms";
        if (this.type != PRIVATE_HOUSE){
            output += ", floor " + this.floorNumber + ".";
        }else {
            output += ".";
        }
        output += "\n" + "Price: " + this.price + "₪." +
                "\n" + "Contact info: " + advertiserUser.toString();

        return output;

    }



}