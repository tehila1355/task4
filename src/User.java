public class User {

    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isEstateBroker;
    private int posts;

    public User (String userName, String password ,String phoneNumber, boolean isEstateBroker){
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isEstateBroker = isEstateBroker;
    }

    public void addPosts() {
        this.posts++;
    }

    public void removePost () {
        this.posts--;
    }

    public String getUserName (){
        return this.userName;
    }

    public void setUserName (String userName){
        this.userName = userName;
    }

    public String getPassword (){
        return this.password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getPhoneNumber (){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public boolean isEstateBroker (){
        return this.isEstateBroker;
    }

    public int getPosts (){
        return this.posts;
    }

    public String toString (){
        String output = this.userName + " " + this.phoneNumber;
        if (this.isEstateBroker()){
            output += " (real estate broker).";
        }else {
            output += ".";
        }

        return output;

    }


}