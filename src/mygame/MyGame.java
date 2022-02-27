/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import SnakeComponent.Snake;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class MyGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
        Fr frame=new Fr();
        frame.setVisible(true);
        });
    }
    
}
class Fr extends JFrame{
private SnakeComponent.Snake snake=new Snake(Fr.this);
public Fr(){
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 300);
    setResizable(false);
    add(snake);
}
}
