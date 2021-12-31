import java.util.Scanner;

public class RealEstate {
    public final static int SIZE_PHONE_NUMBER = 10;
    private User[] users;
    private Property[] properties;
    private Address[] addresses;


    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];

        Address address1 = new Address("Tel Aviv", "HaGibor-HaAlmoni");
        Address address2 = new Address("Tel Aviv", "Rothschild");
        Address address3 = new Address("Tel Aviv", "Dizengoff");
        Address address4 = new Address("Herzelia", "Hatzedef");
        Address address5 = new Address("Ashkelon", "Kadesh");
        Address address6 = new Address("Ashkelon", "Dvor-HaNevi'a");
        Address address7 = new Address("Ashkelon", "Shai-Agnon");
        Address address8 = new Address("Ramat-Gan", "Ha-Yarden");
        Address address9 = new Address("Jerusalem", "Jaffa");
        Address address10 = new Address("Qiryat Shemona", "HaBanim");

        Address[] addressArray = {address1, address2, address3, address4, address5, address6, address7, address8, address9, address10};

        this.addresses = addressArray;

    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;
        String phoneNumber;
        int userChoiceIfAgent;

        boolean isStrongPassword;
        boolean userNameExist;
        boolean isValidPhoneNumber;
        boolean isEstateBroker = false;

        do {
            System.out.println("Please enter a user name: ");
            userName = scanner.nextLine();
            userNameExist = isUserNameExist(userName);
            if (userNameExist) {
                System.out.println("Username is already taken");
            }

        } while (userNameExist);


        do {
            System.out.println("Please enter a valid password: ");
            password = scanner.nextLine();
            isStrongPassword = isStrongPassword(password);
            if (!isStrongPassword) {
                System.out.println("The password is not strong");
            }

        } while (!isStrongPassword);


        do {
            System.out.println("Please enter a valid phone number: ");
            phoneNumber = scanner.nextLine();
            isValidPhoneNumber = isValidPhoneNumber(phoneNumber);
            if (!isValidPhoneNumber) {
                System.out.println("The phone number is incorrect");
            }

        } while (!isValidPhoneNumber);

        do {
            System.out.println("Are you an Estate Broker  or a regular user?" + "\n" + "if you are an Estate Broker enter 1 else enter 2");
            userChoiceIfAgent = scanner.nextInt();
        } while (userChoiceIfAgent != 1 && userChoiceIfAgent != 2);

        if (userChoiceIfAgent == 1) {
            isEstateBroker = true;
        }

        addUserToArray(userName, password, phoneNumber, isEstateBroker);

    }

    public User userLogIn() {
        System.out.println("--Logging in--");
        Scanner scanner = new Scanner(System.in);
        String userNameToCheck;
        String passwordToCheck;


        System.out.println("Enter your username: ");
        userNameToCheck = scanner.nextLine();
        System.out.println("Enter your password: ");
        passwordToCheck = scanner.nextLine();


        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if (currentUser.getUserName().equals(userNameToCheck) &&
                    currentUser.getPassword().equals(passwordToCheck)) {
                return currentUser;
            }
        }

        return null;

    }


    public boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);

        //boolean isPosted = false;
        String city;
        String street;
        int floor;
        int rooms;
        int propertyNumber;
        boolean forRent;
        int price;
        int type;


        if (mayPostMoreProperties(user)) {

            //רשימת ערים + בחירה
            System.out.println("you can post a new property" + "\n" + "please choose a city:");
            printCities();
            city = scanner.nextLine();
            if (!isCityMatches(this.addresses, city)) {
                System.out.println("this city isn't exists in our data");
                return false;
            }


            System.out.println("Streets list:");
            printStreets(city);
            System.out.println("Please Enter the street name:");
            street = scanner.nextLine();
            int theIndexOfStreetInAddresses = theIndexOfStreetInAddresses(street, city);
            if (theIndexOfStreetInAddresses == -1) {
                System.out.println("this street isn't exists in our data");
                return false;
            }

            //הכנסת סוג הנכס- דירה בבניין/פנטאהוז/בית פרטי
            type = propertyType();

            if (type == 1 || type == 2) {
                System.out.println("Please enter the Floor number:");
                floor = scanner.nextInt();
            } else {
                floor = -1;
            }


            rooms = propertyNumberOfRooms();


            //מספר הנכס, אלוהים יודע מה זה אומר
            System.out.println("What is the property number?");
            propertyNumber = scanner.nextInt();


            //אם להשכרה
            forRent = isForRent();
            int answer;


            do {
                System.out.println("What is the price?");
                price = scanner.nextInt();
            } while (price < 0);


            // העלאת מספר הפוסטים של היוזר באחד כי עד כה התבצעה העלאה מוצלחת
            user.addPosts();

            //הוספת הנכס למערך
            addPropertyToArray(addresses[theIndexOfStreetInAddresses], type, floor, rooms, propertyNumber, forRent, price, user);


            System.out.println("the property has successfully posted!");
            return true;


        } else {
            System.out.println("You have reached the limit of properties that you can post");
        }

        return false;
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);

        int counter = 0;
        int propertyToRemove;

        Property[] newPropertyArray = new Property[this.properties.length - 1];

        if (user.getPosts() != 0) {
            printMyProperties(user);

            do {
                System.out.println("Enter the property's number you want to remove: ");
                propertyToRemove = scanner.nextInt();
            }while (propertyToRemove > user.getPosts());


            for (int i = 0; i < this.properties.length ; i++) {
                Property currentProperty = properties[i];
                if (currentProperty.getAdvertiserUser().equals(user)) {
                    counter++;
                }
                if (counter < propertyToRemove){
                    newPropertyArray[i] = this.properties[i];
                }
                if (counter > propertyToRemove) {
                    newPropertyArray[i - 1] = this.properties[i];
                }
                if (counter == propertyToRemove) {
                    counter++;
                }
            }

            this.properties = newPropertyArray;
            user.removePost();
            System.out.println("The property has been deleted");

        } else {
            System.out.println("You have not posted any property so far");
        }
    }


    public void printAllProperties() {
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(this.properties[i] + "\n");
        }
    }

    public void printMyProperties(User user) {
        int counter = 1;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getAdvertiserUser() == user) {
                System.out.println(counter);
                System.out.println(this.properties[i] + "\n");
                counter++;
            }
        }
    }

    public Property[] search() {
        Scanner scanner = new Scanner(System.in);
        Property[] propertiesWithFilter = new Property[this.properties.length];

        int type;
        int roomsNumber;
        int minPrice;
        int maxPrice;
        int answerRentOrSale;
        int counter = 0;
        int thePropertyIsForRent;

        do {
            System.out.println("is the property for rent or sale?");
            System.out.println("Enter 1 - for rent" + "\n" + "Enter 2 -for sale");
            answerRentOrSale = scanner.nextInt();
        } while (answerRentOrSale != 1 && answerRentOrSale != 2 && answerRentOrSale != -999);


        do {
            System.out.println("What is the Property type?");
            System.out.println("1- house building -regular apartment");
            System.out.println("2- house building -penthouse");
            System.out.println("3- Private house");
            type = scanner.nextInt();
        } while (type != 1 && type != 2 && type != 3 && type != -999);


        do {
            System.out.println("Please enter the Rooms number:");
            roomsNumber = scanner.nextInt();
        } while ((roomsNumber < 0 && roomsNumber > -999) || roomsNumber < -999);


        do {
            System.out.println("Please enter the min price: ");
            minPrice = scanner.nextInt();
            System.out.println("Please enter the max price: ");
            maxPrice = scanner.nextInt();
            if (minPrice >= maxPrice && minPrice > 0) {
                System.out.println("The range you entered is not correct...");
            }
        } while (minPrice >= maxPrice && minPrice > 0);

        for (int i = 0; i < this.properties.length; i++) {
            Property currentProperty = this.properties[i];
            if (currentProperty.isForRent()) {
                thePropertyIsForRent = 1;
            } else {
                thePropertyIsForRent = 2;
            }
            if (thePropertyIsForRent == answerRentOrSale || answerRentOrSale == -999) {
                if (currentProperty.getRooms() == roomsNumber || roomsNumber == -999) {
                    if (currentProperty.getType() == type || type == -999) {
                        if (currentProperty.getPrice() >= maxPrice || maxPrice == -999) {
                            if (currentProperty.getPrice() <= minPrice || minPrice == -999) {
                                propertiesWithFilter[counter] = currentProperty;
                                counter++;
                            }
                        }
                    }
                }

            }

        }
        return propertiesWithFilter;
    }


    private int propertyNumberOfRooms() {
        Scanner scanner = new Scanner(System.in);
        int roomsNumber;
        do {
            System.out.println("Please enter the Rooms number:");
            roomsNumber = scanner.nextInt();
        } while (roomsNumber < 0);
        return roomsNumber;
    }

    private int propertyType() {
        Scanner scanner = new Scanner(System.in);
        int type;
        do {
            System.out.println("What is the Property type?");
            System.out.println("1- house building -regular apartment");
            System.out.println("2- house building -penthouse");
            System.out.println("3- Private house");
            type = scanner.nextInt();
        } while (type < 1 || type > 3);
        return type;
    }

    private boolean isForRent() {
        Scanner scanner = new Scanner(System.in);
        int answer;
        boolean forRent;
        do {
            System.out.println("is the property for rent or sale?");
            System.out.println("type 1 - for rent" + "\n" + "type 2 -for sale");
            answer = scanner.nextInt();
        } while (answer != 1 && answer != 2);

        if (answer == 2) {
            forRent = false;
        } else {
            forRent = true;
        }
        return forRent;
    }


    private void printCities() {
        int length = this.addresses.length;

        Address[] copyAddresses = new Address[10];
        for (int i = 0; i < 10; i++) {
            copyAddresses[i] = this.addresses[i];
        }

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (copyAddresses[i].getCity().equals((copyAddresses[j].getCity()))) {
                    copyAddresses[j] = copyAddresses[length - 1];
                    length--;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.println(copyAddresses[i].getCity());
        }
    }

    private void printStreets(String cityName) {
        for (int i = 0; i < this.addresses.length; i++) {
            Address currentAddress = this.addresses[i];
            if (currentAddress.getCity().equals(cityName)) {
                System.out.println(currentAddress.getStreet());
            }

        }
    }

    private boolean isCityMatches(Address[] addresses, String textCity) {
        boolean isMatches = false;
        for (int i = 0; i < addresses.length; i++) {
            String currentCity = addresses[i].getCity();
            if (textCity.equals(currentCity)) {
                isMatches = true;
            }

        }
        return isMatches;
    }

    private int theIndexOfStreetInAddresses(String street, String city) {
        int indexInArray = -1;

        for (int i = 0; i < this.addresses.length; i++) {
            Address currentAddress = this.addresses[i];
            if (currentAddress.getCity().equals(city) && currentAddress.getStreet().equals(street)) {
                indexInArray = i;
            }
        }
        return indexInArray;
    }


    private boolean mayPostMoreProperties(User user) {
        boolean isAllowed = false;

        if (user.isEstateBroker() && user.getPosts() < 10) {
            isAllowed = true;

        } else if (!user.isEstateBroker() && user.getPosts() < 3) {
            isAllowed = true;
        }

        return isAllowed;

    }

    private boolean isUserNameExist(String userName) {
        boolean exist = false;
        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if (currentUser.getUserName().equals(userName)) {
                exist = true;
            }

        }
        return exist;
    }

    private boolean isStrongPassword(String password) {
        boolean strong = false;
        boolean hasChars = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasDigit = true;
            } else if (password.charAt(i) == '$' || password.charAt(i) == '%' || password.charAt(i) == '_') {
                hasChars = true;
            }

            if (hasChars && hasDigit) {
                strong = true;
                break;
            }
        }
        return strong;
    }

    private boolean isValidPhoneNumber(String phoneNumberToCheck) {
        boolean isCorrect = false;
        boolean validLength = false;
        boolean hasDigits = false;
        boolean isAreaCodeCorrect = false;

        if (phoneNumberToCheck.length() == SIZE_PHONE_NUMBER) {
            validLength = true;
        }

        for (int i = 0; i < phoneNumberToCheck.length(); i++) {
            char currentChar = phoneNumberToCheck.charAt(i);
            if (Character.isDigit(currentChar)) {
                hasDigits = true;
            } else {
                hasDigits = false;
                break;
            }
        }

        if (phoneNumberToCheck.charAt(0) == '0' && phoneNumberToCheck.charAt(1) == '5') {
            isAreaCodeCorrect = true;
        }

        if (validLength && hasDigits && isAreaCodeCorrect) {
            isCorrect = true;
        }

        return isCorrect;
    }


    private void addUserToArray(String userName, String password, String phoneNumber, boolean isEstateBroker) {
        User[] newArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newArray[i] = this.users[i];
        }
        User userToAdd = new User(userName, password, phoneNumber, isEstateBroker);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;

    }

    private void addPropertyToArray(Address addressForPost, int type, int floor, int rooms, int propertyNumber, boolean forRent, int price, User user) {
        Property[] newArray = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            newArray[i] = this.properties[i];
        }
        Property newProperty = new Property(addressForPost, type, floor, rooms, propertyNumber, forRent, price, user);
        newArray[this.properties.length] = newProperty;
        this.properties = newArray;

        System.out.println(newProperty);

    }


}