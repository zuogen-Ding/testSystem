package club.banyuan.project.view;


import club.banyuan.project.pojo.Question;
import club.banyuan.project.controller.Send;
import club.banyuan.project.pojo.Student;
import club.banyuan.project.util.PojoUtil;


import javax.swing.*;


public class Examination extends javax.swing.JFrame {
    static Examination examination;
    static int count = 1;
    static int totalGrade = 0;
    static String string = null;
    static Question question = null;
    static String[] strings = new String[3];
    static String[] stuAnswer = new String[3];
    static String[] answer = new String[3];

    /**
     * Creates new form Examination
     */
    public Examination() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("总时长120分钟");

        jButton1.setText("开始考试");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (jTextArea1.getText() != null) {
                    Send.sendQuestion(PojoUtil.getQuestion());
                    question = PojoUtil.getQuestion();
                    string = "第" + count + "题" + question.toString();
                    strings[0] = string;
                    jTextArea1.setText(string);
                    answer[0] = question.getQueAnswer();

                }
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("A");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("B");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("C");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("D");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jButton3.setText("下一题");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (count != 3) {
                    string = null;
                    if (strings[count] == null) {
                        Send.sendQuestion(PojoUtil.getQuestion());
                        question = PojoUtil.getQuestion();
                        string = "第" + (count + 1) + "题" + question.toString();
                        strings[count] = string;
                        answer[count] = question.getQueAnswer();
                    } else {
                        string = strings[count];
                    }
                    jTextArea1.setText(string);
                    if (jRadioButton1.isSelected()) {
                        stuAnswer[count - 1] = "A";
                        jRadioButton1.setSelected(false);
                    }
                    if (jRadioButton2.isSelected()) {
                        stuAnswer[count - 1] = "B";
                        jRadioButton2.setSelected(false);
                    }
                    if (jRadioButton3.isSelected()) {
                        stuAnswer[count - 1] = "C";
                        jRadioButton3.setSelected(false);
                    }
                    if (jRadioButton4.isSelected()) {
                        stuAnswer[count - 1] = "D";
                        jRadioButton4.setSelected(false);
                    }
                    count++;
                } else {

                    JOptionPane.showMessageDialog(null, "已经是最后一题了");
                }

            }
        });

        jButton2.setText("上一题");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (count != 1) {
                    string = strings[count - 2];
                    jTextArea1.setText(string);
                    if (jRadioButton1.isSelected()) {
                        stuAnswer[count - 1] = "A";
                    }
                    if (jRadioButton2.isSelected()) {
                        stuAnswer[count - 1] = "B";
                    }
                    if (jRadioButton3.isSelected()) {
                        stuAnswer[count - 1] = "C";
                    }
                    if (jRadioButton4.isSelected()) {
                        stuAnswer[count - 1] = "D";
                    }
                    count--;
                } else {
                    JOptionPane.showMessageDialog(null, "已经是第一题了");

                }

            }
        });

        jLabel2.setText(PojoUtil.getTestName());

        jButton4.setText("提交");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PojoUtil.getStudent().setStuTotalScore(5);
                if (count == 3) {
                    if (jRadioButton1.isSelected()) {
                        stuAnswer[count - 1] = "A";
                    }
                    if (jRadioButton2.isSelected()) {
                        stuAnswer[count - 1] = "B";
                    }
                    if (jRadioButton3.isSelected()) {
                        stuAnswer[count - 1] = "C";
                    }
                    if (jRadioButton4.isSelected()) {
                        stuAnswer[count - 1] = "D";
                    }
                }

                for (int i = 0; i < answer.length; i++) {
                    if (stuAnswer[i] != null) {
                        if (stuAnswer[i] == answer[i]) {
                            totalGrade += 10;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "第" + (i + 1) + "题没有作答");
                    }
                }
                Student student = PojoUtil.getStudent();

                if (jLabel2.getText().equals("java")) {
                    student.setJava(totalGrade);
                }
                if (jLabel2.getText().equals("databases")) {
                    student.setDatabase(totalGrade);
                }
                if (jLabel2.getText().equals("python")) {
                    student.setPython(totalGrade);
                }

                Send.send(PojoUtil.getStudent());
                JOptionPane.showMessageDialog(null, "本场考试" + totalGrade + "分,考试结束");
                dispose();
                StudentMain.startUI();

            }
        });


        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jRadioButton1)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jRadioButton2)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jRadioButton3)
                                                                .addGap(5, 5, 5)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jRadioButton4)
                                                                                .addGap(30, 30, 30)
                                                                                .addComponent(jButton2)
                                                                                .addGap(37, 37, 37)
                                                                                .addComponent(jButton3))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(35, 35, 35)
                                                                                .addComponent(jButton4))))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addComponent(jLabel2)
                                                .addGap(86, 86, 86)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(54, 54, 54)
                                                .addComponent(jButton1)))
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2))
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton2)
                                        .addComponent(jRadioButton3)
                                        .addComponent(jRadioButton4)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();

    }// </editor-fold>


    public static void startUI() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Examination.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Examination.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Examination.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Examination.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (examination == null) {
                    synchronized (Login.class) {
                        if (examination == null) {
                            examination = new Examination();
                            examination.setVisible(true);
                        }
                    }
                } else {
                    examination.setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}