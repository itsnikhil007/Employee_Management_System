package employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Update_Employee extends JFrame implements ActionListener{

    JLabel empIDLabel, empIDValue, name, fname, dob, salary, address, phone, email, education, designation, aadhar;

    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;

    JButton update, back;

    ComboBoxModel<Object> Boxeducation;

    JComboBox<String> boxEducation;

    JDateChooser tdob;


    String empId;

    Update_Employee(String empId) {
        this.empId = empId;
        setLayout(null);

        setTitle("Update Employee");
        setSize(620, 620);
        setLocation(400, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel header = new JPanel();
        header.setBackground(new Color(44, 62, 80));
        header.setBounds(1, 0, 700, 60);
        header.setLayout(null);
        add(header);

        JLabel title = new JLabel("UPDATE EMPLOYEE DETAILS");
        title.setBounds(10+165, 15, 400, 35);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);

        empIDLabel = new JLabel("Employee ID");
        empIDLabel.setBounds(10+40, 90-15, 140, 28);
        empIDLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(empIDLabel);

        empIDValue = new JLabel("429962");
        empIDValue.setBounds(10+200, 90-15, 350, 28);
        empIDValue.setFont(new Font("SansSerif", Font.BOLD, 15));
        add(empIDValue);

        name = new JLabel("Name");
        name.setBounds(10+40, 130-15, 140, 28);
        name.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(name);

        tname = new JTextField();
        tname.setBounds(10+200, 130-15, 350, 28);
        add(tname);

        fname = new JLabel("Father's Name");
        fname.setBounds(10+40, 170-15, 140, 28);
        fname.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(10+200, 170-15, 350, 28);
        add(tfname);

        dob = new JLabel("Date of Birth");
        dob.setBounds(10+40, 210-15, 140, 28);
        dob.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(10+200, 210-15, 350, 28);
        add(tdob);

        salary = new JLabel("Salary");
        salary.setBounds(10+40, 250-15, 140, 28);
        salary.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(10+200, 250-15, 350, 28);
        add(tsalary);

        address = new JLabel("Address");
        address.setBounds(10+40, 290-15, 140, 28);
        address.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(10+200, 290-15, 350, 28);
        add(taddress);

        phone = new JLabel("Phone");
        phone.setBounds(10+40, 330-15, 140, 28);
        phone.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(10+200, 330-15, 350, 28);
        add(tphone);

        email = new JLabel("Email");
        email.setBounds(10+40, 370-15, 140, 28);
        email.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(email);

        temail = new JTextField();
        temail.setBounds(10+200, 370-15, 350, 28);
        add(temail);

        education = new JLabel("Education");
        education.setBounds(10+40, 410-15, 140, 28);
        education.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(education);

        String items[] = {"B.Tech","BBA","BCA","BA","BSC","B.COM","MBA","MCA","MA","MTech","MSC","PHD"};

        boxEducation = new JComboBox<>(items);
        boxEducation.setBounds(10+200, 410-15, 350, 28);
        add(boxEducation);

        designation = new JLabel("Designation");
        designation.setBounds(10+40, 450-15, 140, 28);
        designation.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(10+200, 450-15, 350, 28);
        add(tdesignation);

        aadhar = new JLabel("Aadhar");
        aadhar.setBounds(10+40, 490-15, 140, 28);
        aadhar.setFont(new Font("SansSerif", Font.BOLD, 13));
        add(aadhar);

        taadhar = new JTextField();
        taadhar.setBounds(10+200, 490-15, 350, 28);
        add(taadhar);

        update = new JButton("Update");
        update.setBounds(10+200, 540-15, 120, 35);
        update.setBackground(new Color(44, 62, 80));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(440, 540-15, 120, 35);
        add(back);

        try{
            conn c = new conn();

            String query = "SELECT * FROM employee WHERE empid = ?";
            PreparedStatement ps = c.connection.prepareStatement(query);
            ps.setString(1, empId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                empIDValue.setText(rs.getString("empid"));
                tname.setText(rs.getString("name"));
                tfname.setText(rs.getString("fname"));
                tsalary.setText(rs.getString("salary"));
                taddress.setText(rs.getString("address"));
                tphone.setText(rs.getString("phone"));
                temail.setText(rs.getString("email"));
                boxEducation.setSelectedItem(rs.getString("education"));
                tdesignation.setText(rs.getString("designation"));
                taadhar.setText(rs.getString("aadhar"));

                ((JTextField) tdob.getDateEditor().getUiComponent()).setText(rs.getString("dob"));
            }

        }catch(Exception e1){
            e1.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update){

            try {
                conn c = new conn();
                String name = tname.getText();
                String fname = tfname.getText();String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
                String salary = tsalary.getText();
                String address = taddress.getText();
                String phone = tphone.getText();
                String email = temail.getText();
                String education = (String) boxEducation.getSelectedItem();
                String designation = tdesignation.getText();
                String aadhar = taadhar.getText();

                if(name.isEmpty() || fname.isEmpty() || dob.isEmpty() || salary.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || designation.isEmpty() || aadhar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                }

                String query = "UPDATE employee SET name = ?, fname = ?, dob=?, salary=?, address=?, phone=?, email=?, education=?, designation=?, aadhar=? WHERE empid=?";

                PreparedStatement ps = c.connection.prepareStatement(query);
                ps.setString(1, name); ps.setString(2, fname); ps.setString(3, dob);
                ps.setString(4, salary); ps.setString(5, address); ps.setString(6, phone);
                ps.setString(7, email); ps.setString(8, education); ps.setString(9, designation);
                ps.setString(10, aadhar); ps.setString(11, empId);

                int result = ps.executeUpdate();

                if(result > 0){
                    JOptionPane.showMessageDialog(null, "Employee updated successfully");
                    setVisible(false);
                    new View_Employee();
                }else{
                    JOptionPane.showMessageDialog(null, "Update failed. Try again.");
                    setVisible(false);
                    new View_Employee();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update unsuccessful. Please try again later.");
                setVisible(false);
                new View_Employee();
            }
        }else{
            setVisible(false);
            new View_Employee();
        }
    }

    public static void main(String[] args) {

        new Update_Employee("429962");
    }
}