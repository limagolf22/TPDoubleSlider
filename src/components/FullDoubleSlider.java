/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author bou
 */
public class FullDoubleSlider extends JLayeredPane {
    private Model model;
    private DSlider dslider;

    public FullDoubleSlider() {
        super();
        JLabel length = new JLabel("length");
        JLabel x1Text = new JLabel("38");
        JLabel x2Text = new JLabel("162");
        dslider = new DSlider();
        
//        this.add(length);
//        this.add(x1Text);
//        this.add(x2Text);
        this.add(dslider);
        
        //model = new Model(dslider.getWidth(),dslider.getHeight(),0,100,38,162);
        
       // dslider.setUpPropertyListeners(model);
        
        model.addPropertyChangeListener("x1mod", (evt) -> {
            System.out.println("sortie modèle");
            dslider.setX1val((int) evt.getNewValue());
        });
        
        model.addPropertyChangeListener("x2mod", (evt) -> {
            System.out.println("sortie modèle");
            dslider.setX2val((int) evt.getNewValue());
        });
        
        model.onX1Change(40);
        model.onX2Change(60);
        dslider.repaint();
    }
    
      @Override
    public void setBounds(int aX, int aY, int aWidth, int aHeight) {
        super.setBounds(aX, aY, aWidth, aHeight);

        dslider.setBounds(aX, aY, aWidth, aHeight);
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                300,
                60
        );
    }
    
}
