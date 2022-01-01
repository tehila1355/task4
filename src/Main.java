import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int userChoice1;
        int userChoice2;
        Scanner scanner = new Scanner(System.in);

        RealEstate realEstate = new RealEstate();
        do {
            do {
                System.out.println("To sign up enter 1 to Log-in enter 2 and to Exit enter 3");
                userChoice1 = scanner.nextInt();

            }while (userChoice1 > 3 || userChoice1 <= 0);

            if (userChoice1 == 1) {
                realEstate.createUser();

            }if (userChoice1 == 2){
                User loggedUser = realEstate.userLogIn();

                if (loggedUser == null) {
                    System.out.println("Login failed, The username or password is incorrect");
                }else {
                    do {
                        System.out.println("Enter your choice: " +
                                "\n" + "1- Post a new property" +
                                "\n" + "2- Remove property posting" +
                                "\n" + "3- View all property in the system" +
                                "\n" + "4- View all properties posted by you" +
                                "\n" + "5- Search for properties by parameters" +
                                "\n" + "6- Disconnect and return to the main menu");
                        userChoice2 = scanner.nextInt();
                    }while (userChoice2 > 6 || userChoice2 < 1);


                    switch (userChoice2) {
                        case 1:
                            realEstate.postNewProperty(loggedUser);
                            break;
                        case 2:
                            realEstate.removeProperty(loggedUser);
                            break;
                        case 3:
                            realEstate.printAllProperties();
                            break;
                        case 4:
                            realEstate.printMyProperties(loggedUser);
                            break;
                        case 5:
                            Property [] printProperties = realEstate.search();
                            for (int i = 0; i < printProperties.length; i++) {
                                System.out.println(printProperties[i]);
                            }
                            break;
                        case 6:
                            break;
                    }
                }


            }
        }while (userChoice1 != 3);
    }

//    1 – ליצור חשבון
//2 – להתחבר לחשבון קיים
//3 – לסיים את התוכנית


//        1 – לפרסם נכס חדש
//        2 – להסיר פרסום על נכס
//        3 – להציג את כל הנכסים במערכת
//        4 – להציג את כל הנכסים שפורסמו על ידי המשתמש
//        5 – לחפש נכס לפי פרמטרים
//        6 – להתנתק ולחזור לתפריט הראשי






}





