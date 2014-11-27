package com.ts.beans;

public class UserBean {
	
	private String name;
    private String email;
    private String address;
    private String password;
    private String userId;
    private int age;
   
    public int getAge(){
        return (this.age);
    }

      public void setAge(int age){
        this.age = age;
    }
      
    public String getUserId(){
        return fixNull(this.userId);
    }

      public void setUserId(String userId){
        this.userId = userId;
    }
     
    public String getPassword(){
          return fixNull(this.password);
      }

        public void setPassword(String password){
          this.password = password;
      }
    

    public String getName(){
        return fixNull(this.name);
    }

      public void setName(String name){
        this.name = name;
    }

   
    public String getEmail(){
        return fixNull(this.email);
    }


    public void setEmail(String email){
        this.email = email;
    }
    
    public String getAddress(){
        return fixNull(this.address);
    }

    
    public void setAddress(String address){
        this.address = address;
    }

    private String fixNull(String in){
        return (in == null) ? "" : in;
    }

  
}
