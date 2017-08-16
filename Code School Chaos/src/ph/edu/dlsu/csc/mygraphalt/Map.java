/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;
import java.io.Serializable;
import java.lang.reflect.Field;//optional,for toString shortcut
import ph.edu.dlsu.csc.myexception.MapUnsyncException;

import java.util.LinkedList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
//Fp, this assumes each key appears only once
public class Map<K,V> implements Serializable{//one dimensional mapper
    private static final long serialVersionUID = 1L;
    private LinkedList<K> keys=new LinkedList<>();
    private LinkedList<V> values=new LinkedList<>();
    
        
    public Map(){//constructor
        keys=new LinkedList<>();
        values=new LinkedList<>();
        chkIntegrity("map");
    }
    
    //other methods
    public boolean add(K key, V value){
        if(keys.contains(key)){
            return false;
        }
        boolean a=keys.add(key);
        boolean b=values.add(value);
        chkIntegrity("add");
        return a&&b;
    }
    public boolean removeByK(K key){
        int i=keys.indexOf(key);
        if(i==-1){
            chkIntegrity("removebyk");
            return false;
        } else {
            keys.remove(i);
            values.remove(i);
            chkIntegrity("removebyk");
            return true;
        }
    }
    public boolean removeByV(V value){
        int i=values.indexOf(value);
        if(i==-1){
            chkIntegrity("removebyv");
            return false;
        } else {
            keys.remove(i);
            values.remove(i);
            chkIntegrity("removebyv");
            return true;
        }
    }
    public int indexOfK(K key){
        chkIntegrity("indexofk");
        return keys.indexOf(key);
    }
    public int indexOfV(V val){
        chkIntegrity("indexofv");
        return values.indexOf(val);
    }
    public void remove(int i){
        keys.remove(i);
        values.remove(i);
        chkIntegrity("remove");
    }
    public int size(){
        chkIntegrity("size");
        return keys.size();
    }
    public boolean isEmpty(){
        chkIntegrity("isempty");
        return size()==0;
    }
    
    public V getV(K key){
        int i=keys.indexOf(key);
        chkIntegrity("getv");
        if(i==-1){
            return null;
        }
        return values.get(i);
    }
    public K getK(V val){
        int i=values.indexOf(val);
        chkIntegrity("getk");
        if(i==-1){
            return null;
        }
        return keys.get(i);
    }
    
    public boolean setV(K key,V newValue){
        int i=keys.indexOf(key);
        chkIntegrity("getv");
        if(i==-1){
            return false;
        }
        values.remove(i);
        values.add(i,newValue);
        return true;
    }
    public boolean setK(V val,K newKey){
        int i=values.indexOf(val);
        chkIntegrity("getk");
        if(i==-1){
            return false;
        }
        keys.remove(i);
        keys.add(i,newKey);
        return true;
    }
    
    
    private void chkIntegrity(String mthdName){
        if(keys.size()!=values.size()){
            throw new MapUnsyncException("list sizes unsycnhronized:\n"
                    +"keys:"+ keys.size()+"values:"+values.size()+
                    "\n last method: "+mthdName+"\n"+this);
        }
    }
    
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
