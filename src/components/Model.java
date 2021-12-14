/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author liam
 */
public class Model implements ChangeListener {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int width;
    private float m,M;
    public int x1mod=40,x2mod=90;

    public Model(int w, float _m, float _M) {
        width = w;
        m = _m;
        M = _M;
    }
    
    public void onX1Change(int newval){
        int oldvalmod = x1mod;
        x1mod = Math.max(0, Math.min(newval, x2mod));
        System.out.println("sortie onX1change"+oldvalmod+"    "+x1mod);

        support.firePropertyChange("x1mod",oldvalmod,x1mod);
    }
    
    public void onX2Change(int newval){
        int oldvalmod = x2mod;
        x2mod = Math.min(width, Math.max(newval, x1mod));
        support.firePropertyChange("x2mod",oldvalmod,x2mod);
    }
    
    
    public void addPropertyChangeListener(final PropertyChangeListener aListener) {
        support.addPropertyChangeListener(aListener);
    }

    
    public void removePropertyChangeListener(final PropertyChangeListener aListener) {
        support.removePropertyChangeListener(aListener);
    }

    public void addPropertyChangeListener(
            final String aPropertyName, final PropertyChangeListener aListener) {
        support.addPropertyChangeListener(aPropertyName, aListener);
    }

    public void removePropertyChangeListener(
            final String aPropertyName, final PropertyChangeListener aListener) {
        support.removePropertyChangeListener(aPropertyName, aListener);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private float convert2Val(int xval){
        return xval/width*(M-m)+m;
    } 
}
