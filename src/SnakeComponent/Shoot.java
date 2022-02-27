/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakeComponent;

import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class Shoot {
     public static void animate(Rectangle2D r,JFrame parent,Rectangle2D ship,double x,double y,int ammo){
        double ya= ship.getY();
    for(x=r.getX();x<=parent.getWidth();x+=ammo){
               r.setFrame(x,ya, ammo, ammo);
          
          
               try {
                   Thread.sleep(1);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
    }
}
