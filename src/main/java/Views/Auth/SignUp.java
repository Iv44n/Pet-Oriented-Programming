package Views.Auth;

import Controllers.AuthController;
import Models.User.PersistentUser;
import Views.Home.Dashboard;

public class SignUp extends javax.swing.JFrame {

    private final AuthController authController;

    public SignUp() {
        initComponents();
        this.authController = new AuthController();
        this.labelErrorPassword.setVisible(false);
        this.labelErrorConfirmPass.setVisible(false);
        this.labelErrorEmail.setVisible(false);
        this.labelErrorUsername.setVisible(false);
        this.labelErrorAdress.setVisible(false);
        this.labelErrorName.setVisible(false);
        this.labelErrorLastName.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        labelErrorConfirmPass = new javax.swing.JLabel();
        labelErrorPassword = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtAdrress = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        btnSignUp = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        labelErrorEmail = new javax.swing.JLabel();
        labelErrorUsername = new javax.swing.JLabel();
        labelErrorAdress = new javax.swing.JLabel();
        labelErrorName = new javax.swing.JLabel();
        labelErrorLastName = new javax.swing.JLabel();
        changeLogBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Crear una cuenta");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contraseña:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Confirmar Contraseña");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("¿Ya tienes una cuenta?");

        txtConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));

        labelErrorConfirmPass.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorConfirmPass.setText("error");

        labelErrorPassword.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorPassword.setText("error");

        txtName.setBackground(new java.awt.Color(255, 255, 255));
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nombre de usuario:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Apellido:");

        txtLastName.setBackground(new java.awt.Color(255, 255, 255));
        txtLastName.setForeground(new java.awt.Color(0, 0, 0));
        txtLastName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLastName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nombre:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ubicación de envió:");

        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsername.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        txtAdrress.setBackground(new java.awt.Color(255, 255, 255));
        txtAdrress.setForeground(new java.awt.Color(0, 0, 0));
        txtAdrress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAdrress.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAdrress.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Correo Electrónico:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Número de Telefono (Opcional):");

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtEmail.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        txtNumber.setBackground(new java.awt.Color(255, 255, 255));
        txtNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNumber.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        btnSignUp.setBackground(new java.awt.Color(0, 0, 0));
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Registrarse");
        btnSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        labelErrorEmail.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorEmail.setText("error");

        labelErrorUsername.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorUsername.setText("error");

        labelErrorAdress.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorAdress.setText("error");

        labelErrorName.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorName.setText("error");

        labelErrorLastName.setForeground(new java.awt.Color(255, 51, 51));
        labelErrorLastName.setText("error");

        changeLogBtn.setBackground(new java.awt.Color(255, 255, 255));
        changeLogBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changeLogBtn.setForeground(new java.awt.Color(51, 51, 255));
        changeLogBtn.setText("Iniciar Sesión");
        changeLogBtn.setBorder(null);
        changeLogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelErrorName)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(changeLogBtn)
                            .addGap(12, 12, 12))
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(labelErrorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(labelErrorConfirmPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConfirmPassword)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtName)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel7))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel8)
                                .addComponent(labelErrorLastName)))
                        .addComponent(btnSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelErrorEmail)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(labelErrorUsername))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelErrorAdress)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAdrress)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 16, Short.MAX_VALUE)))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelErrorName)
                    .addComponent(labelErrorLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdrress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelErrorUsername)
                    .addComponent(labelErrorAdress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(labelErrorEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelErrorPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelErrorConfirmPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(changeLogBtn))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed

        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String username = txtUsername.getText();
        String address = txtAdrress.getText();
        String email = txtEmail.getText();
        String number = txtNumber.getText();
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());

        if (!validateForm()) {
            return;
        }

        if (!validateEmail(email)) {
            return;
        }

        if (!validatePassword(password)) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("las contraseñas no coinciden", labelErrorConfirmPass);
            showError("las contraseñas no coinciden", labelErrorPassword);
            return;
        }

        try {
            PersistentUser user = authController.signUp(name, lastName, username, password, email, number, address);

            if (user == null) {
                showError("el correo o la contraseña son incorrectos", labelErrorPassword);
                showError("el correo o la contraseña son incorrectos", labelErrorConfirmPass);
                return;
            }

            this.setVisible(false);
            Dashboard dashboard = new Dashboard(user);
            dashboard.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            dashboard.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void changeLogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeLogBtnActionPerformed
        this.dispose();
        new SignIn().setVisible(true);
    }//GEN-LAST:event_changeLogBtnActionPerformed

    private boolean validateForm() {
        if (txtName.getText().isEmpty()) {
            showError("El nombre no puede estar vacío", labelErrorName);
            return false;
        } else {
            labelErrorName.setVisible(false);
        }

        if (txtLastName.getText().isEmpty()) {
            showError("El apellido no puede estar vacío", labelErrorLastName);
            return false;
        } else {
            labelErrorLastName.setVisible(false);
        }

        if (txtUsername.getText().isEmpty()) {
            showError("El nombre de usuario no puede estar vacío", labelErrorUsername);
            return false;
        } else {
            labelErrorUsername.setVisible(false);
        }

        if (txtAdrress.getText().isEmpty()) {
            showError("La ubicación de envío no puede estar vacía", labelErrorAdress);
            return false;
        } else {
            labelErrorAdress.setVisible(false);
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            showError("el correo no puede estar vacío", labelErrorEmail);
            return false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showError("el correo no es válido", labelErrorEmail);
            return false;
        }
        labelErrorPassword.setVisible(false);
        return true;
    }

    private boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            showError("la contraseña no puede estar vacía", labelErrorConfirmPass);
            showError("la contraseña no puede estar vacía", labelErrorPassword);
            return false;
        } else if (!password.matches("^(?=(.*[A-Za-z]){5,}).{5,}$")) {
            showError("la contraseña al menos debe tener 5 letras", labelErrorConfirmPass);
            showError("la contraseña al menos debe tener 5 letras", labelErrorPassword);
            return false;
        }
        labelErrorConfirmPass.setVisible(false);
        return true;
    }

    private void showError(String message, javax.swing.JLabel label) {
        label.setText(message);
        label.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton changeLogBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelErrorAdress;
    private javax.swing.JLabel labelErrorConfirmPass;
    private javax.swing.JLabel labelErrorEmail;
    private javax.swing.JLabel labelErrorLastName;
    private javax.swing.JLabel labelErrorName;
    private javax.swing.JLabel labelErrorPassword;
    private javax.swing.JLabel labelErrorUsername;
    private javax.swing.JTextField txtAdrress;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
