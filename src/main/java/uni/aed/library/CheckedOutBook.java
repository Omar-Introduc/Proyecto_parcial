/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

/**
 *
 * @author omar
 */
public class CheckedOutBook {
    public Author author=null;
    public Book book=null;
    public CheckedOutBook(){       
    }
    public boolean equals(Object node){
        return book.title.equals(((CheckedOutBook)node).book.title)
                && author.name.equals(((CheckedOutBook)node).author.name);
    }
    public String toString(){
        return "    *   "+author.name+", "+book.title+"\n";
    }
    
}
