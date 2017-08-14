/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.myexception;


/**
 *
 * @author NeilOliver
 */
public class QueueFullException extends RuntimeException{
    public QueueFullException(){
        super();
    }
     
    public QueueFullException(String message){
        super(message);
    }
     
    public QueueFullException(String message, Throwable cause){
        super(message, cause);
    }
}
