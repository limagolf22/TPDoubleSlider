/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import java.beans.Beans;

/**
 *
 * @author liam
 */
public class Arrow extends JComponent {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private boolean isRight=false;
    private int xval;
    private DSlider dslider;
    
    public Arrow(){
        this(true,0,null);
    }
    
    public Arrow(boolean isright,float xval,DSlider _dslider){
        super();
        isRight = isright;
        dslider = _dslider;
        setXval(xval);
        ArrowMouseListener arml = new ArrowMouseListener();
        this.addMouseListener(arml);
        this.addMouseMotionListener(arml);

    }
    
    public void setisRight(boolean val){
        this.isRight = val;
    }
    
    public void setXval(float val){
        xval = convert2Pix(val);      
        this.repaint();
    }
     
     @Override
     public void setBounds(int aX, int aY, int aWidth, int aHeight){
         if(!isRight){
            super.setBounds(xval-aHeight, aY, aHeight, aHeight);
        
        }  
        else {
            super.setBounds(xval, aY, aHeight, aHeight);
        }

     }
    
     @Override
    public void paint(final Graphics g) {
        Color color = Color.WHITE;
        g.setColor(color);

        g.fillRect(0, 0, getHeight(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getHeight(), getHeight());
        color = Color.BLACK;
        g.setColor(color);      
        if(isRight){
            Polygon poly = new Polygon();
            poly.addPoint(3, 3);
            poly.addPoint(3, getHeight()-3);
            poly.addPoint(getHeight()-3, getHeight()/2);
            g.fillPolygon(poly);
        }
        else {
            Polygon poly = new Polygon();
            poly.addPoint(3, getHeight()/2);
            poly.addPoint(getHeight()-3, 3);
            poly.addPoint(getHeight()-3, getHeight()-3);
            g.fillPolygon(poly);
        }
    }
   
    
      @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
    
     @Override
    public boolean contains(int aX, int aY) {
        return super.contains(aX, aY) && aX<=getHeight();

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
        return (int) ((xval-dslider.model.m)/(dslider.model.M-dslider.model.m)*(dslider.getWidth()-dslider.getHeight())+(float)dslider.getHeight()/2);
    } 
    
    
    private class ArrowMouseListener extends MouseInputAdapter {
        private int x0;
        private boolean pressed=false;
        
        public ArrowMouseListener(){
            super();
            x0 = 0;
        }
        
        @Override
        public void mousePressed(final MouseEvent aE) {
            System.out.println(aE);
            pressed = true;
            x0 = aE.getX();
        }
        @Override
        public void mouseReleased(final MouseEvent aE) {
                        System.out.println(aE);
            pressed = false;
        }
        
        @Override
        public void mouseDragged(final MouseEvent aE) {
            //System.out.println(aE);
            if(isRight){
                //System.out.println("valX1 fired "+xval+"    "+ ((int)aE.getX()-x0) );
                support.firePropertyChange("valX2",convert2Val(xval),convert2Val(xval+aE.getX()-x0));
            }
            else {
                           // System.out.println("valX1 fired "+xval+"    "+ ((int)aE.getX()-x0) );

                support.firePropertyChange("valX1",convert2Val(xval), convert2Val(xval+aE.getX()-x0));
            }
           
        }
        
        

    }
}
