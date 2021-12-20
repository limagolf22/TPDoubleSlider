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
    private int width,height;
    public float m,M;
    public float x1mod,x2mod;

    public Model(float _m, float _M, int _x1, int _x2) {
        m = _m;
        M = _M;
        x1mod = _x1;
        x2mod = _x2;
    }
    
    public void onX1Change(float newval){
        float oldvalmod = x1mod;
        x1mod = Math.max(m, Math.min(newval, x2mod));
        //System.out.println("sortie onX1change "+oldvalmod+"    "+x1mod);

        support.firePropertyChange("x1mod",oldvalmod,x1mod);
    }
    
    public void onX2Change(float newval){
        float oldvalmod = x2mod;
        x2mod = Math.min(M, Math.max(newval, x1mod));
       // System.out.println("sortie onX2change "+oldvalmod+"    "+x2mod);

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
    
   
}
