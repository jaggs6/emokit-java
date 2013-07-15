/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fommil.emokit.gui;

import com.github.fommil.emokit.jpa.EmotivJpaController;
import com.github.fommil.emokit.jpa.EmotivSession;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jagdeep
 */
public class ControlsView extends javax.swing.JPanel {

    @Getter
    @Setter
    private EmotivJpaController controller;
    
    /**
     * Creates new form SessionEditor
     */
    public ControlsView() {
        super(new BorderLayout());
        initComponents();
        
        btnSessionLive.setSelected(true);
        btnSessionStop.setEnabled(false);
        btnSessionLive.setEnabled(false);
        
        btnSessionLive.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ScrollableSensorView.isLive = btnSessionLive.isSelected();
            }
        });
        btnSessionRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
                btnSessionLive.setEnabled(true);
            }
        });
        btnSessionStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
                btnSessionLive.setEnabled(false);
            }
        });
        txtSessionNotes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtSessionNotes.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        });
        txtSessionUser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                titleChanged();
            }
        });
        txtSessionNotes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                notesChanged();
            }
        });
    }
    
    public void loadSessionList()
    {
        List<EmotivSession> data = controller.getSessions();
        ListIterator<EmotivSession> emoSesItr = data.listIterator();
        DefaultListModel lm1  = (DefaultListModel) listSessions.getModel();
        lm1.clear();
        while(emoSesItr.hasNext())
        {
            lm1.addElement(emoSesItr.next().getName());
        }
        revalidate();
    }
    
    private static void generateSessionCSVFile(String sFileName,EmotivSession es)
   {
	try
	{
	    FileWriter writer = new FileWriter(sFileName);
 
	    writer.append("DisplayName");
	    writer.append(',');
	    writer.append("Age");
	    writer.append('\n');
 
	    writer.append("MKYONG");
	    writer.append(',');
	    writer.append("26");
            writer.append('\n');
 
	    //generate whatever data you want
 
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
    }
    
    private void deleteSession(EmotivSession es)
    {
        controller.deleteSession(es);
    }
    
    private void clear() {
        txtSessionUser.setText("");
        txtSessionNotes.setText("");
    }

    private void start() {
        if (controller.isRecording())
            return;
        EmotivSession session = new EmotivSession();
        session.setName(txtSessionUser.getText() + "-" + txtSessionDateTime);
        session.setNotes(txtSessionNotes.getText());
        controller.createSession(session);
        controller.setRecording(true);
        ScrollableSensorView.isRecording = true;
        btnSessionRecord.setEnabled(false);
        btnSessionStop.setEnabled(true);
    }

    private void stop() {
        controller.setRecording(false);
        txtSessionDateTime.setText(updateTime());
        btnSessionRecord.setEnabled(true);
        btnSessionStop.setEnabled(false);
        ScrollableSensorView.isRecording = false;
        clear();
    }
    
    private String updateTime() {
        //update the time here
        return null;
    }

    private void titleChanged() {
        if (controller.isRecording()) {
            EmotivSession session = controller.getSession();
            session.setName(txtSessionUser.getText() + "-" + txtSessionDateTime);
            controller.updateSession(session);
        }
    }

    private void notesChanged() {
        if (controller.isRecording()) {
            EmotivSession session = controller.getSession();
            session.setNotes(txtSessionNotes.getText());
            controller.updateSession(session);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mainTabbedPane = new javax.swing.JTabbedPane();
        sessionsTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnSessionLoad = new javax.swing.JButton();
        btnSessionDelete = new javax.swing.JButton();
        btnSessionImport = new javax.swing.JButton();
        btnSessionExport = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listSessions = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSessionDateTime = new javax.swing.JTextField();
        txtSessionUser = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSessionNotes = new javax.swing.JTextArea();
        btnSessionRecord = new javax.swing.JButton();
        btnSessionLive = new javax.swing.JToggleButton();
        btnSessionStop = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listActions = new javax.swing.JList();
        btnActionAdd = new javax.swing.JButton();
        btnActionDelete = new javax.swing.JButton();
        btnActionImport = new javax.swing.JButton();
        btnActionExport = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtActionTittle = new javax.swing.JTextField();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        btnSessionLoad.setText("Load");

        btnSessionDelete.setText("Delete");
        btnSessionDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionDeleteActionPerformed(evt);
            }
        });

        btnSessionImport.setText("Import");

        btnSessionExport.setText("Export");

        listSessions.setModel(new DefaultListModel());
        jScrollPane3.setViewportView(listSessions);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSessionLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSessionDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSessionImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSessionExport)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSessionLoad)
                    .addComponent(btnSessionDelete)
                    .addComponent(btnSessionImport)
                    .addComponent(btnSessionExport))
                .addContainerGap())
        );

        sessionsTabbedPane.addTab("Recorded", jPanel2);

        jLabel4.setText("Notes:");

        jLabel3.setText("DateTime:");

        jLabel2.setText("User:");

        txtSessionNotes.setColumns(20);
        txtSessionNotes.setLineWrap(true);
        txtSessionNotes.setRows(5);
        jScrollPane1.setViewportView(txtSessionNotes);

        btnSessionRecord.setText("Record");

        btnSessionLive.setText("Live");

        btnSessionStop.setText("Stop");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtSessionDateTime)
                            .addComponent(txtSessionUser)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSessionRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSessionLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSessionStop)
                        .addGap(3, 3, 3)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSessionUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSessionDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSessionLive)
                        .addComponent(btnSessionStop))
                    .addComponent(btnSessionRecord))
                .addContainerGap())
        );

        sessionsTabbedPane.addTab("New", jPanel1);

        mainTabbedPane.addTab("Sessions", sessionsTabbedPane);

        jScrollPane2.setViewportView(listActions);

        btnActionAdd.setText("Add");

        btnActionDelete.setText("Delete");

        btnActionImport.setText("Import");

        btnActionExport.setText("Export");

        jLabel1.setText("Title:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnActionAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActionDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActionImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActionExport))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtActionTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtActionTittle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActionAdd)
                    .addComponent(btnActionDelete)
                    .addComponent(btnActionImport)
                    .addComponent(btnActionExport))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Actions", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSessionDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionDeleteActionPerformed
        deleteSession(controller.getSessions().get(listSessions.getSelectedIndex()));
        loadSessionList();
        revalidate();
    }//GEN-LAST:event_btnSessionDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActionAdd;
    private javax.swing.JButton btnActionDelete;
    private javax.swing.JButton btnActionExport;
    private javax.swing.JButton btnActionImport;
    private javax.swing.JButton btnSessionDelete;
    private javax.swing.JButton btnSessionExport;
    private javax.swing.JButton btnSessionImport;
    private javax.swing.JToggleButton btnSessionLive;
    private javax.swing.JButton btnSessionLoad;
    private javax.swing.JButton btnSessionRecord;
    private javax.swing.JButton btnSessionStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listActions;
    private javax.swing.JList listSessions;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTabbedPane sessionsTabbedPane;
    private javax.swing.JTextField txtActionTittle;
    private javax.swing.JTextField txtSessionDateTime;
    private javax.swing.JTextArea txtSessionNotes;
    private javax.swing.JTextField txtSessionUser;
    // End of variables declaration//GEN-END:variables
}
