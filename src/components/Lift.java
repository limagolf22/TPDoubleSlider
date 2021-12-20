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
    private DSlider dslider;
    
    public Lift(float x1,float x2, DSlider _dslider){
        super();
        dslider = _dslider;
        setX1val(x1);
        setX2val(x2);
        LiftMouseListener arml = new LiftMouseListener();
        this.addMouseListener(arml);
        this.addMouseMotionListener(arml);
    }
    
    public void setX1val(float val){
        x1val=convert2Pix(val);
    }
    
     public void setX2val(float val){
         x2val= convert2Pix(val);
    }

     @Override
     public void setBounds(int aX, int aY, int aWidth, int aHeight){      
            super.setBounds(x1val, aY, x2val-x1val, aHeight); 
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
    
    private float convert2Val(int xval){
        return ((float)(xval-(float)dslider.getHeight()/2))/(dslider.getWidth()-dslider.getHeight())*(dslider.model.M-dslider.model.m)+dslider.model.m;
    } 
    
    private int convert2Pix(float xval){
        return (int) ((xval-dslider.model.m)/(dslider.model.M-dslider.model.m)*(dslider.getWidth()-(float)dslider.getHeight())+(float)dslider.getHeight()/2);
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
            x0 = aE.getXOnScreen();
        }
        @Override
        public void mouseReleased(final MouseEvent aE) {
            pressed = false;
        }
        
        @Override
        public void mouseDragged(final MouseEvent aE) {
            
            support.firePropertyChange("valX2",convert2Val(x1val), convert2Val(x2val+aE.getXOnScreen()-x0));

            support.firePropertyChange("valX1",convert2Val(x1val), convert2Val(x1val+aE.getXOnScreen()-x0));
            
          
            x0 = aE.getXOnScreen();
            
        }
    }
        
        

    
}
