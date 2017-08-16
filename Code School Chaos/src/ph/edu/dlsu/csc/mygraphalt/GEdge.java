package ph.edu.dlsu.csc.mygraphalt;

/* © 2017 by Patrick Matthew Chan */

import java.io.Serializable;
import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class GEdge implements Serializable {
    private static final long serialVersionUID = 1L;
    public int wt=1;//weight
    public String desc="";
        
    public GEdge(){//constructor
        wt=1;//unweighted default 1
        desc="";
    }
    public GEdge(int weight){//constructor
        wt=weight;
        desc="";
    }
    public GEdge(String description){//constructor
        wt=1;
        desc=description;
    }
    public GEdge(int weight,String description){//constructor
        wt=weight;
        desc=description;
    }
    
    //other methods
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString shortcut">
    //++toString shortcut
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Field f: getClass().getDeclaredFields()) {
            try {
            result
            .append(f.getName())
            .append(" : ")
            .append(f.get(this))
            .append(System.getProperty("line.separator"));
            }
            catch (IllegalStateException ise) {
                result
                .append(f.getName())
                .append(" : ")
                .append("[cannot retrieve value]")
                .append(System.getProperty("line.separator"));
            }
            // nope
            catch (IllegalAccessException iae) {}
        }
        return result.toString();
    }
    // </editor-fold>
}
