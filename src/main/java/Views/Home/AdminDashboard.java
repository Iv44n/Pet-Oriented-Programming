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
import Models.Animal.AnimalBase;
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
        EImageLabel.setText(null);
        AImagelLabel.setText(null);
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
                    animal.getBreed(),
                    animal.getAdoptionStatus().equals("ADOPTED") ? "Adoptado" : "En adopción",});
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
        EImageLabel.setText(null);
        AImagelLabel.setText(null);
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

    private void clearAddAnimalForm() {
        ANameTextField.setText("");
        ABreedTextField.setText("");
        AWeigthTextField.setText("");
        AAgeTextField.setText("");
        ATypeComboBox.setSelectedIndex(0); // Selección predeterminada
        ASexComboBox.setSelectedIndex(0); // Selección predeterminada
        AStateComboBox.setSelectedIndex(0); // Selección predeterminada
        AImageTextField.setText("");
        ADescriptionTextArea.setText("");
        AImagelLabel.setText(null);
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        ANameTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ABreedTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ADescriptionTextArea = new javax.swing.JTextArea();
        AImageTextField = new javax.swing.JTextField();
        ATypeComboBox = new javax.swing.JComboBox<>();
        AStateComboBox = new javax.swing.JComboBox<>();
        ASexComboBox = new javax.swing.JComboBox<>();
        AWeigthTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        AddAnimalButton = new javax.swing.JButton();
        AImagelLabel = new javax.swing.JLabel();
        CheckBoxDog = new javax.swing.JCheckBox();
        CheckBoxCat = new javax.swing.JCheckBox();
        EAgeTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        AAgeTextField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        welcomeTxt.setBackground(new java.awt.Color(255, 255, 255));
        welcomeTxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        welcomeTxt.setForeground(new java.awt.Color(0, 0, 0));
        welcomeTxt.setText("Bienvenido al panel de administrador Ivan");

        tableofAnimals.setBackground(new java.awt.Color(255, 255, 255));
        tableofAnimals.setForeground(new java.awt.Color(0, 0, 0));
        tableofAnimals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Tipo", "Edad", "Género", "Raza", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
        ENameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ENameTextFieldActionPerformed(evt);
            }
        });

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

        ANameTextField.setBackground(new java.awt.Color(255, 255, 255));
        ANameTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tipo:");

        ABreedTextField.setBackground(new java.awt.Color(255, 255, 255));
        ABreedTextField.setForeground(new java.awt.Color(0, 0, 0));


        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Descripción:");

        ADescriptionTextArea.setBackground(new java.awt.Color(255, 255, 255));
        ADescriptionTextArea.setColumns(20);
        ADescriptionTextArea.setForeground(new java.awt.Color(0, 0, 0));
        ADescriptionTextArea.setLineWrap(true);
        ADescriptionTextArea.setRows(5);
        jScrollPane4.setViewportView(ADescriptionTextArea);

        AImageTextField.setBackground(new java.awt.Color(255, 255, 255));
        AImageTextField.setForeground(new java.awt.Color(0, 0, 0));

        AImageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AImageTextFieldKeyTyped(evt);
            }
        });

        ATypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ATypeComboBox.setForeground(new java.awt.Color(0, 0, 0));
        ATypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato" }));

        AStateComboBox.setBackground(new java.awt.Color(255, 255, 255));
        AStateComboBox.setForeground(new java.awt.Color(0, 0, 0));
        AStateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En adopción", "Adoptado" }));


        ASexComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ASexComboBox.setForeground(new java.awt.Color(0, 0, 0));
        ASexComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hembra", "Macho" }));

        AWeigthTextField.setBackground(new java.awt.Color(255, 255, 255));
        AWeigthTextField.setForeground(new java.awt.Color(0, 0, 0));

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

        AddAnimalButton.setBackground(new java.awt.Color(0, 0, 0));
        AddAnimalButton.setForeground(new java.awt.Color(255, 255, 255));
        AddAnimalButton.setText("Agregar mascota");
        AddAnimalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAnimalButtonActionPerformed(evt);
            }
        });

        AImagelLabel.setText("jLabel21");

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

        AAgeTextField.setBackground(new java.awt.Color(255, 255, 255));
        AAgeTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Edad:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel9))
                                        .addGap(2, 2, 2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(EAgeTextField)
                                    .addComponent(EWeightTextField)
                                    .addComponent(ENameTextField)
                                    .addComponent(EBreedTextField)
                                    .addComponent(ETypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ESexComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EAdoptionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(EImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(EUpdateButton))))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ATypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(ANameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel22)
                                                .addComponent(jLabel23)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ABreedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ASexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AWeigthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(AddAnimalButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel20))
                                            .addComponent(jLabel26))
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(AAgeTextField)
                                            .addComponent(AStateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AImagelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(welcomeTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutBtn))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(226, 226, 226)
                        .addComponent(CheckBoxDog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CheckBoxCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BreedTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
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
                    .addComponent(BreedTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(CheckBoxDog)
                    .addComponent(CheckBoxCat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(ENameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(AImagelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel16)
                                                .addComponent(ANameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(4, 4, 4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel17)
                                                .addComponent(ATypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel24)
                                                .addComponent(ASexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(4, 4, 4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel23)
                                                .addComponent(ABreedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(3, 3, 3)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel22)
                                                .addComponent(AWeigthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(4, 4, 4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel20)
                                                .addComponent(AStateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(ESexComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(EBreedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(EWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(EAdoptionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(EAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(EImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(AAgeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(AImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EUpdateButton)
                            .addComponent(AddAnimalButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(EIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(ETypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ENameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ENameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ENameTextFieldActionPerformed

    private void AImageTextFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_AImageTextFieldKeyTyped
        String imgUrl = AImageTextField.getText();

        try {
            URL image = URI.create(imgUrl).toURL();

            Icon icon = new ImageIcon(
                    new ImageIcon(image)
                            .getImage()
                            .getScaledInstance(this.AImagelLabel.getWidth(),
                                    this.AImagelLabel.getHeight(), 0));

            this.AImagelLabel.setIcon(icon);
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }// GEN-LAST:event_AImageTextFieldKeyTyped

    private void AddAnimalButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AddAnimalButtonActionPerformed
        String name = ANameTextField.getText();
        String breed = ABreedTextField.getText();
        String weight = AWeigthTextField.getText();
        String age = AAgeTextField.getText();
        String type = ATypeComboBox.getSelectedItem().toString();
        String sex = ASexComboBox.getSelectedItem().toString();
        String adoptionState = AStateComboBox.getSelectedItem().toString();
        String imgUrl = AImageTextField.getText();
        String description = ADescriptionTextArea.getText();

        String normalizedAdoptionStatus = Normalizer.normalize(adoptionState, Normalizer.Form.NFD);
        normalizedAdoptionStatus = normalizedAdoptionStatus.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        String finalTypeInDB = type.equals("Perro") ? "DOG" : type.equals("Gato") ? "CAT" : null;
        String finalSexInDB = sex.equals("Hembra") ? "FEMALE" : sex.equals("Macho") ? "MALE" : null;
        String finalAdoptionStatusInDB = normalizedAdoptionStatus.equals("Adoptado") ? "ADOPTED"
                : normalizedAdoptionStatus.equals("En adopcion") ? "IN ADOPTION" : null;
        Integer finalAge = Integer.parseInt(age);
        float finalWeight = Float.parseFloat(weight);

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        } else if (breed.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza no puede estar vacía");
        } else if (finalWeight <= 0 || weight.trim().isEmpty()) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        } else if (finalAge <= 0 || age.trim().isEmpty()) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0");
        } else if (finalTypeInDB == null || finalTypeInDB.trim().isEmpty() || type.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo no puede estar vacío");
        } else if (finalSexInDB == null || finalSexInDB.trim().isEmpty() || sex.trim().isEmpty()) {
            throw new IllegalArgumentException("El sexo no puede estar vacío");
        } else if (finalAdoptionStatusInDB == null || finalAdoptionStatusInDB.trim().isEmpty()
                || adoptionState.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de adopción no puede estar vacío");
        } else if (imgUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL de la imagen no puede estar vacía");
        } else if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripci&oacute;n no puede estar vacía");
        } else {

            AnimalBase animal = new AnimalBase(name, finalTypeInDB, finalAge, breed, finalSexInDB, finalWeight,
                    description, finalAdoptionStatusInDB, null, imgUrl);
            boolean result = animalController.addAnimal(animal);

            if (result) {
                updateAnimalTable(animalController.getAllAnimals(null, null));
                clearAddAnimalForm();
                AImagelLabel.setIcon(null);
                System.out.println("animal agregado correctamente");
            } else {
                System.out.println("Error al agregar el animal");
            }
        }
    }// GEN-LAST:event_AddAnimalButtonActionPerformed

    private void EUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EUpdateButtonActionPerformed
        String query = EIdTextField.getText();
        Animal beforeAnimal = null;

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
            updateAnimalTable(animalController.getAnimalsByType("DOG", null));
        } else {
            updateAnimalTable(animalController.getAllAnimals(null, null));
        }
    }// GEN-LAST:event_CheckBoxDogActionPerformed

    private void CheckBoxCatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CheckBoxCatActionPerformed
        if (CheckBoxCat.isSelected()) {
            CheckBoxDog.setSelected(false);
            updateAnimalTable(animalController.getAnimalsByType("CAT", null));
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
    private javax.swing.JTextField AAgeTextField;
    private javax.swing.JTextField ABreedTextField;
    private javax.swing.JTextArea ADescriptionTextArea;
    private javax.swing.JTextField AImageTextField;
    private javax.swing.JLabel AImagelLabel;
    private javax.swing.JTextField ANameTextField;
    private javax.swing.JComboBox<String> ASexComboBox;
    private javax.swing.JComboBox<String> AStateComboBox;
    private javax.swing.JComboBox<String> ATypeComboBox;
    private javax.swing.JTextField AWeigthTextField;
    private javax.swing.JButton AddAnimalButton;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTable tableofAnimals;
    private javax.swing.JLabel welcomeTxt;
    // End of variables declaration//GEN-END:variables
}
