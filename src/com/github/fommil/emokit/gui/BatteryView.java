// Copyright Samuel Halliday 2012

package com.github.fommil.emokit.gui;


import javax.swing.*;

import com.github.fommil.emokit.EmotivListener;
import com.github.fommil.emokit.Packet;
import java.awt.Color;

/**
 * Component that can listen for Emotiv packets and show the battery level.
 *
 * @author Sam Halliday
 */
public class BatteryView extends JProgressBar implements EmotivListener {

    public BatteryView()
    {
        setStringPainted(true);
        setForeground(Color.BLUE);
        setString("Battery");
    }
    
    @Override
    public void receivePacket(Packet packet) {
        int battery = packet.getBatteryLevel();
        setValue(battery);
        repaint();
    }

    @Override
    public void connectionBroken() { }
}
