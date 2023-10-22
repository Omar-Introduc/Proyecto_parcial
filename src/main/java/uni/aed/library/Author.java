/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

/**
 *
 * @author omar
 */
public class Author {
    public String name;
    public BookList books=new BookList();
    
    public Author(){
    }
    public boolean equals(Object node){
        return name.equals(((Author) node).name);
    }
    public void display(){
        System.out.println(name);
        books.display();
    }
}
