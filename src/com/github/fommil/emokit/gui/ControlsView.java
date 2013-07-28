/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fommil.emokit.gui;

import com.github.fommil.emokit.jpa.EmotivDatum;
import com.github.fommil.emokit.jpa.EmotivJpaController;
import com.github.fommil.emokit.jpa.EmotivSession;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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

        txtSessionDateTime.setEditable(false);
        updateTime();

        btnSessionLive.setSelected(true);
        btnSessionStop.setEnabled(false);
        btnSessionLive.setEnabled(false);
    }

    public void loadSessionList() {
        List<EmotivSession> data = controller.getSessions();
        ListIterator<EmotivSession> emoSesItr = data.listIterator();
        DefaultListModel lm1 = (DefaultListModel) listSessions.getModel();
        lm1.clear();
        while (emoSesItr.hasNext()) {
            lm1.addElement(emoSesItr.next().getName());
        }
        revalidate();
    }

    private void generateSessionCSVFile(String sFileName, EmotivSession es) {
        try {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("id");
            writer.append(',');
            writer.append("notes");
            writer.append(',');
            writer.append("sitting");
            writer.append(',');
            writer.append("subject");
            writer.append('\n');

            writer.append(es.getId().toString());
            writer.append(',');
            writer.append(es.getNotes().toString());
            writer.append(',');
            writer.append(es.getSitting().toString());
            writer.append(',');
            if (es.getSubject() == null) {
                writer.append(es.getSubject());
            } else {
                writer.append(es.getSubject().toString());
            }
            writer.append('\n');

            //save datum
            writer.append("id");
            writer.append(',');
            writer.append("af3");
            writer.append(',');
            writer.append("af3_quality");
            writer.append(',');
            writer.append("af4");
            writer.append(',');
            writer.append("af4_quality");
            writer.append(',');
            writer.append("f3");
            writer.append(',');
            writer.append("f3_quality");
            writer.append(',');
            writer.append("f4");
            writer.append(',');
            writer.append("f4_quality");
            writer.append(',');
            writer.append("f7");
            writer.append(',');
            writer.append("f7_quality");
            writer.append(',');
            writer.append("f8");
            writer.append(',');
            writer.append("f8_quality");
            writer.append(',');
            writer.append("fc5");
            writer.append(',');
            writer.append("fc5_quality");
            writer.append(',');
            writer.append("fc6");
            writer.append(',');
            writer.append("fc6_quality");
            writer.append(',');
            writer.append("o1");
            writer.append(',');
            writer.append("o1_quality");
            writer.append(',');
            writer.append("o2");
            writer.append(',');
            writer.append("o2_quality");
            writer.append(',');
            writer.append("p7");
            writer.append(',');
            writer.append("p7_quality");
            writer.append(',');
            writer.append("p8");
            writer.append(',');
            writer.append("p8_quality");
            writer.append(',');
            writer.append("t7");
            writer.append(',');
            writer.append("t7_quality");
            writer.append(',');
            writer.append("t8");
            writer.append(',');
            writer.append("t8_quality");
            writer.append(',');
            writer.append("battery");
            writer.append(',');
            writer.append("gyrox");
            writer.append(',');
            writer.append("gyroy");
            writer.append(',');
            writer.append("timestamp");
            writer.append(',');
            writer.append("session_id");
            writer.append('\n');

            List<EmotivDatum> led = controller.getDatums(es);
            for (EmotivDatum ed : led) {
                writer.append(ed.getId().toString());
                writer.append(',');
                writer.append(ed.getAF3().toString());
                writer.append(',');
                writer.append(ed.getAF3_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getAF4().toString());
                writer.append(',');
                writer.append(ed.getAF4_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getF3().toString());
                writer.append(',');
                writer.append(ed.getF3_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getF4().toString());
                writer.append(',');
                writer.append(ed.getF4_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getF7().toString());
                writer.append(',');
                writer.append(ed.getF7_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getF8().toString());
                writer.append(',');
                writer.append(ed.getF8_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getFC5().toString());
                writer.append(',');
                writer.append(ed.getFC5_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getFC6().toString());
                writer.append(',');
                writer.append(ed.getFC6_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getO1().toString());
                writer.append(',');
                writer.append(ed.getO1_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getO2().toString());
                writer.append(',');
                writer.append(ed.getO2_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getP7().toString());
                writer.append(',');
                writer.append(ed.getP7_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getP8().toString());
                writer.append(',');
                writer.append(ed.getP8_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getT7().toString());
                writer.append(',');
                writer.append(ed.getT7_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getT8().toString());
                writer.append(',');
                writer.append(ed.getT8_QUALITY().toString());
                writer.append(',');
                writer.append(ed.getBattery().toString());
                writer.append(',');
                writer.append(ed.getGyroX().toString());
                writer.append(',');
                writer.append(ed.getGyroY().toString());
                writer.append(',');
                writer.append(ed.getTimestamp().toString());
                writer.append(',');
                writer.append(ed.getSession().getId().toString());
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteSession(EmotivSession es) {
        controller.deleteSession(es);
    }

    private void clear() {
        txtSessionUser.setText("");
        txtSessionDateTime.setText("");
        txtSessionNotes.setText("");
    }

    private void start() {
        if (controller.isRecording()) {
            return;
        }
        EmotivSession session = new EmotivSession();
        //updateTime();
        session.setName(getSessionName());
        session.setNotes(txtSessionNotes.getText());
        controller.createSession(session);
        controller.setRecording(true);
        ScrollableSensorView.isRecording = true;
        btnSessionRecord.setEnabled(false);
        btnSessionStop.setEnabled(true);
        sessionsTabbedPane.setEnabledAt(0, false);
    }

    private String getSessionName() {
        return txtSessionUser.getText() + "-" + txtSessionDateTime.getText();
    }

    private void stop() {
        controller.setRecording(false);
        //updateTime();
        btnSessionRecord.setEnabled(true);
        btnSessionStop.setEnabled(false);
        ScrollableSensorView.isRecording = false;
        clear();
        loadSessionList();
        sessionsTabbedPane.setEnabledAt(0, true);
    }

    private void updateTime() {
        //update the time here
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        txtSessionDateTime.setText(dateFormat.format(cal.getTime()));
    }

    private void titleChanged() {
        if (controller.isRecording()) {
            EmotivSession session = controller.getSession();
            session.setName(getSessionName());
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
        cbCurrentDateTime = new javax.swing.JCheckBox();
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
        btnSessionLoad.setEnabled(false);

        btnSessionDelete.setText("Delete");
        btnSessionDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionDeleteActionPerformed(evt);
            }
        });

        btnSessionImport.setText("Import");
        btnSessionImport.setEnabled(false);

        btnSessionExport.setText("Export");
        btnSessionExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionExportActionPerformed(evt);
            }
        });

        listSessions.setModel(new DefaultListModel());
        listSessions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
                .addContainerGap(40, Short.MAX_VALUE))
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

        txtSessionUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSessionUserFocusLost(evt);
            }
        });

        txtSessionNotes.setColumns(20);
        txtSessionNotes.setLineWrap(true);
        txtSessionNotes.setRows(5);
        txtSessionNotes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSessionNotesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtSessionNotes);

        btnSessionRecord.setText("Record");
        btnSessionRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionRecordActionPerformed(evt);
            }
        });

        btnSessionLive.setText("Live");
        btnSessionLive.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                btnSessionLiveStateChanged(evt);
            }
        });

        btnSessionStop.setText("Stop");
        btnSessionStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionStopActionPerformed(evt);
            }
        });

        cbCurrentDateTime.setSelected(true);
        cbCurrentDateTime.setText("Current");
        cbCurrentDateTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCurrentDateTimeActionPerformed(evt);
            }
        });

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addComponent(txtSessionUser))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSessionDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCurrentDateTime))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSessionRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSessionLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSessionStop)
                        .addGap(3, 3, 3)))
                .addContainerGap(39, Short.MAX_VALUE))
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
                    .addComponent(txtSessionDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCurrentDateTime))
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

        listActions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listActions);

        btnActionAdd.setText("Add");
        btnActionAdd.setEnabled(false);

        btnActionDelete.setText("Delete");
        btnActionDelete.setEnabled(false);

        btnActionImport.setText("Import");
        btnActionImport.setEnabled(false);

        btnActionExport.setText("Export");
        btnActionExport.setEnabled(false);

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
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addComponent(mainTabbedPane)
                .addContainerGap())
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

    private void btnSessionRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionRecordActionPerformed
        start();
        btnSessionLive.setEnabled(true);
    }//GEN-LAST:event_btnSessionRecordActionPerformed

    private void btnSessionLiveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnSessionLiveStateChanged
        ScrollableSensorView.isLive = btnSessionLive.isSelected();
    }//GEN-LAST:event_btnSessionLiveStateChanged

    private void btnSessionStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionStopActionPerformed
        stop();
        btnSessionLive.setEnabled(false);
    }//GEN-LAST:event_btnSessionStopActionPerformed

    private void txtSessionUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSessionUserFocusLost
        titleChanged();
    }//GEN-LAST:event_txtSessionUserFocusLost

    private void txtSessionNotesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSessionNotesFocusLost
        notesChanged();
    }//GEN-LAST:event_txtSessionNotesFocusLost

    private void cbCurrentDateTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCurrentDateTimeActionPerformed
        if (cbCurrentDateTime.isSelected()) {
            updateTime();
            txtSessionDateTime.setEditable(false);
        } else {
            txtSessionDateTime.setEditable(true);
        }
    }//GEN-LAST:event_cbCurrentDateTimeActionPerformed

    private void btnSessionExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionExportActionPerformed
        JFileChooser saveFile = new JFileChooser();
        saveFile.setSelectedFile(new File(listSessions.getSelectedValue().toString().replace(" ", "") + ".csv"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
        saveFile.setFileFilter(filter);
        int returnVal = saveFile.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = saveFile.getSelectedFile();
                String fileName = file.getPath();

                if (fileName == null) {
                    return;
                }
                int offset = fileName.lastIndexOf(".");
                if (offset == -1) {
                    String message = "file suffix was not specified";
                    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
                String type = fileName.substring(offset + 1);
                if (type.toLowerCase().equals("csv")) {
                    //save the csv
                    generateSessionCSVFile(fileName, controller.getSessions().get(listSessions.getSelectedIndex()));
                } else {
                    String message = "unknown writer file suffix (" + type + ")";
                    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                String message = "could not save file";
                JOptionPane.showMessageDialog(this, ex.getStackTrace(), "IOException", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSessionExportActionPerformed
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
    private javax.swing.JCheckBox cbCurrentDateTime;
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
