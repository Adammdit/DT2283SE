package ie.dit.miedziejewski.adam.dao;

import ie.dit.miedziejewski.adam.business.NextAppointment;
import ie.dit.miedziejewski.adam.business.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ie.dit.miedziejewski.adam.dao.Dao;
import ie.dit.miedziejewski.adam.exceptions.DaoException;

public class DAOImplementation extends Dao 
{
	public boolean addPatient(Patient patient) {
		return false;
	}

	public void updatePatient(NextAppointment patient) throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;       
              
        try {
            con = this.getConnection();
            String query = "UPDATE PATIENT SET DATE_TIME = ?, BOOKED = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, patient.dateTime);
            
            String ans = "n";
            if (patient.booked) {
            	ans = "y";
            }
            ps.setString(2, ans);
            ps.setString(3, patient.firstName);
            ps.setString(4, patient.lastName);           
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
            
            rs = ps.executeQuery();
            if (rs.next()) {
                dateTime = rs.getString("DATE_TIME");            
            }
        } catch (SQLException e) {
            throw new DaoException("findDateTimeAvailable" + e.getMessage());
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
                throw new DaoException("findDateTimeAvailable" + e.getMessage());
            }
        }
        return dateTime;
	}

	public boolean isPatientExist(String name) {
		return false;
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
            ps.setString(1, first);
            ps.setString(2, last);
            
            rs = ps.executeQuery();
            if (rs.next()) {
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
            else {
            	return null;
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
        return p;
	}

	public Patient findPatientsAppointmentsForDate(String date) {
		return null;
	}

	public void createPatient(Patient patient) {}
	
	public void fileAppointment(NextAppointment next) throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            
            String query = "INSERT INTO VISIT VALUES (null, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, next.firstName);
            ps.setString(2, next.lastName);
            ps.setString(3, next.dateTime);
            System.out.println(next.lastName);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException("findAppointment" + e.getMessage());
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
                throw new DaoException("findAppointment" + e.getMessage());
            }
        }
	}
}