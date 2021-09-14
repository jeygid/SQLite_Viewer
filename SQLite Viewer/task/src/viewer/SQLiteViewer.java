package viewer;

import javax.swing.*;

public class SQLiteViewer extends JFrame {

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

        JComboBox tables = new JComboBox();
        tables.setName("TablesComboBox");
        tables.setBounds(20, 70, 740, 30);
        add(tables);
        panel.add(tables);

        JTextArea query = new JTextArea();
        query.setName("QueryTextArea");
        query.setBounds(20, 110, 630, 120);
        add(query);
        panel.add(query);

        JButton execute = new JButton();
        execute.setName("ExecuteQueryButton ");
        execute.setText("Execute");
        execute.setBounds(660, 150, 100, 30);
        add(execute);
        panel.add(execute);

        setVisible(true);
    }
}
