/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author liam
 */
public class DSlider extends JLayeredPane implements ChangeListener{
    private float x1val,x2val;
    
    public Model model;
    
    public final Bar barL,barR;
    public final Arrow arrowL,arrowR;
    public final Lift lift;
    
    public final JLabel length;
    public final JLabel x1Text;
    public final JLabel x2Text;
    

    public DSlider() {
        super();
        
        model = new Model(0,100,20,60);
        barL = new Bar(false,20,this);
        barR = new Bar(true,60,this);
        arrowL = new Arrow(false,20,this);
        arrowR = new Arrow(true,60,this);
        lift = new Lift(20,60,this);

        length = new JLabel("length");
        x1Text = new JLabel("20");
        x2Text = new JLabel("60");

        
        model.addPropertyChangeListener("x1mod", (evt) -> {
            setX1val((float) evt.getNewValue());
            
        });
        
        model.addPropertyChangeListener("x2mod", (evt) -> {
            setX2val((float) evt.getNewValue());
        });        
        
        //length.setBounds(0, 40, 100, 50);
        this.add(length);
        this.add(x1Text);
        this.add(x2Text);

        this.add(arrowL);
        this.add(lift);

        this.add(arrowR);
        this.add(barL);
        this.add(barR);
        
        setUpPropertyListeners();
        
        model.onX1Change(20);
        model.onX2Change(60);
        this.revalidate();
    }
    
    public void setX1val(float val){
        x1val = val;
        barL.setXval(val);
        arrowL.setXval(val);
        lift.setX1val(val);
        x1Text.setText(String.valueOf(val));
        this.revalidate();
    }
    public void setX2val(float val){
        x2val = val;
        barR.setXval(val);
        arrowR.setXval(val);
        lift.setX2val(val);
        x2Text.setText(String.valueOf(val));
        this.revalidate();

    }
    
//    public void onX1change(int newval){
//        setX1val(newval);
//    }
    
    public void setUpPropertyListeners() {
        barL.addPropertyChangeListener("valX1", (evt) -> {
            System.out.println("valX1");
            model.onX1Change((float) evt.getNewValue());
        });
        barR.addPropertyChangeListener("valX2", (evt) -> {
            System.out.println("valX2");
            model.onX2Change((float) evt.getNewValue());
        });
        arrowL.addPropertyChangeListener("valX1", (evt) -> {
            model.onX1Change((float) evt.getNewValue());
        });
        arrowR.addPropertyChangeListener("valX2", (evt) -> {
            model.onX2Change((float) evt.getNewValue());
        });
        lift.addPropertyChangeListener("valX1", (evt) -> {
            model.onX1Change((float) evt.getNewValue());
        });
        lift.addPropertyChangeListener("valX2", (evt) -> {
            model.onX2Change((float) evt.getNewValue());
        });
    }
    
      @Override
    public void setBounds(int aX, int aY, int aWidth, int aHeight) {
        super.setBounds(aX, aY, aWidth, aHeight);

        barR.setBounds(aX, aHeight/2, aWidth, aHeight/2);
        barL.setBounds(aX, aHeight/2, aWidth, aHeight/2);
        arrowL.setBounds(aX, aHeight/2, aWidth, aHeight/2);
        arrowR.setBounds(aX, aHeight/2, aWidth, aHeight/2);
        lift.setBounds(aX, aHeight/2, aWidth, aHeight/2);
        
        length.setBounds(0, 0, aWidth/3, aHeight/2);
        x1Text.setBounds(aWidth/3, 0, aWidth/3, aHeight/2);
        x2Text.setBounds(aWidth*2/3, 0, aWidth/3, aHeight/2);
                
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                300,
               40
        );
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
