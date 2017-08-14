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
class ListEmptyException extends RuntimeException{
    public ListEmptyException(String s){ 
        super(s);
    }//end constructor
} 