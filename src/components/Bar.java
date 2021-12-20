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
public class Bar extends JComponent {
    
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private boolean IsRight;
    private int xval;
    private DSlider dslider;
    
    
    public Bar(){
        this(false,0,null);
    }
    
    public Bar(boolean isright ,float xval,DSlider _dslider){
        super();
        IsRight = isright;
        dslider = _dslider;
        setXval(xval);
        this.addMouseListener(new BarMouseListener());
        System.out.println("dslider width "+dslider.getWidth());
    }
    
    public void setXval(float val){
        xval = convert2Pix(val);
        this.revalidate();
        System.out.println("dslider width "+dslider.getWidth());

    }
    
    @Override
     public void setBounds(int aX, int aY, int aWidth, int aHeight){
        if(IsRight){
            super.setBounds(xval+aHeight, aY, dslider.getWidth()-xval-aHeight, aHeight);
                    System.out.println("dslider width "+dslider.getWidth());

        }  
        else {
            super.setBounds(0, aY, xval-aHeight, aHeight);
        }

     }
    
     @Override
    public void paint(final Graphics g) {
        Color color = Color.GRAY;
        g.setColor(color);
        g.fillRect(0,0,getWidth(), getHeight());
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
        return (int) ((xval-dslider.model.m)/(dslider.model.M-dslider.model.m)*(dslider.getWidth()-dslider.getHeight())+(float)dslider.getHeight()/2);
    } 
    
    
    
    private class BarMouseListener extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent aE) {
//            System.out.println(aE);
            if(IsRight){
                support.firePropertyChange("valX2",convert2Val(xval), convert2Val(xval+aE.getX()));
            }
            else {
                support.firePropertyChange("valX1",convert2Val(xval), convert2Val(aE.getX()+(int)dslider.getHeight()/2));
            }
           
        }

    }
}
