/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fommil.emokit.gui;

import com.github.fommil.emokit.Packet;
import com.sun.org.apache.xpath.internal.operations.Quo;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Jagdeep
 */
public class ScrollableSensorView extends JScrollPane
{
    private SensorView sv;
    private final JScrollBar horizontalBar;
    public static Boolean isLive = true;
    public static Boolean isRecording = false;
    
    public ScrollableSensorView(SensorView senView)
    {
        super(senView);
        this.sv = senView;
        senView.parent = this;
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        horizontalBar = getHorizontalScrollBar();
        horizontalBar.addAdjustmentListener(new AdjustmentListener() {  
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {  
            if(isLive)
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
        }
    });
        
        senView.parentHeight = this.getHeight();
//        horizontalBar.addAdjustmentListener(new AdjustmentListener() {
//
//            @Override
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                int pos = horizontalBar.getValue();
//                if(pos>0)
//                {
//                    sv.isLive = false;
//                    //sv.scrollQueue.addAll(sv.queue.subList(pos, pos+sv.MAX_SIZE));
//                }
//                else
//                {
//                    sv.isLive = true;
//                }
//            }
//        });
    }
    
    public SensorView getSensorView()
    {
        return this.sv;
    }
}
