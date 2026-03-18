package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {

    Choice choiceEMP;

    JTable table;

    JButton searchbtn, print, update, back, add, delete;

    View_Employee() {

        setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(44,62,80));
        header.setBounds(5,5,975,70);
        header.setLayout(null);
        add(header);

        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI",Font.BOLD,22));
        heading.setBounds(310,20,750,30);
        header.add(heading);

        JLabel search = new JLabel("Search Employee ID:");
        search.setFont(new Font("Segoe UI",Font.BOLD,18));
        search.setBounds(75-45,130-40,180,30);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(270-45,137-40,180,60);
        choiceEMP.setFont(new Font("Arial", Font.PLAIN, 15));
        add(choiceEMP);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(460-45,133-40,85,30);
        searchbtn.addActionListener(this);
        add(searchbtn);

        JPanel header1 = new JPanel();
        header1.setBackground(new Color(216, 220, 227));
        header1.setBounds(5,71,975,70);
        header1.setLayout(null);
        add(header1);

        try {
            conn c = new conn();
            PreparedStatement ps = c.connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {
            conn c = new conn();
            PreparedStatement ps = c.connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = ps.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setRowHeight(30);
        table.getTableHeader().setPreferredSize(new Dimension(120,35));


        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(10,160,968,325);
        add(jp);

        add = new JButton("Add");
        add.setBounds(20,500,140,40);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(180,500,140,40);
        update.addActionListener(this);
        add(update);

        delete = new JButton("Delete");
        delete.setBounds(340,500,140,40);
        delete.addActionListener(this);
        add(delete);

        print = new JButton("Print");
        print.setBounds(700,500,120,40);
        print.addActionListener(this);
        add(print);

        back = new JButton("Back");
        back.setBounds(840,500,120,40);
        back.addActionListener(this);
        add(back);


        setSize(1000,600);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchbtn){
            String query = "SELECT * FROM employee WHERE  empid = ?";
            try{
                conn c = new conn();

                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, choiceEMP.getSelectedItem().toString());
                ResultSet resultSet = ps.executeQuery();

                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            }catch (Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == print){
            try{
                table.print();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }else if(e.getSource() == back){
            setVisible(false);
            new Main_Class();
        }
        else if(e.getSource() == add){
            setVisible(false);
            new AddEmployee();
        }else if(e.getSource() == update){

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null, "Select employee First");
                return;
            }

            setVisible(false);

            String empId = table.getValueAt(row, 10).toString();

            new Update_Employee(empId);
        }
        else if(e.getSource() == delete){
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(null, "Select employee first");
            }

            String empId = table.getValueAt(row, 10).toString();

            try{
                conn c = new conn();
                String query = "DELETE FROM employee WHERE empid = ?";

                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, empId);
                ps.executeUpdate();

                ResultSet resultSet = c.connection.createStatement().executeQuery("SELECT * FROM employee");
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

                JOptionPane.showMessageDialog(null,"Employee Deleted");

            }catch(Exception eE){
                eE.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }
}
