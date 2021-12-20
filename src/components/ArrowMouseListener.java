/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author bou
 */
public class ArrowMouseListener extends MouseInputAdapter {
        private int x0;
        private boolean pressed=false,isRight;
        private DSlider dslider;
        
        public ArrowMouseListener(boolean isr,DSlider _dslider){
            super();
            isRight = isr;
            dslider = _dslider;
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
                //support.firePropertyChange("valX2",xval, xval+(int)aE.getX()-x0);
            }
            else {
                           // System.out.println("valX1 fired "+xval+"    "+ ((int)aE.getX()-x0) );

               // support.firePropertyChange("valX1",xval, xval+(int)aE.getX()-x0);
            }
        }
        
        

    }
