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
    private int xval,offset;
    
    public Bar(){
        this(0,0);
    }
    
    public Bar(int ofs,int xval){
        super();
        offset = ofs;
        setXval(xval);
        this.addMouseListener(new BarMouseListener());
    }
    
    public void setXval(int val){
        xval = val;
        if(offset>0){
            setSize(offset-xval-getHeight(),getHeight());
            setLocation(xval+getHeight(), 0);
        }  
        else {
            setSize(xval-getHeight(),getHeight());
        }
        this.invalidate();
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
    
    private class BarMouseListener extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent aE) {
            System.out.println(aE);
            if(offset>0){
                support.firePropertyChange("valX2",xval, xval+(int)aE.getX());
            }
            else {
                support.firePropertyChange("valX1",xval, (int)aE.getX()+getHeight());
            }
        }

    }
}
