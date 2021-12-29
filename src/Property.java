public class Property {

    private Address address;
    private int rooms;
    private int price;
    private int type;
    private boolean isForRent;
    private String rentOrSale;
    private int propertyNumber;
    private int floorNumber;
    private User advertiserUser;

    // כתובת
    //מספר חדרים,
    // מחיר
    //סוג,
    // האם להשכרה,
    //מספר הבית,
    //מספר הקומה,
    //המשתמש שפרסם את הנכס

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
            case 1 :
                output = "house building -regular apartment - ";
                break;
            case 2:
                output = "house building -penthouse - ";
                break;

            default:
                output = "Private house - ";
        }
        output += this.rentOrSale + ": " + this.rooms + " rooms";
        if (this.type != 3){
            output += ", floor " + this.floorNumber + ".";
        }else {
            output += ".";
        }
        output += "\n" + "Price: " + this.price + "₪." +
                "\n" + "Contact info: " + advertiserUser.getUserName() + " " + advertiserUser.getPhoneNumber();
        if (advertiserUser.isEstateBroker()){
            output += " (real estate broker).";
        }else {
            output += ".";
        }

        return output;

    }



}