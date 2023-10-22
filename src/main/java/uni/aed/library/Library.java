/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author omar
 */
public class Library {
    private AuthorList[] catalog=new AuthorList[(int)('Z'+1)];
    private PatronList[] people=new PatronList[(int)('Z'+1)];
    private String input;
    //Eficiencia para entradas principalmente de texto
    private BufferedReader buffer=new BufferedReader(
                                        new InputStreamReader(System.in));
    /**
     * Asigna catalog[] y people[] donde cada espacio estara aignado
     * por la letra inicial para una mayor eficiencia a la hora de la 
     * busqueda en las listas enlazadas de Author y Patron
     */
    public Library(){
        for(int i=0;i<=(int)'Z';i++){
            catalog[i]=new AuthorList();
            people[i]=new PatronList();
        }
    }
    private String getString(String msg){
        System.out.println(msg+" ");
        System.out.flush();
        try{
            input=buffer.readLine();
        }catch(IOException io){
        }
        return input.substring(0,1).toUpperCase()+input.substring(1);
    }
    private String getString2(String input){
        return input.substring(0,1).toUpperCase()+input.substring(1);
    }
    /**
     * nos muestra los libro que tiene la libreria y las personas que se encuentran
     * usando esta
     */
    private void status(){
        System.out.println("Library has the following books:\n");
        //recorre el array catalogo en caso no esta vacio nos muestra sus elementos
        for(int i=(int)'A';i<=(int)'Z';i++){
           if(!catalog[i].isEmpty()){
        //despliega el nombre del autor con sus respectivos libros       
               catalog[i].display();
           }
        }
        System.out.println("The following people are using the"
                + "library:\n");
        //recorre el array people en caso no esta vacio nos muestra sus elementos
        for(int i=(int)'A';i<=(int)'Z';i++){
           if(!people[i].isEmpty()){
        //nos da los datos del nombre de la persona y libros que esta usando       
               people[i].display();
           }
        }
    }
    /**
     * Se crea un nuevo objeto Author y se nos pide ingredar el nombre 
     * y titulo del libro, se configura para saber si el author ingresado
     * ya se encontraba en la lista de autores y simplemente agregar su libro
     * caso contrario agrega el libro al nuevo autor,finalmente add el nuevo
     * autor a la lista de autores
     */
    public void includeBook(String name,String title){
        Author newAuthor=new Author();
        int oldAuthor;
        Book newBook=new Book();
        newAuthor.name=getString2(name);
        newBook.title=getString2(title);
        //Busca si el autor es ya conocido
        oldAuthor=catalog[(int)
                newAuthor.name.charAt(0)].indexOf(newAuthor);
        //no se encontro que el autor sea parte de la lista catalog
        if(oldAuthor==-1){
            //agrega el libro a la lista de libros del nuevo autor
            newAuthor.books.add(newBook);
            //agrega el nuevo autor a las lista de autores en catalog[primeraLetra]
            catalog[(int)newAuthor.name.charAt(0)].add(newAuthor);         
        }
        //se encontro autor
        else{
            //agrega libro a BookList de AuthorList
           ((Author)catalog[(int)newAuthor.name.charAt(0)].get(oldAuthor)).
                   books.add(newBook);
           
        }
    }
    /**
     * Pide prestado un libro para un patron donde se inspecciona si el libro buscado 
     * se encuentra , una vez logrado esto mediante cabeceras se rellenas los valores
     * para prestar de las clases Book(public->patron),Patron(BookLis->books)
     * ademas se añade si el patron recien ingresa o si ya ha ingresado anteriormente
     * en patronList
     */
    public void checkOutBook(String patronN,String authorN,String titleN){
        Patron patron=new Patron() , patronRef;
        Author author=new Author(), authorRef=new Author();
        Book book=new Book();
        //Iniciamos index en -1
        int patronIndex,bookIndex=-1,authorIndex=-1;
        patron.name=getString2(patronN);
        
        //mientras el autor ingresado no se encuentre repetiremos while caso contrario
        //obtenemos el indes del autor buscado
        while(authorIndex==-1){
            author.name=getString2(authorN);
            //Busca en AuthorList si se encuentra el nombre autor ingresado
            authorIndex=catalog[(int)author.name.charAt(0)].indexOf(author);
            
            //entra: el autor no se encontro
            if(authorIndex==-1){
                System.out.println("Misspelled author's name");                
            }
        }
        //mientras el libro ingresado no se encuentre repetiremos while caso contrario
        //obtenemos el index del libro buscado        
        while(bookIndex==-1){
            book.title=getString2(titleN);
            //apuntamos al autor con cabecera authorRef, y este nos indica si el 
            //libro buscado se encuentra
            authorRef=((Author)catalog[(int)author.name.charAt(0)].get(authorIndex));
            bookIndex=authorRef.books.indexOf(book);
            //entra: libro no se encontro
            if(bookIndex==-1){
                System.out.println("Misspelled title");                
            }
        }
        //Apuntamos a libro buscado con la cabecera BookRef
        Book bookRef=(Book)authorRef.books.get(bookIndex);
        //Agregamos al checkOutBook apuntando a authorRef y bookRef
        CheckedOutBook bookToCheckOut=new CheckedOutBook();
        bookToCheckOut.author=authorRef;
        bookToCheckOut.book=bookRef;
        //buscamo index patron
        patronIndex=people[(int)
                patron.name.charAt(0)].indexOf(patron);
        //caso no encontrarlo asignamos a people[]
        if(patronIndex==-1){
            patron.books.add(bookRef);
            //agrega a people
            people[(int)patron.name.charAt(0)].add(patron);
            //atributo patron=null de libro apunta a primer patron ya que esta siendo
            //recientemente aignado siempre va ser el primero
            bookRef.patron=(Patron)people[(int)patron.name.charAt(0)].getFirst();         
        }
        else{
            patronRef=(Patron)people[(int)patron.name.charAt(0)].get(patronIndex);
            patronRef.books.add(bookToCheckOut);
            bookRef.patron=patronRef;
        }
    }
    /**
     * Busca si el patron, libro,autor ingresado existen una vez confirmado
     * nulifica para libro su estado patron y remueve de la lista books de patron
     * el libro ingresado
     */
    public void returnBook(String patronN,String autorN,String titleT){
        Patron patron=new Patron();
        Book book=new Book();
        Author author=new Author(),authorRef=new Author();
        int patronIndex=-1,bookIndex=-1,authorIndex=-1;
        //mientras el patron ingresado no se encuentre repetiremos while caso contrario
        //obtenemos el index del patron
        while(patronIndex==-1){
            patron.name=getString2(patronN);
            //Busca en AuthorList si se encuentra el nombre autor ingresado
            patronIndex=people[(int)patron.name.charAt(0)].indexOf(patron);
            
            //entra: el autor no se encontro
            if(patronIndex==-1){
                System.out.println("Patron's name misspelled");                
            }
        }
        //mientras el autor ingresado no se encuentre repetiremos while caso contrario
        //obtenemos el indes del autor buscado
        while(authorIndex==-1){
            author.name=getString2(autorN);
            //Busca en AuthorList si se encuentra el nombre autor ingresado
            authorIndex=catalog[(int)author.name.charAt(0)].indexOf(author);
            
            //entra: el autor no se encontro
            if(authorIndex==-1){
                System.out.println("Misspelled author's name");                
            }
        }
        while(bookIndex==-1){
            book.title=getString2(titleT);
            //apuntamos al autor con cabecera authorRef, y este nos indica si el 
            //libro buscado se encuentra
            authorRef=((Author)catalog[(int)author.name.charAt(0)].get(authorIndex));
            bookIndex=authorRef.books.indexOf(book);
            //entra: libro no se encontro
            if(bookIndex==-1){
                System.out.println("Misspelled title");                
            }
        }
        //Agregamos al checkOutBook apuntando a authorRef y bookRef
        CheckedOutBook cheekedToOutBook=new CheckedOutBook();  
        cheekedToOutBook.author=authorRef;
        cheekedToOutBook.book=(Book)authorRef.books.get(bookIndex);
        //vuelve null el atributo patron de libro
        ((Book)authorRef.books.get(bookIndex)).patron=null;
        //remueve de la lista de los libro patron
        System.out.println(((Patron)people[(int)patron.name.charAt(0)].get(patronIndex)).name);
        
        System.out.println(((Patron)people[(int)patron.name.charAt(0)].get(patronIndex)).books);
        
//        ((Patron)people[(int)patron.name.charAt(0)].get(patronIndex)).books.remove(cheekedToOutBook);
    }  
    public void run(){
        while(true){
            char option=getString("\nEnter one of the following"
                    + "options:\n"
                    + "1.Incluse a book in the catalogo"
                    + "2.Check out a book\n"
                    + "3.Return a book\n4.Status\n5"
                    + "Exit\n"
                    + "Your option:").charAt(0);
            switch(option){
                case'1':includeBook();break;
                case'2':checkOutBook();break;
                case'3':returnBook();break;
                case'4':status();break;
                case'5':return;
                default:System.out.println("Wrong option ,try again");
            }
        }
    }
    
    private void includeBook(){
        String name=getString("Enter author's name:");
        String title=getString("Enter the title of the book");
        this.includeBook(name, title);
    }
    private void checkOutBook(){
        String patronN=getString("Enter patron's name");
        String authorN=getString("Enter author's name");
        String titleN=getString("Enter the title of the book");
        this.checkOutBook(patronN, authorN, titleN);
    }
    private void returnBook(){
        String patronN=getString("Enter patron's name");
        String authorN=getString("Enter author's name");
        String title=getString("Enter the title of the book");
        this.returnBook(patronN, authorN, title);
    }
    public static void main(String[] args){
//        (new LibraryP()).run();
        Library library=new Library();
        library.includeBook("Fielding Hernry","The History of Tom Jones");
        library.includeBook("Fielding Hernry","Pasquin");
        library.includeBook("Fitzgerald Edward","Select Works");
        library.includeBook("Fitzgerald Edward","Euphranor");
        library.includeBook("Murdoch Iris","The Red and the Green");
        library.includeBook("Murdoch Iris","Sartre");
        library.includeBook("Murdoch Iris","The Bell");
        
        library.checkOutBook("Anais", "Fielding Hernry","The History of Tom Jones");
        library.checkOutBook("Floid", "Fielding Hernry","Pasquin");
        library.checkOutBook("Jeremy", "Fitzgerald Edward","Select Works");
        library.checkOutBook("Jeremy", "Fitzgerald Edward","Euphranor");
        library.checkOutBook("Sofia", "Murdoch Iris","Sartre");
        library.status();
        library.returnBook("Jeremy", "Fitzgerald Edward", "Select Works");
        library.returnBook("Sofia", "Murdoch Iris", "Sartre");  
        library.status();
        
        
    }
    
    
    public AuthorList[] getCatalog() {
        return catalog;
    }

    public PatronList[] getPeople() {
        return people;
    }

    
}
