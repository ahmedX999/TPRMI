
package ui;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;



public class SalleMachine extends javax.swing.JInternalFrame {
    IDao<Machine> dao = null;
    IDao<Salle> salles =null;
    private DefaultTableModel model;

    public SalleMachine() {
        initComponents();
        try {
            dao = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/machine");
            salles = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/salle");
        } catch (NotBoundException ex) {
            Logger.getLogger(SalleMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SalleMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model = (DefaultTableModel) listMachine.getModel();
        loadSalle();
    }
    public void load (){
        model.setRowCount(0);
        try {
            for(Machine m : dao.findMachinesBySalle((Salle) comboSalle.getSelectedItem())){
                model.addRow(new Object[]{
                    m.getId(),
                    m.getRef(),
                    m.getMarque(),
                    m.getPrix(),
                    
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadSalle(){
        try {
            for(Salle s :salles.findAll()){
                comboSalle.addItem(s);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SalleMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMachine = new javax.swing.JTable();
        comboSalle = new javax.swing.JComboBox<Salle>();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Machines par Salle");

        listMachine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Réference", "Marque", "Prix"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listMachine);

        comboSalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSalleActionPerformed(evt);
            }
        });

        jLabel1.setText("Salle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(comboSalle, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboSalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSalleActionPerformed
        load();        // TODO add your handling code here:
    }//GEN-LAST:event_comboSalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Salle> comboSalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable listMachine;
    // End of variables declaration//GEN-END:variables
}
