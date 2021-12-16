/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author liam
 */
public class Lift extends JComponent {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int x1val,x2val;
    
    public Lift(int x1,int x2){
        super();
        setX1val(x1);
        setX2val(x2);
        LiftMouseListener arml = new LiftMouseListener();
        this.addMouseListener(arml);
        this.addMouseMotionListener(arml);
    }
    
    public void setX1val(int val){
         x1val= val;
//        setSize(x2val-x1val,getHeight());
//        setLocation(x1val,0);
//                this.invalidate();


    }
    
     public void setX2val(int val){
         x2val= val;
//        setSize(x2val-x1val,getHeight());
//                this.invalidate();


    }

     @Override
     public void setBounds(int aX, int aY, int aWidth, int aHeight){
        
            super.setBounds(x1val, 0, x2val-x1val, aHeight); 
       

     }
     
     
     @Override
    public void paint(final Graphics g) {
        Color color = Color.YELLOW;        
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        color = Color.BLACK;        
        g.setColor(color);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
    
      @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 20);
    }
    
     @Override
    public boolean contains(int aX, int aY) {
        return super.contains(aX, aY);
           
    }
    
      @Override
    public void addPropertyChangeListener(final PropertyChangeListener aListener) {
        support.addPropertyChangeListener(aListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener aListener) {
        support.removePropertyChangeListener(aListener);
    }
    
     @Override
    public void addPropertyChangeListener(
            final String aPropertyName, final PropertyChangeListener aListener) {
        support.addPropertyChangeListener(aPropertyName, aListener);
    }

    @Override
    public void removePropertyChangeListener(
            final String aPropertyName, final PropertyChangeListener aListener) {
        support.removePropertyChangeListener(aPropertyName, aListener);
    }
    
    private class LiftMouseListener extends MouseInputAdapter {
        private int x0;
        private boolean pressed=false;
        
        public LiftMouseListener(){
            super();
            x0 = 0;
        }
        
        @Override
        public void mousePressed(final MouseEvent aE) {
            pressed = true;
            x0 = aE.getX();
        }
        @Override
        public void mouseReleased(final MouseEvent aE) {
            pressed = false;
        }
        
        @Override
        public void mouseDragged(final MouseEvent aE) {
            
            support.firePropertyChange("valX2",x2val, x2val+(int)aE.getX()-x0);

            support.firePropertyChange("valX1",x1val, x1val+(int)aE.getX()-x0);
            
            x0 = (int)aE.getX();
            }
    }
        
        

    
}
