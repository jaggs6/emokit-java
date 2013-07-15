package com.github.fommil.emokit.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.swing.*;

import com.github.fommil.emokit.jpa.EmotivJpaController;
import com.github.fommil.emokit.jpa.EmotivSession;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Sam Halliday
 */
@Log
public class SessionEditor1 extends JPanel {

    @Getter
    @Setter
    private EmotivJpaController controller;

    private final JPanel topPanel,middlePanel,bottomPanel;
    private final JTextField title = new JTextField("");
    private final JTextArea notes = new JTextArea("");
    private final JButton start, stop, load, delete, export;
    private final JToggleButton live;
    private JList recordedSessions;

    public SessionEditor1() {
        super(new BorderLayout());

        topPanel = new JPanel(new BorderLayout());
        middlePanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new BorderLayout());
        
        JLabel sessionLabel = new JLabel("New Session");
        sessionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(sessionLabel, BorderLayout.NORTH);
        
        JPanel titleRow = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Title:");
        titleRow.add(titleLabel, BorderLayout.WEST);
        titleRow.add(title, BorderLayout.CENTER);
        topPanel.add(titleRow, BorderLayout.CENTER);

        JPanel notesRow = new JPanel(new BorderLayout());
        JLabel notesLabel = new JLabel("Notes:");
        notes.setLineWrap(true);
        notesRow.add(notesLabel, BorderLayout.WEST);
        notesRow.add(notes, BorderLayout.CENTER);
        middlePanel.add(notesRow, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new BorderLayout());
        start = new JButton("Record");
        stop = new JButton("Stop");
        live = new JToggleButton("Live");
        live.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ScrollableSensorView.isLive = live.isSelected();
            }
        });
        live.setSelected(true);
        stop.setEnabled(false);
        live.setEnabled(false);
        
        buttons.add(start, BorderLayout.WEST);
        buttons.add(live, BorderLayout.CENTER);
        buttons.add(stop, BorderLayout.EAST);
        middlePanel.add(buttons, BorderLayout.SOUTH);

        //log.info(notes.getBorder().getClass().toString());

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
                live.setEnabled(true);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
                live.setEnabled(false);
            }
        });
        title.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                title.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        });
        notes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                notes.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        });
        title.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                titleChanged();
            }
        });
        notes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                notesChanged();
            }
        });
        
        load = new JButton("Load");
        export = new JButton("Export");
        delete = new JButton("Delete");
        
        recordedSessions = new JList();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        
        setPreferredSize(new Dimension(225,300));
        
        add(topPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);

    }
    
    public void loadSessionList()
    {
        List<EmotivSession> data = controller.getSessions();
        //recordedSessions = new JList(data.toArray());
        ListIterator<EmotivSession> emoSesItr = data.listIterator();
        ArrayList<String> names = new ArrayList<String>();
        while(emoSesItr.hasNext())
        {
            names.add(emoSesItr.next().getName());
        }
        recordedSessions = new JList(names.toArray(new String[names.size()]));
        //recordedSessions.setPreferredSize(new Dimension(50,150));
        bottomPanel.removeAll();
        bottomPanel.add(recordedSessions, BorderLayout.CENTER);
        resetBottomPanel();
        revalidate();
    }
    
    private void resetBottomPanel()
    {
        JLabel sessionsLabel = new JLabel("Recorded Sessions");
        sessionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(sessionsLabel, BorderLayout.NORTH);
        
        JPanel buttonRow = new JPanel(new BorderLayout());
        buttonRow.add(load,BorderLayout.WEST);
        buttonRow.add(export,BorderLayout.CENTER);
        buttonRow.add(delete,BorderLayout.EAST);
        bottomPanel.add(buttonRow,BorderLayout.SOUTH);
    }        
         
    private void clear() {
        //title.setText("");
        notes.setText("");
    }

    private void start() {
        if (controller.isRecording())
            return;
        EmotivSession session = new EmotivSession();
        session.setName(title.getText());
        session.setNotes(notes.getText());
        controller.createSession(session);
        controller.setRecording(true);
        ScrollableSensorView.isRecording = true;
        start.setEnabled(false);
        stop.setEnabled(true);
    }

    private void stop() {
        controller.setRecording(false);
        title.setText(updateTitle());
        start.setEnabled(true);
        stop.setEnabled(false);
        ScrollableSensorView.isRecording = false;
        clear();
    }

    private String updateTitle() {
        Matcher matcher = Pattern.compile("^(.*)(\\d++)$").matcher(title.getText());
        if (!matcher.find())
            return title.getText() + " 1";
        return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
    }

    private void titleChanged() {
        if (controller.isRecording()) {
            EmotivSession session = controller.getSession();
            session.setName(title.getText());
            controller.updateSession(session);
            title.setBorder(null);
        }
    }

    private void notesChanged() {
        if (controller.isRecording()) {
            EmotivSession session = controller.getSession();
            session.setNotes(notes.getText());
            controller.updateSession(session);
            notes.setBorder(null);
        }
    }

}
