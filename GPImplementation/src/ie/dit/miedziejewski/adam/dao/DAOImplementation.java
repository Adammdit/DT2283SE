package ie.dit.miedziejewski.adam.dao;

import ie.dit.miedziejewski.adam.business.NextAppointment;
import ie.dit.miedziejewski.adam.business.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ie.dit.miedziejewski.adam.dao.Dao;
import ie.dit.miedziejewski.adam.business.Patient;
import ie.dit.miedziejewski.adam.exceptions.DaoException;

public class DAOImplementation extends Dao 
{
	public boolean addPatient(Patient patient) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public void updatePatient(NextAppointment patient) throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //NextAppointment p = null;
      //UPDATE table_name SET column1=value1,column2=value2,... WHERE some_column=some_value;
        
        
        String query = "UPDATE PATIENT SET DATE_TIME = ?, BOOKED = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
        try {
            con = this.getConnection();
            
            ps = con.prepareStatement(query);
            ps.setString(1, patient.dateTime); //ps.setString(1, uname);
            
            String ans = "n";
            if (patient.booked) {
            	ans = "y";
            }
            ps.setString(2, ans);
            ps.setString(3, patient.firstName); //ps.setString(1, uname);
            ps.setString(4, patient.lastName); //ps.setString(2, pword);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException("updatePatient" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("updatePatient" + e.getMessage());
            }
        }
        System.out.println(patient.firstName);
        System.out.println(patient.lastName);
        //System.out.println(ans);
        System.out.println(patient.dateTime);
        //return p;
	}

	public String findDateTimeAvailable() throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dateTime = null;
        
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM PATIENT WHERE DATE_TIME IN (SELECT max(DATE_TIME) FROM PATIENT)";

            ps = con.prepareStatement(query);
            //ps.setString(1, first); //ps.setString(1, uname);
            //ps.setString(2, last); //ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	int userId = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");
                String address = rs.getString("ADDRESS");
                int telNo = rs.getInt("TEL_NO");
                dateTime = rs.getString("DATE_TIME");
                String booked = rs.getString("BOOKED");
                
                           
            }
        } catch (SQLException e) {
            throw new DaoException("findDateTime" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findDateTime" + e.getMessage());
            }
        }
        System.out.println("DAO: "+dateTime); 
        return dateTime;
	}

	public boolean isPatientExist(String name) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}
	
	public NextAppointment findPatient(String first, String last) throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        NextAppointment p = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM PATIENT WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, first); //ps.setString(1, uname);
            ps.setString(2, last); //ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	int userId = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");
                String address = rs.getString("ADDRESS");
                int telNo = rs.getInt("TEL_NO");
                String dateTime = rs.getString("DATE_TIME");
                String booked = rs.getString("BOOKED");
                
                p = new NextAppointment(firstName, lastName, address, telNo);
                p.dateTime = dateTime;
                if (booked.equals("y")) {
                	p.booked = true;
                }
                else {
                	p.booked = false;
                }              
            }
        } catch (SQLException e) {
            throw new DaoException("findPatient" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findPatient" + e.getMessage());
            }
        }
        System.out.println(p.dateTime);
        return p;
	}

	public Patient findPatientsAppointmentsForDate(String date) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void createPatient(Patient patient) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}