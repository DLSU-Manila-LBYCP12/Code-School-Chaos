/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.myexception;


/**
 *
 * @author Administrator
 */
public class ListFullException extends RuntimeException{
    public ListFullException(String s){ 
        super(s);
    }//end constructor
} //end ListException