/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

import java.util.Iterator;
import uni.aed.linkedlistTDA.LinkedListTDA;
import uni.aed.listTDA.IteratorTDA;

/**
 *
 * @author omar
 */
public class PatronList extends LinkedListTDA{
    public PatronList(){
        super();
    }
    public void display(){
        
//        for(java.util.Iterator it=iterator();it.hasNext();)
//            ((Patron)it.next()).display();;
        IteratorTDA iterador = this.iterator();
        while (iterador.hasNext()) {
            ((Patron)iterador.next()).display();
            }
    }


}