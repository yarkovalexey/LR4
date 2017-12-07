
import javax.swing.*;
import java.awt.event.*;

public class Form extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textAreaOut;
    private JButton buttonAdd;
    private JTextArea textAreaIn;
    private JButton buttonEdit;
    private JButton buttonFind;
    private JButton buttonDel;
    private JButton buttonSort;

    private CarShop A;
    private Car X;

    public Form() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        A = new CarShop();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Buffer = textAreaIn.getText().trim();
                X = new Car();
                int space;
                try {
                    for (int i = 0; i < 6; i++) {
                        space = Buffer.indexOf(",");
                        switch (i) {
                            case 0: {
                                X.setID(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 1: {
                                X.setModel(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 2: {
                                X.setCountry(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 3: {
                                X.setYear(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 4: {
                                X.setV(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 5: {
                                X.setPrice(Integer.parseInt(Buffer.trim()));
                            }
                            break;
                        }
                        Buffer = Buffer.substring(space + 1);

                    }
                    A.addCar(X);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при добавлении\nФормат ввода данных:\nУникальный идентификатор, марка автомобиля, страна-производитель, год выпуска, объем двигателя, стоимость", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Buffer = textAreaIn.getText();
                X = new Car();
                int posID = 0;
                try {
                    for (int i = 0; i < 7; i++) {
                        int space = Buffer.indexOf(",");
                        switch (i) {
                            case 0: {
                                posID = Integer.parseInt(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 1: {
                                X.setID(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 2: {
                                X.setModel(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 3: {
                                X.setCountry(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 4: {
                                X.setYear(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 5: {
                                X.setV(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 6: {
                                X.setPrice(Integer.parseInt(Buffer.trim()));
                            }
                            break;
                        }
                        Buffer = Buffer.substring(space + 1);
                    }
                    A.editCarShop(posID, X);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при изменении\nФормат ввода данных:\nПозиция изменения, новый уникальный идентификатор, новая марка автомобиля, новая страна-производитель, новый год выпуска, новый объем двигателя, новая стоимость", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Buffer = textAreaIn.getText();
                    int posID = Integer.parseInt(Buffer.trim());
                    textAreaOut.setText(A.toString() + "\nЭлемент " + posID + ": " + A.findCar(posID));
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при поиске\nФормат ввода данных:\nПозиция элемента", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Buffer = textAreaIn.getText();
                    int posID = Integer.parseInt(Buffer.trim());
                    A.delCar(posID);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при удалении\nФормат ввода данных:\nПозиция элемента", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    A.bubbleSortCar();
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при сортировке\nДанные отсутсвуют", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Form dialog = new Form();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
