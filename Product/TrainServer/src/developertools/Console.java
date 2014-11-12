/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package developertools;

import common.interfaces.ProcessorRequest;
import connection.tcp.IncomingConnectionsHandler;
import connection.udp.SetupUDP;
import execute.Server;
import execute.SimpleProcessorRequest;
import helpers.ServerState;
import helpers.User;
import helpers.UserArray;
import java.awt.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author James
 */
public class Console extends javax.swing.JFrame
{
    SetupUDP udpCaster;

    public Console()
    {
        udpCaster = new SetupUDP();
        initComponents();
        potentialUsers.setModel(new DefaultListModel<>());

        try
        {
            IncomingConnectionsHandler incomingTCP = new IncomingConnectionsHandler();
            SimpleProcessorRequest incomingTCPProcess = new SimpleProcessorRequest(incomingTCP);
            Server.serverThreadPool.schedule(incomingTCPProcess);

            Server.serverThreadPool.schedule(new ProcessorRequest()
            {

                @Override
                public void process()
                {
                    while (true)
                    {
                        updateTables();
                        try
                        {
                            Thread.sleep(Server.serverTransmitter.getDelay() / 4);
                        } catch (InterruptedException ex)
                        {
                            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });

        } catch (Exception ex)
        {
        }
    }

    private void updateTables()
    {
        potentialUsers.setModel(createModel(Server.potentialUsers));
        activeUsers.setModel(createModel(Server.activeUsers));
        repaint();
    }

    private DefaultListModel<String> createModel(UserArray array)
    {
        DefaultListModel<String> model = new DefaultListModel<>();

        if (array.getArraySize() == 0)
        {
            model.addElement("-Empty-");
        } else
        {
            for (Map.Entry<Integer, User> entries : array.getUserEntrySet())
            {
                Integer key = entries.getKey();
                User value = entries.getValue();

                model.addElement("User: " + value.ID);
            }
        }
        return model;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        potentialUsers = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        activeUsers = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        toBeChargedUsers = new javax.swing.JList();
        btn_arriveAtStation = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        field_serverState = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_leaveStation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        potentialUsers.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(potentialUsers);

        activeUsers.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(activeUsers);

        toBeChargedUsers.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(toBeChargedUsers);

        btn_arriveAtStation.setText("Arrive At Station");
        btn_arriveAtStation.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btn_arriveAtStationActionPerformed(evt);
            }
        });

        jLabel1.setText("Potential Users:");

        jLabel2.setText("Active Users:");

        jLabel3.setText("Users to be charged:");

        jLabel4.setText("Train Server state:");

        btn_leaveStation.setText("Leave Station");
        btn_leaveStation.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btn_leaveStationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(field_serverState))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_arriveAtStation)
                                .addGap(18, 18, 18)
                                .addComponent(btn_leaveStation))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel4))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_arriveAtStation)
                    .addComponent(field_serverState, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_leaveStation))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_arriveAtStationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btn_arriveAtStationActionPerformed
    {//GEN-HEADEREND:event_btn_arriveAtStationActionPerformed
        udpCaster.getGPS().setState(ServerState.State.arrivedAtStation);
        field_serverState.setText(Server.state.toString());
        
        Server.serverThreadPool.schedule(new ProcessorRequest()
        {

            @Override
            public void process()
            {
                udpCaster.getGPS().transmit();
            }
        });
    }//GEN-LAST:event_btn_arriveAtStationActionPerformed

    private void btn_leaveStationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btn_leaveStationActionPerformed
    {//GEN-HEADEREND:event_btn_leaveStationActionPerformed
        udpCaster.getGPS().setState(ServerState.State.leftStation);
        field_serverState.setText(Server.state.toString());
        
        Server.serverThreadPool.schedule(new ProcessorRequest()
        {

            @Override
            public void process()
            {
                udpCaster.getGPS().transmit();
            }
        });
    }//GEN-LAST:event_btn_leaveStationActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Console().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList activeUsers;
    private javax.swing.JButton btn_arriveAtStation;
    private javax.swing.JButton btn_leaveStation;
    private javax.swing.JTextField field_serverState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList potentialUsers;
    private javax.swing.JList toBeChargedUsers;
    // End of variables declaration//GEN-END:variables
}
