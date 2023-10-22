/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

/**
 *
 * @author omar
 */
public class Patron {
    public String name;
    public BookList books=new BookList();
    public Patron(){
    }
    public boolean equals(Object node){
        return name.equals(((Patron) node).name);
    }
    public void display(){
        if(!books.isEmpty()){
            System.out.println(name +" has the following books:");
            books.display();
        }
        else System.out.println(name+" has no books");
    }
    
}
