/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author liam
 */
public class DSlider extends JLayeredPane implements ChangeListener{
    private int width,x1val,x2val;
    
    public final Bar barL,barR;
    public final Arrow arrowL,arrowR;
    public final Lift lift;
    

    public DSlider() {
        super();
        barL = new Bar(0,40);
        barR = new Bar(300,90);
        arrowL = new Arrow(false,40);
        arrowR = new Arrow(true,90);
        lift = new Lift(40,90);

        this.add(arrowL);
        this.add(lift);

        this.add(arrowR);
        this.add(barL);
        this.add(barR);
        
        SwingUtilities.invokeLater(() -> {
          //  this.setX1val(40);
          //  this.setX2val(90);
           // this.repaint();
        });

        
    }
    
    public void setX1val(int val){
        x1val = val;
        barL.setXval(val);
        arrowL.setXval(val);
        lift.setX1val(val);
        //this.invalidate();
        //this.setBounds(getLocation().x,getLocation().y,getWidth(),getHeight());
        this.revalidate();
    }
    public void setX2val(int val){
        x2val = val;
        barR.setXval(val);
        arrowR.setXval(val);
        lift.setX2val(val);
        //this.invalidate();
        //this.setBounds(getLocation().x,getLocation().y,getWidth(),getHeight());
        this.revalidate();

    }
    
    public void onX1change(int newval){
        setX1val(newval);
    }
    
    public void setUpPropertyListeners(Model model) {
        barL.addPropertyChangeListener("valX1", (evt) -> {
            model.onX1Change((int)evt.getNewValue());
        });
        barR.addPropertyChangeListener("valX2", (evt) -> {
            model.onX2Change((int)evt.getNewValue());
        });
        arrowL.addPropertyChangeListener("valX1", (evt) -> {
            model.onX1Change((int)evt.getNewValue());
        });
        arrowR.addPropertyChangeListener("valX2", (evt) -> {
            model.onX2Change((int)evt.getNewValue());
        });
        lift.addPropertyChangeListener("valX1", (evt) -> {
            model.onX1Change((int)evt.getNewValue());
        });
        lift.addPropertyChangeListener("valX2", (evt) -> {
            model.onX2Change((int)evt.getNewValue());
        });
    }
    
      @Override
    public void setBounds(int aX, int aY, int aWidth, int aHeight) {
        super.setBounds(aX, aY, aWidth, aHeight);

        barR.setBounds(aX, aY, aWidth, aHeight);
        barL.setBounds(aX, aY, aWidth, aHeight);
        arrowL.setBounds(aX, aY, aWidth, aHeight);
        arrowR.setBounds(aX, aY, aWidth, aHeight);
        lift.setBounds(aX, aY, aWidth, aHeight);
        System.out.println("setBounds done");
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                300,
                20
        );
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
