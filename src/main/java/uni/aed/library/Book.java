/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

/**
 *
 * @author omar
 */
public class Book {
    public String title;
    public Patron patron=null;
    public Book(){
    }
    public boolean equals(Object node){
        return title.equals(((Book) node).title);
    }
    public String toString(){
        return "    *   "+title+
                (patron!=null?" -checked out to "+patron.name:"")+
                "\n";
    }
    
}
