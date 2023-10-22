/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

import uni.aed.linkedlistTDA.LinkedListTDA;



/**
 *
 * @author omar
 */
public class BookList extends LinkedListTDA{
    
    public BookList(){
        super();
    }
    public void display(){
        for(int i=0;i<size();i++)
            System.out.print(get(i));
    }
    
}
