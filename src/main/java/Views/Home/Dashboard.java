/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.Home;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Controllers.AnimalController;
import Models.Animal;
import Models.User.PersistentUser;
import Views.Auth.SignIn;

/**
 *
 * @author ivant
 */
public class Dashboard extends javax.swing.JFrame {

    private PersistentUser user;
    private final AnimalController animalController;
    private Timer timer;

    public Dashboard(PersistentUser user) {
        this.user = user;
        initComponents();
        animalController = new AnimalController();
        initOptions();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();
        petsBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        welcomeTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableofAnimals = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        adoptIdInput = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        adoptLocationInput = new javax.swing.JTextField();
        continueAdoptionBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameLabelInfo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        typeLabelInfo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        genereLabelInfo = new javax.swing.JLabel();
        ageLabelInfo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dateAdmisionLabelInfo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoPetInput = new javax.swing.JTextArea();
        imgLabelPet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        logoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(0, 0, 0));
        logoutBtn.setText("Cerrar Sessión");
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        petsBtn.setBackground(new java.awt.Color(0, 0, 0));
        petsBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        petsBtn.setForeground(new java.awt.Color(255, 255, 255));
        petsBtn.setText("Mascotas");
        petsBtn.setBorderPainted(false);
        petsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutBtn)
                    .addComponent(petsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(petsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(49, 49, 49))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        welcomeTxt.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        welcomeTxt.setForeground(new java.awt.Color(0, 0, 0));
        welcomeTxt.setText("!Es bueno verte de nuevo Ivan¡");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingresa la raza de la mascota para más información.");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        tableofAnimals.setAutoCreateRowSorter(true);
        tableofAnimals.setBackground(new java.awt.Color(255, 255, 255));
        tableofAnimals.setForeground(new java.awt.Color(0, 0, 0));
        tableofAnimals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo", "Raza", "Género", "Edad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableofAnimals.setGridColor(new java.awt.Color(0, 0, 0));
        tableofAnimals.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tableofAnimals);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Adoptar:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Id de la mascota:");

        adoptIdInput.setBackground(new java.awt.Color(255, 255, 255));
        adoptIdInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adoptIdInput.setForeground(new java.awt.Color(0, 0, 0));
        adoptIdInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adoptIdInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adoptIdInputKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tu ubicación de envío:");

        adoptLocationInput.setBackground(new java.awt.Color(255, 255, 255));
        adoptLocationInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adoptLocationInput.setForeground(new java.awt.Color(0, 0, 0));
        adoptLocationInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        continueAdoptionBtn.setBackground(new java.awt.Color(0, 0, 0));
        continueAdoptionBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        continueAdoptionBtn.setForeground(new java.awt.Color(255, 255, 255));
        continueAdoptionBtn.setText("Continuar con la adopción");
        continueAdoptionBtn.setBorderPainted(false);
        continueAdoptionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueAdoptionBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(continueAdoptionBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(adoptIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(adoptLocationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(adoptIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(adoptLocationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(continueAdoptionBtn))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Información de la mascota:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nombre:");

        nameLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        nameLabelInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nameLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        nameLabelInfo.setText("Jack");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tipo:");

        typeLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        typeLabelInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        typeLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        typeLabelInfo.setText("Gato");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Género:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Edad: ");

        genereLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        genereLabelInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        genereLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        genereLabelInfo.setText("Hembra");

        ageLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        ageLabelInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ageLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        ageLabelInfo.setText("2 años");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Fecha de Ingreso: ");

        dateAdmisionLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        dateAdmisionLabelInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateAdmisionLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        dateAdmisionLabelInfo.setText("26, julio de 2024");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Descripción:");

        infoPetInput.setEditable(false);
        infoPetInput.setBackground(new java.awt.Color(255, 255, 255));
        infoPetInput.setColumns(20);
        infoPetInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoPetInput.setForeground(new java.awt.Color(0, 0, 0));
        infoPetInput.setLineWrap(true);
        infoPetInput.setRows(5);
        infoPetInput.setText("reugeiurgheuirrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        infoPetInput.setBorder(null);
        infoPetInput.setCaretColor(new java.awt.Color(255, 255, 255));
        infoPetInput.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        infoPetInput.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(infoPetInput);

        imgLabelPet.setText("jLabel16");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeLabelInfo))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genereLabelInfo))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ageLabelInfo))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateAdmisionLabelInfo)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgLabelPet, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nameLabelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(typeLabelInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(genereLabelInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(ageLabelInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(dateAdmisionLabelInfo)))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgLabelPet, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(welcomeTxt)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(welcomeTxt)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initOptions() {
        welcomeTxt.setText("¡Bienvenido " + user.getUsername() + "!");
        infoPetInput.setLineWrap(true);
        infoPetInput.setWrapStyleWord(true);
        infoPetInput.setEditable(false);
        infoPetInput.setBorder(null);
        infoPetInput.setBackground(null);
        adoptLocationInput.setText(user.getAddress());
        clearLabels();
        updateAnimalTable(animalController.getAllAnimals());
    }

    private void clearLabels() {
        nameLabelInfo.setText("");
        typeLabelInfo.setText("");
        genereLabelInfo.setText("");
        ageLabelInfo.setText("");
        dateAdmisionLabelInfo.setText("");
        infoPetInput.setText("");
    }

    private void updateAnimalTable(List<Animal> animals) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tableofAnimals.getModel();
        model.setRowCount(0);

        for (Animal animal : animals) {
            animal.setSex(animal.getSex().equals("FEMALE") ? "Hembra" : "Macho");
            animal.setType(animal.getType().equals("DOG") ? "Perro" : "Gato");

            model.addRow(new Object[]{
                animal.getId(),
                animal.getName(),
                animal.getType(),
                animal.getBreed(),
                animal.getSex(),
                animal.getAge() + " años",});
        }
    }

    private void handleAnimalSearch(String query) {
        try {
            int id = Integer.parseInt(query);

            Animal animal = animalController.getAnimalById(id);

            if (animal == null) {
                SwingUtilities.invokeLater(this::clearLabels);
                return;
            }

            animal.setSex(animal.getSex().equals("FEMALE") ? "Hembra" : "Macho");
            animal.setType(animal.getType().equals("DOG") ? "Perro" : "Gato");

            SwingUtilities.invokeLater(() -> updateAnimalLabels(animal));

        } catch (Exception e) {
            SwingUtilities.invokeLater(this::clearLabels);
        }
    }

    private void updateAnimalLabels(Animal animal) {
        nameLabelInfo.setText(animal.getName());
        typeLabelInfo.setText(animal.getType());
        genereLabelInfo.setText(animal.getSex());
        ageLabelInfo.setText(animal.getAge() + " años");
        dateAdmisionLabelInfo.setText(animal.getDateOfAdmission());
        infoPetInput.setText(animal.getDescription());
    }

    private void petsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_petsBtnActionPerformed

    private void continueAdoptionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueAdoptionBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continueAdoptionBtnActionPerformed

    private void adoptIdInputKeyTyped(java.awt.event.KeyEvent evt) {
        String query = adoptIdInput.getText() + evt.getKeyChar();

        if (timer != null) {
            timer.cancel();
        }

        if (query.trim().isEmpty()) {
            clearLabels();
            return;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handleAnimalSearch(query);
            }
        }, 500);
    }

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {
        String query = jTextField1.getText() + evt.getKeyChar();

        if (query.trim().isEmpty()) {
            updateAnimalTable(animalController.getAllAnimals());
        } else {
            if (timer != null) {
                timer.cancel();
            }

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Animal> animals = animalController.getAnimalsByBreed(query);
                    updateAnimalTable(animals);
                }
            }, 500);
        }
    }

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.user = null;
        this.dispose();
        new SignIn().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adoptIdInput;
    private javax.swing.JTextField adoptLocationInput;
    private javax.swing.JLabel ageLabelInfo;
    private javax.swing.JButton continueAdoptionBtn;
    private javax.swing.JLabel dateAdmisionLabelInfo;
    private javax.swing.JLabel genereLabelInfo;
    private javax.swing.JLabel imgLabelPet;
    private javax.swing.JTextArea infoPetInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel nameLabelInfo;
    private javax.swing.JButton petsBtn;
    private javax.swing.JTable tableofAnimals;
    private javax.swing.JLabel typeLabelInfo;
    private javax.swing.JLabel welcomeTxt;
    // End of variables declaration//GEN-END:variables
}