/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SnakeComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class Snake extends JComponent{
    private int ammo=new Random().nextInt(100);
    private Clip clip;
    private int speed=15;
    private Rectangle2D.Double body;
    private Rectangle2D.Double ammoImg;
    private ArrayList<Rectangle2D>ammoList;
    private JFrame parent;
    private int side=20;
    private int ammoSize=10;
    private double x=20;
    private double y=20;
     private double xAmmo=20;
  private String ammoHint;
    public Snake(JFrame parent){
    body=new Rectangle2D.Double(x, y, side, side);
    ammoImg=new Rectangle2D.Double(body.getCenterX(), y, ammoSize, ammoSize);
    ammoList=new ArrayList<>(200);
        addMouseListener(new click());
       
    this.parent=parent;
  
    }
    public void add(){
      
    ammoImg=new Rectangle2D.Double(body.getCenterX(), y, ammoSize, ammoSize);
        ammoList.add(ammoImg);
      
        repaint();
    }
    @Override public void paintComponent(Graphics g){
        ammoHint=String.valueOf(ammo);
        Graphics2D g2d=(Graphics2D)g;
     g2d.draw(body);
   g2d.drawString("ammo: "+ammoHint, 20, 10);
        for(Rectangle2D allAmmo:ammoList){
        g2d.draw(allAmmo);
         repaint();
         
        }
        
    }
   
    private class shoot implements Runnable{

        @Override
        public void run() {
              add();
               ammo--;
           for(int i=0;i<=ammoList.size()-1;i++){
              
              Rectangle2D allAmmo=ammoList.get(i);
              Shoot.animate(allAmmo, parent, body, xAmmo, body.getY(), ammoSize);
               
              
        }
   
    }
      
            
            
        
    }
    private class click extends MouseAdapter{
         public void mousePressed(MouseEvent e) {
        
         }
   @Override  public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            ExecutorService exec;
             if(ammo==0){
                  ammoHint="Out of ammo !";
                 exec=null;
                   
             }else{
         exec=Executors.newWorkStealingPool(3);
         exec.execute(new shoot());
        
         try {
                 clip=AudioSystem.getClip();
                
                 clip.open(AudioSystem.getAudioInputStream(new File("C:\\Users\\User\\Downloads\\8-bit-shot_F#_minor.wav")));
             clip.start();
             } catch (Exception ex) {
                 Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
         
          repaint();
        
             
          
         }
       try {
           clip=AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(new File("C:\\Users\\User\\Downloads\\mixkit-retro-arcade-casino-notification-211.wav")));
        if(e.getButton()==MouseEvent.BUTTON2){
          
      y+=side;
       body.setFrame(x, y, side, side);
       if(y==parent.getHeight())y=0;
       repaint();
       clip.start();
       }else if(SwingUtilities.isRightMouseButton(e)){
            
        y-=side;
       body.setFrame(x, y, side, side);
       if(y==0)y=parent.getHeight();
        repaint();
       clip.start();
       }
       } catch (Exception ex) {
           Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
       } 
      
   
    
   }}
}

