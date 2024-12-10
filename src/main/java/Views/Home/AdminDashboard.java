/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.Home;

import java.net.URI;
import java.net.URL;
import java.text.Normalizer;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import Controllers.AnimalController;
import Models.Animal.Animal;
import Models.User.PersistentUser;
import Views.Auth.SignIn;

/**
 *
 * @author ivant
 */
public class AdminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form AdminDashboard
     */
    private PersistentUser user;
    private final AnimalController animalController;
    private Timer timer;

    public AdminDashboard(PersistentUser user) {
        this.user = user;
        animalController = new AnimalController();
        initComponents();
        initOptions();
    }

    private void initOptions() {
        welcomeTxt.setText("¡Bienvenido al panel de administrador " + user.getUsername() + "!");
        updateAnimalTable(animalController.getAllAnimals(null, null));
    }

    private void updateAnimalTable(List<Animal> animals) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tableofAnimals
                .getModel();
        model.setRowCount(0);

        for (Animal animal : animals) {
            model.addRow(new Object[] {
                    animal.getId(),
                    animal.getName(),
                    animal.getType().equals("DOG") ? "Perro" : "Gato",
                    animal.getAge() + " años",
                    animal.getSex().equals("FEMALE") ? "Hembra" : "Macho",
                    animal.getBreed() });
        }
    }

    private void showAnimalUpdate(Animal animal) {
        ENameTextField.setText(animal.getName());
        ETypeComboBox.setSelectedItem(animal.getType().equals("DOG") ? "Perro" : "Gato");
        ESexComboBox.setSelectedItem(animal.getSex().equals("FEMALE") ? "Hembra" : "Macho");
        EBreedTextField.setText(animal.getBreed());
        EWeightTextField.setText(String.valueOf(animal.getWeight()));
        EAgeTextField.setText(String.valueOf(animal.getAge()));
        EAdoptionComboBox.setSelectedItem(
                animal.getAdoptionStatus().equals("ADOPTED") ? "Adoptado" : "En adopción");
        EImageTextField.setText(animal.getImage());
        EDescriptionTextArea.setText(animal.getDescription());

        String urlIcon = animal.getImage();

        try {
            URL imgUrl = URI.create(urlIcon).toURL();

            Icon icon = new ImageIcon(
                    new ImageIcon(imgUrl)
                            .getImage()
                            .getScaledInstance(this.EImageLabel.getWidth(),
                                    this.EImageLabel.getHeight(), 0));

            this.EImageLabel.setIcon(icon);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    private void clearAnimalUpdateForm() {
        ENameTextField.setText("");
        EBreedTextField.setText("");
        EWeightTextField.setText("");
        EAgeTextField.setText("");
        EImageTextField.setText("");
        EDescriptionTextArea.setText("");

        ETypeComboBox.setSelectedIndex(0);
        ESexComboBox.setSelectedIndex(0);
        EAdoptionComboBox.setSelectedIndex(0);

        EImageLabel.setIcon(null);
    }

    private void handleAnimalSearch(String query) {
        try {
            int id = Integer.parseInt(query);

            Animal animal = animalController.getAnimalById(id, null);

            SwingUtilities.invokeLater(() -> showAnimalUpdate(animal));

        } catch (Exception e) {
            SwingUtilities.invokeLater(this::clearAnimalUpdateForm);
        }
    }

    private Animal getAnimalFromFormForUpdate(Animal beforeAnimal) {
        int id = beforeAnimal.getId(); // El ID no cambia
        String name = ENameTextField.getText().trim();
        String type = ETypeComboBox.getSelectedItem().toString().trim();
        String sex = ESexComboBox.getSelectedItem().toString().trim();
        String breed = EBreedTextField.getText().trim();
        String adoptionStatus = EAdoptionComboBox.getSelectedItem().toString().trim();
        String image = EImageTextField.getText().trim();
        String description = EDescriptionTextArea.getText().trim();

        String normalizedAdoptionStatus = Normalizer.normalize(adoptionStatus, Normalizer.Form.NFD);
        normalizedAdoptionStatus = normalizedAdoptionStatus.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        String typeInDB = type.equals("Perro") ? "DOG" : type.equals("Gato") ? "CAT" : null;
        String sexInDB = sex.equals("Hembra") ? "FEMALE" : sex.equals("Macho") ? "MALE" : null;
        String adoptionStatusInDB = normalizedAdoptionStatus.equals("Adoptado") ? "ADOPTED"
                : normalizedAdoptionStatus.equals("En adopcion") ? "IN ADOPTION" : null;

        String finalType = typeInDB != null && !typeInDB.equals(beforeAnimal.getType())
                ? typeInDB
                : null;
        String finalSex = sexInDB != null && !sexInDB.equals(beforeAnimal.getSex())
                ? sexInDB
                : null;
        String finalAdoptionStatus = adoptionStatusInDB != null
                && !adoptionStatusInDB.equals(beforeAnimal.getAdoptionStatus())
                        ? adoptionStatusInDB
                        : null;

        float weight = 0;
        Integer age = 0;

        try {
            weight = EWeightTextField.getText().trim().isEmpty() ? null
                    : Float.parseFloat(EWeightTextField.getText().trim());
            age = EAgeTextField.getText().trim().isEmpty() ? null
                    : Integer.parseInt(EAgeTextField.getText().trim());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir campos numéricos: " + e.getMessage());
        }

        Integer finalAge = age != 0 && !age.equals(beforeAnimal.getAge()) ? age : 0;
        float finalWeight = weight != 0 && weight != beforeAnimal.getWeight() ? weight : 0;

        // Comparación de valores
        return new Animal(
                id,
                name.isEmpty() || name.equals(beforeAnimal.getName()) ? null : name,
                finalType,
                finalAge,
                breed.isEmpty() || breed.equals(beforeAnimal.getBreed()) ? null : breed,
                finalSex,
                finalWeight,
                description.isEmpty() || description.equals(beforeAnimal.getDescription())
                        ? null
                        : description,
                finalAdoptionStatus,
                null,
                image.isEmpty() || image.equals(beforeAnimal.getImage()) ? null : image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        welcomeTxt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableofAnimals = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        BreedTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        EIdTextField = new javax.swing.JTextField();
        EImageLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ENameTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        EBreedTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        EWeightTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ETypeComboBox = new javax.swing.JComboBox<>();
        ESexComboBox = new javax.swing.JComboBox<>();
        EAdoptionComboBox = new javax.swing.JComboBox<>();
        EImageTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        EDescriptionTextArea = new javax.swing.JTextArea();
        EUpdateButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jTextField9 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField10 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CheckBoxDog = new javax.swing.JCheckBox();
        CheckBoxCat = new javax.swing.JCheckBox();
        EAgeTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        welcomeTxt.setBackground(new java.awt.Color(255, 255, 255));
        welcomeTxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        welcomeTxt.setForeground(new java.awt.Color(0, 0, 0));
        welcomeTxt.setText("Bienvenido al panel de administrador Ivan");

        tableofAnimals.setBackground(new java.awt.Color(255, 255, 255));
        tableofAnimals.setForeground(new java.awt.Color(0, 0, 0));
        tableofAnimals.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null }
                },
                new String[] {
                        "Id", "Nombre", "Tipo", "Edad", "Género", "Raza"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableofAnimals);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tabla de animales");

        logoutBtn.setBackground(new java.awt.Color(0, 0, 0));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Cerrar Sessión");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        BreedTxtField.setBackground(new java.awt.Color(255, 255, 255));
        BreedTxtField.setForeground(new java.awt.Color(0, 0, 0));
        BreedTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BreedTxtFieldActionPerformed(evt);
            }
        });
        BreedTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BreedTxtFieldKeyTyped(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Filtrar por raza:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Editar los datos de una mascota:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ingrese el Id de la mascota:");

        EIdTextField.setBackground(new java.awt.Color(255, 255, 255));
        EIdTextField.setForeground(new java.awt.Color(0, 0, 0));
        EIdTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EIdTextFieldKeyTyped(evt);
            }
        });

        EImageLabel.setText("jLabel6");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nombre:");

        ENameTextField.setBackground(new java.awt.Color(255, 255, 255));
        ENameTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tipo:");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Género:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Raza:");

        EBreedTextField.setBackground(new java.awt.Color(255, 255, 255));
        EBreedTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Peso:");

        EWeightTextField.setBackground(new java.awt.Color(255, 255, 255));
        EWeightTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Estado:");

        ETypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ETypeComboBox.setForeground(new java.awt.Color(0, 0, 0));
        ETypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato" }));

        ESexComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ESexComboBox.setForeground(new java.awt.Color(0, 0, 0));
        ESexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hembra", "Macho" }));

        EAdoptionComboBox.setBackground(new java.awt.Color(255, 255, 255));
        EAdoptionComboBox.setForeground(new java.awt.Color(0, 0, 0));
        EAdoptionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En adopción", "Adoptado" }));

        EImageTextField.setBackground(new java.awt.Color(255, 255, 255));
        EImageTextField.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Imagen:");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Descripción:");

        EDescriptionTextArea.setBackground(new java.awt.Color(255, 255, 255));
        EDescriptionTextArea.setColumns(20);
        EDescriptionTextArea.setForeground(new java.awt.Color(0, 0, 0));
        EDescriptionTextArea.setLineWrap(true);
        EDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(EDescriptionTextArea);

        EUpdateButton.setBackground(new java.awt.Color(0, 0, 0));
        EUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        EUpdateButton.setText("Actualizar los datos de la mascota");
        EUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EUpdateButtonActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Agregar una nueva mascota:");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nombre:");

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tipo:");

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Descripción:");

        jTextArea3.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea3.setColumns(20);
        jTextArea3.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato" }));

        jComboBox6.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox6.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En adopción", "Adoptado" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jComboBox5.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox5.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hembra", "Macho" }));

        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Imagen:");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Estado:");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Peso:");

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Raza:");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Género:");

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Agregar mascota");

        jLabel21.setText("jLabel21");

        CheckBoxDog.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxDog.setForeground(new java.awt.Color(0, 0, 0));
        CheckBoxDog.setText("Mostrar solo Perros");
        CheckBoxDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxDogActionPerformed(evt);
            }
        });

        CheckBoxCat.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxCat.setForeground(new java.awt.Color(0, 0, 0));
        CheckBoxCat.setText("Mostrar solo Gatos");
        CheckBoxCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxCatActionPerformed(evt);
            }
        });

        EAgeTextField.setBackground(new java.awt.Color(255, 255, 255));
        EAgeTextField.setForeground(new java.awt.Color(0, 0, 0));
        EAgeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EAgeTextFieldActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Edad:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel4)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel5)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        EIdTextField,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        98,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel8)
                                                                                        .addComponent(jLabel7)
                                                                                        .addComponent(jLabel10)
                                                                                        .addComponent(jLabel11)
                                                                                        .addComponent(jLabel12)
                                                                                        .addComponent(jLabel9))
                                                                                .addGap(2, 2, 2))
                                                                        .addGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(jLabel25)
                                                                                        .addGap(18, 18, 18)))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(EAgeTextField)
                                                                        .addComponent(EWeightTextField)
                                                                        .addComponent(ENameTextField)
                                                                        .addComponent(EBreedTextField)
                                                                        .addComponent(ETypeComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(ESexComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(EAdoptionComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel14)
                                                                        .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                260,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(EImageLabel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 275,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel13,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        42,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(EImageTextField,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        377,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(EUpdateButton))))
                                                .addGap(100, 100, 100)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel15)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(jLabel17)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(jComboBox4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(jLabel16)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jTextField4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel24)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(jLabel22)
                                                                                                .addComponent(
                                                                                                        jLabel23)))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jTextField5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jComboBox5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jTextField10,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                100,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(1, 1, 1)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel19)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(
                                                                                                        jTextField9,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        391,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                        jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(
                                                                                                                        jLabel20)
                                                                                                                .addGap(14,
                                                                                                                        14,
                                                                                                                        14)
                                                                                                                .addComponent(
                                                                                                                        jComboBox6,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(41,
                                                                                                                        41,
                                                                                                                        41)
                                                                                                                .addGroup(
                                                                                                                        jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(
                                                                                                                                        jLabel18)
                                                                                                                                .addComponent(
                                                                                                                                        jScrollPane4,
                                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                        250,
                                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addComponent(
                                                                                                        jButton3)))))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel21,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 176,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(welcomeTxt)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutBtn))
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(226, 226, 226)
                                                .addComponent(CheckBoxDog)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(CheckBoxCat)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BreedTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(welcomeTxt)
                                        .addComponent(logoutBtn))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(BreedTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(CheckBoxDog)
                                        .addComponent(CheckBoxCat))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7)
                                        .addComponent(ENameTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createSequentialGroup()
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel16)
                                                                                                .addComponent(
                                                                                                        jTextField4,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGap(4, 4, 4)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel17)
                                                                                                .addComponent(
                                                                                                        jComboBox4,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel24)
                                                                                                .addComponent(
                                                                                                        jComboBox5,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGap(4, 4, 4)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel23)
                                                                                                .addComponent(
                                                                                                        jTextField5,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGap(3, 3, 3)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jLabel22)
                                                                                                .addComponent(
                                                                                                        jTextField10,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGap(4, 4, 4)
                                                                                        .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel20)
                                                                                                .addComponent(
                                                                                                        jComboBox6,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel1Layout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(jLabel18)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jScrollPane4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                134,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(28, 28, 28)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel9)
                                                                                        .addComponent(ESexComboBox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(EBreedTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel10))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(EWeightTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel11))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel12)
                                                                                        .addComponent(EAdoptionComboBox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(EAgeTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel25)))
                                                                        .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                162,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel19)
                                                                        .addComponent(jTextField9,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(EImageTextField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel13)))
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton3)
                                                        .addComponent(EUpdateButton))
                                                .addGap(0, 24, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(EIdTextField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(ETypeComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EUpdateButtonActionPerformed
        String query = EIdTextField.getText();
        Animal beforeAnimal = null;

        if (timer != null) {
            timer.cancel();
        }

        if (query.trim().isEmpty()) {
            clearAnimalUpdateForm();
            return;
        }

        int id;
        try {
            id = Integer.parseInt(query);
        } catch (NumberFormatException e) {
            return;
        }

        beforeAnimal = animalController.getAnimalById(id, null);
        Animal updatedAnimal = getAnimalFromFormForUpdate(beforeAnimal);

        if (beforeAnimal == null)
            return;

        boolean result = animalController.updateAnimal(updatedAnimal);

        if (result) {
            clearAnimalUpdateForm();
            updateAnimalTable(animalController
                    .getAllAnimals(CheckBoxDog.isSelected() ? "DOG"
                            : CheckBoxCat.isSelected() ? "CAT" : null, null));
            EIdTextField.setText("");
        }
    }// GEN-LAST:event_EUpdateButtonActionPerformed

    private void EIdTextFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_EIdTextFieldKeyTyped
        String query = EIdTextField.getText() + evt.getKeyChar();

        if (timer != null) {
            timer.cancel();
        }

        if (query.trim().isEmpty()) {
            // clearLabels();
            return;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handleAnimalSearch(query);
            }
        }, 500);
    }// GEN-LAST:event_EIdTextFieldKeyTyped

    private void EAgeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EAgeTextFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_EAgeTextFieldActionPerformed

    private void CheckBoxDogActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CheckBoxDogActionPerformed
        // TODO add your handling code here:
        if (CheckBoxDog.isSelected()) {
            CheckBoxCat.setSelected(false);
            updateAnimalTable(animalController.getAnimalsByType("DOG"));
        } else {
            updateAnimalTable(animalController.getAllAnimals(null, null));
        }
    }// GEN-LAST:event_CheckBoxDogActionPerformed

    private void CheckBoxCatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CheckBoxCatActionPerformed
        if (CheckBoxCat.isSelected()) {
            CheckBoxDog.setSelected(false);
            updateAnimalTable(animalController.getAnimalsByType("CAT"));
        } else {
            updateAnimalTable(animalController.getAllAnimals(null, null));
        }
    }// GEN-LAST:event_CheckBoxCatActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutBtnActionPerformed
        this.user = null;
        this.dispose();
        new SignIn().setVisible(true);
    }// GEN-LAST:event_logoutBtnActionPerformed

    private void BreedTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BreedTxtFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed
     // GEN-LAST:event_BreedTxtFieldActionPerformed

    private void BreedTxtFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_BreedTxtFieldKeyTyped
        String query = BreedTxtField.getText() + evt.getKeyChar();

        String type;

        if (CheckBoxCat.isSelected()) {
            type = "CAT";
        } else if (CheckBoxDog.isSelected()) {
            type = "DOG";
        } else {
            type = null;
        }

        if (query.trim().isEmpty()) {
            updateAnimalTable(animalController.getAllAnimals(type, null));
        } else {
            if (timer != null) {
                timer.cancel();
            }

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    List<Animal> animals = animalController.getAnimalsByBreed(query, type, null);
                    updateAnimalTable(animals);
                }
            }, 500);
        }
    }
    // GEN-FIRST:event_jTextField8ActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jTextField8ActionPerformed
    // GEN-FIRST:event_jTextField7ActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jTextField7ActionPerformed
    // GEN-FIRST:event_jCheckBox2ActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jCheckBox2ActionPerformed
    // GEN-FIRST:event_jCheckBox1ActionPerformed
    // TODO add your handling code here:
    // GEN-LAST:event_jCheckBox1ActionPerformed
    // GEN-LAST:event_BreedTxtFieldKeyTyped

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField9ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BreedTxtField;
    private javax.swing.JCheckBox CheckBoxCat;
    private javax.swing.JCheckBox CheckBoxDog;
    private javax.swing.JComboBox<String> EAdoptionComboBox;
    private javax.swing.JTextField EAgeTextField;
    private javax.swing.JTextField EBreedTextField;
    private javax.swing.JTextArea EDescriptionTextArea;
    private javax.swing.JTextField EIdTextField;
    private javax.swing.JLabel EImageLabel;
    private javax.swing.JTextField EImageTextField;
    private javax.swing.JTextField ENameTextField;
    private javax.swing.JComboBox<String> ESexComboBox;
    private javax.swing.JComboBox<String> ETypeComboBox;
    private javax.swing.JButton EUpdateButton;
    private javax.swing.JTextField EWeightTextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTable tableofAnimals;
    private javax.swing.JLabel welcomeTxt;
    // End of variables declaration//GEN-END:variables
}
