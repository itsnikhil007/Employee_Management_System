package employee.management.system;

import com.sun.tools.javac.Main;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;

    JLabel tempid, aadhar;

    JDateChooser tdob;

    JComboBox Boxeducation;

    JButton back, add;

    AddEmployee(){

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/add_employee.png"));
        Image i2 = i1.getImage().getScaledInstance(1050, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1000, 700);
        img.setLayout(null);
        add(img);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(350+20, 40-30, 400, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        img.add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(280+20, 150-30, 120, 30);
        name.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(name);

        tname = new JTextField();
        tname.setBounds(280+20, 180-30, 250, 35);
        tname.setBorder(BorderFactory.createLineBorder(new Color(180,180,180), 1, true));
        img.add(tname);

        JLabel faname = new JLabel("Father's Name");
        faname.setBounds(600+20, 150-30, 150, 30);
        faname.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(faname);

        tfname = new JTextField();
        tfname.setBounds(600+20, 180-30, 250, 35);
        img.add(tfname);

        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(280+20, 230-30, 150, 30);
        dob.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(280+20, 260-30, 250, 35);
        img.add(tdob);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(600+20, 230-30, 120, 30);
        salary.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600+20, 260-30, 250, 35);
        img.add(tsalary);

        JLabel address = new JLabel("Address");
        address.setBounds(280+20, 310-30, 120, 30);
        address.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(address);

        taddress = new JTextField();
        taddress.setBounds(280+20, 340-30, 250, 35);
        img.add(taddress);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(600+20, 310-30, 120, 30);
        phone.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(phone);

        tphone = new JTextField();
        tphone.setBounds(600+20, 340-30, 250, 35);
        img.add(tphone);

        JLabel email = new JLabel("Email");
        email.setBounds(280+20, 390-30, 120, 30);
        email.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(email);

        temail = new JTextField();
        temail.setBounds(280+20, 420-30, 250, 35);
        img.add(temail);

        JLabel education = new JLabel("Highest Education");
        education.setBounds(600+20, 390-30, 200, 30);
        education.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(education);

        String items[] = {"B.Tech","BBA","BCA","BA","BSC","B.COM","MBA","MCA","MA","MTech","MSC","PHD"};

        Boxeducation = new JComboBox<>(items);
        Boxeducation.setBounds(600+20, 420-30, 250, 35);
        img.add(Boxeducation);

        JLabel designation = new JLabel("Designation");
        designation.setBounds(280+20, 470-30, 150, 30);
        designation.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(280+20, 500-30, 250, 35);
        img.add(tdesignation);

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(87, 220, 150, 30);
        empid.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(empid);

        tempid = new JLabel("" + number);
        tempid.setBounds(100, 248, 150, 30);
        tempid.setFont(new Font("SansSerif", Font.BOLD, 18));
        tempid.setForeground(Color.RED);
        img.add(tempid);

        aadhar = new JLabel("Aadhar");
        aadhar.setBounds(600+20, 470-30, 150, 30);
        aadhar.setFont(new Font("SansSerif", Font.BOLD, 18));
        img.add(aadhar);

        taadhar = new JTextField();
        taadhar.setBounds(600+20, 500-30, 250, 35);
        img.add(taadhar);

        add = new JButton("Add");
        add.setBounds(590, 560, 140,40);
        add.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.setFocusPainted(false);
        add.addActionListener(this);
        img.add(add);

        back = new JButton("Back");
        back.setBounds(370, 560, 140,40);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setFocusPainted(false);
        back.setFont(new Font("Segoe UI", Font.BOLD, 15));
        back.addActionListener(this);
        img.add(back);

        styleField(tname);
        styleField(tfname);
        styleField(taddress);
        styleField(tphone);
        styleField(temail);
        styleField(tsalary);
        styleField(tdesignation);

        setPlaceholder(tname, "  Enter Name");
        setPlaceholder(tfname, "Enter Father's Name");
        setPlaceholder(taddress, "Enter Address");
        setPlaceholder(tphone, "Enter Phone");
        setPlaceholder(temail, "Enter Email");
        setPlaceholder(tsalary, "Enter Salary");
        setPlaceholder(tdesignation, "Enter Designation");
        setPlaceholder(taddress, "Enter Aadhar");

        setSize(1000, 700);
        setLocation(300, 50);
        setVisible(true);
        requestFocus();
    }

    void setPlaceholder(JTextField field, String text){

        field.setText(text);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter(){

            public void focusGained(java.awt.event.FocusEvent evt){
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt){
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    void styleField(JTextField field) {

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180,180,180),1,true),
                BorderFactory.createEmptyBorder(5,10,5,10)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createLineBorder(new Color(0,120,215),2,true));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createLineBorder(new Color(180,180,180),1,true));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
            String name = tname.getText();
            String fname = tfname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = (String) Boxeducation.getSelectedItem();
            String designation = tdesignation.getText();
            String empid = tempid.getText();
            String aadhar = taadhar.getText();

            try{
                conn conn = new conn();
                String query = "INSERT INTO employee(name, fname, dob, salary, address, phone, email, education, designation, aadhar, empid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.connection.prepareStatement(query);

                ps.setString(1, name);
                ps.setString(2, fname);
                ps.setString(3, dob);
                ps.setString(4, salary);
                ps.setString(5, address);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setString(8, education);
                ps.setString(9, designation);
                ps.setString(10, aadhar);
                ps.setString(11, empid);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Details added succesfully :)");
                setVisible(false);
                new Main_Class();

            }catch (Exception ee){
                ee.printStackTrace();
            }
        }else{
            setVisible(false);
            new Main_Class();
        }
    }

    public static void main(String[] args) {

        new AddEmployee();
    }
}