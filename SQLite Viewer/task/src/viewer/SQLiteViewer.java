package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class SQLiteViewer extends JFrame {

    public static File dbFile;
    public static List<String> tablesList = new ArrayList<>();

    public static List<String> columns = new ArrayList<>();
    public static List<ArrayList<String>> rows = new ArrayList<>();

    public static JComboBox<String> tablesBox;
    public static JTextArea query;
    public static JTable resultTable;
    public static JButton execute;

    public SQLiteViewer() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SQLite Viewer");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 700);
        panel.setLayout(null);
        add(panel);

        JTextField fileName = new JTextField();
        fileName.setName("FileNameTextField");
        fileName.setBounds(20, 20, 630, 30);
        add(fileName);
        panel.add(fileName);

        JButton open = new JButton();
        open.setName("OpenFileButton");
        open.setText("Open");
        open.setBounds(660, 20, 100, 30);
        add(open);
        panel.add(open);

        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                File file = new File(fileName.getText());

                if (!file.exists()) {
                    showMessageDialog(new Frame(), "The file doesn't exist!");
                    execute.setEnabled(false);
                    return;
                }

                if (file.getName().endsWith(".db")) {

                    fileName.setText(file.getName());
                    dbFile = file;

                    tablesBox.removeAllItems();
                    tablesList.clear();

                    DB db = new DB();
                    db.getTablesList();

                    for (String item : tablesList) {
                        tablesBox.addItem(item);
                    }

                    query.setText("SELECT * FROM " + tablesBox.getSelectedItem() + ";");

                    execute.setEnabled(true);

                } else {
                    showMessageDialog(new Frame(), "The file doesn't have .db extension");
                    execute.setEnabled(false);
                }
            }

        });

        tablesBox = new JComboBox<>();
        tablesBox.setName("TablesComboBox");
        tablesBox.setBounds(20, 70, 740, 30);
        add(tablesBox);
        panel.add(tablesBox);

        query = new JTextArea();
        query.setName("QueryTextArea");
        query.setBounds(20, 110, 630, 50);
        query.setEnabled(false);
        add(query);
        panel.add(query);
        tablesBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                query.setText("SELECT * FROM " + tablesBox.getSelectedItem() + ";");
            }
        });

        execute = new JButton();
        execute.setName("ExecuteQueryButton");
        execute.setText("Execute");
        execute.setEnabled(false);
        execute.setBounds(660, 120, 100, 30);
        add(execute);
        panel.add(execute);

        execute.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DB db = new DB();
                db.getTableData((String) tablesBox.getSelectedItem());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columns.toArray());

                for (ArrayList<String> row : rows) {
                    model.addRow(row.toArray());
                }

                resultTable.setModel(model);
            }
        });

        resultTable = new JTable();
        resultTable.setName("Table");
        resultTable.setBounds(20, 170, 750, 475);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollTable = new JScrollPane(resultTable);
        scrollTable.setBounds(20, 170, 750, 475);
        scrollTable.setVisible(true);
        scrollTable.setPreferredSize(new Dimension());
        add(scrollTable);
        panel.add(scrollTable);

        setVisible(true);

    }
}
