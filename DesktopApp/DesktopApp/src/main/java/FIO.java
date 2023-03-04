import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class FIO {
    private JPanel panel;
    private JCheckBox CheckBox;
    private JTextField surName;
    private JTextField lastName;
    private JTextField name;
    private JButton collapseButton;
    private JLabel familyLabel;
    private JLabel nameLabel;

    public FIO() {
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (collapseButton.getText().equals("Collapse")) {
                    clickCollapse();
                    return;
                }

                if (collapseButton.getText().equals("Expand")) {
                    clickExpand();
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setVisionField(boolean b) {
        surName.setVisible(b);
        name.setVisible(b);
        familyLabel.setVisible(b);
        nameLabel.setVisible(b);
        CheckBox.setVisible(b);
    }

    public void clickCollapse() {
        boolean check = (CheckBox.isSelected() && surName.getText().trim().equals(""))
                || surName.getText().trim().split(" ").length > 1
                || name.getText().trim().equals("")
                || name.getText().trim().split(" ").length > 1
                || lastName.getText().trim().equals("")
                || lastName.getText().trim().split(" ").length > 1;
        if (check) {
            JOptionPane.showMessageDialog(null,
                    "Фамилия и имя  - обязательные поля\n" +
                            "Отчество заполняется если отмечено",
                    "Ошибка заполнения", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        lastName.setText(lastName.getText().trim()
                + " " + name.getText().trim()
                + " " + surName.getText().trim());
        setVisionField(false);
        collapseButton.setText("Expand");
    }

    public void clickExpand() {
        String[] text = lastName.getText().split(" ");
        if (text.length < 2 || text.length > 3) {
            JOptionPane.showMessageDialog(null,
                    "ФИО должны быть разделены пробелами\n" +
                            "Отчество заполняется если имеется",
                    "Ошибка заполнения", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (text.length == 3) {
            lastName.setText(text[0]);
            name.setText(text[1]);
            surName.setText(text[2]);
            CheckBox.setSelected(true);
        } else {
            lastName.setText(text[0]);
            name.setText(text[1]);
            surName.setText(null);
            CheckBox.setSelected(false);
        }
        setVisionField(true);
        collapseButton.setText("Collapse");
    }
}
