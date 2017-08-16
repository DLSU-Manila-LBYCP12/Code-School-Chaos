/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;
import acm.graphics.GImage;
import acm.util.ErrorException;
import java.awt.Image;
import java.io.Serializable;
import java.lang.reflect.Field;//optional,for toString shortcut
import java.util.Arrays;
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Profile  implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name="";
    public transient GImage img=null;//this cannot be serialized
    public int[][] imgArr;//only this is serialzed instead
    public String imgPath="";//this is also serialized
    public String status="";
    
    
    
    public Profile(){//constructor
        this("");
    }
    public Profile(String name){//constructor
        this.name=name;
        status="";
        img=getImage();
        }
    
    //other methods
    //only for compatibility
    /** This method returns the name associated with the profile. */ 
    public String getName() {
        return name;
    }
    /** 
     * This method returns the image associated with the profile.  
     * If there is no image associated with the profile, the method
     * returns null. */ 
    public GImage getImage() {
        if(img==null){
            if(imgPath.length()>0){
                try{
                    img=new GImage(imgPath);
                } catch (ErrorException e){
                    img=null;
                    System.out.println("image not found in:\n"+imgPath);
                }
                if(imgArr!=null){
                    if(img==null || !Arrays.deepEquals(img.getPixelArray(), imgArr)){
                        if(img==null){
                            System.out.println("null kasi");
                        } else {
                            System.out.println("unequal kasi");
                        }
            img=new GImage(imgArr);
            System.out.println("image recreated.");
        }
                }
            } else if (imgArr!=null){
                img=new GImage(imgArr);
                System.out.println("path blank. image recreated.");
            }
        }
        return img;
    }
    /** This method sets the image associated with the profile. */ 
    public void setImage(GImage image,String path) {
        img=image;
        if(image==null){
            imgArr=null;
            imgPath="";
        } else {
        imgArr=img.getPixelArray();
            System.out.println("image pixel-set (just in case).");
            if(path==null){
                imgPath="";
            } else {
                imgPath=path;
    }
        }
        
    }
    public void setImage(GImage image) {
        setImage(image,null);
    }
    /** 
     * This method returns the status associated with the profile.
     * If there is no status associated with the profile, the method
     * returns the empty string ("").
     */ 
    public String getStatus() {
        return status;
    }
    /** This method sets the status associated with the profile. */ 
    public void setStatus(String status) {
        this.status=status;
    }

    @Override
    public boolean equals(Object obj) {//names by itself cannot be the same.
        if(obj instanceof Profile){
            return equals((Profile) obj);
        }
        return super.equals(obj); //ata
    }
    public boolean equals(Profile p){
        return getName().equals(p.getName());
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString shortcut">
    //++toString shortcut
    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(getName()+" ("+getStatus()+"):");
        if(getImage()!=null){
            s.append("[has image] ");
        }
        return s.toString();
    }
    // </editor-fold>
}
