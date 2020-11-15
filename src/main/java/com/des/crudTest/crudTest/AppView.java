package com.des.crudTest.crudTest;

public  class AppView {



    public static String menu(){
        return "\nMenu - Enter number to select option\n" +
                "1. Add Person" +
                "\n2. Edit Person" +
                "\n3. Delete Person" +
                "\n4. Add Address to Person" +
                "\n5. Edit Address" +
                "\n6. Delete Address" +
                "\n7. Count Number of Persons" +
                "\n8. List Persons" +
                "\n'exit' to close app";
    }

    public static String displayBreak(){
        return "\n\n\n";
    }
}
