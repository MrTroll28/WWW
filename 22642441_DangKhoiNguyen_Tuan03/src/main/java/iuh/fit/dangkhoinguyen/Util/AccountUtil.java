package iuh.fit.dangkhoinguyen.Util;

import iuh.fit.dangkhoinguyen.Model.Account;
import iuh.fit.dangkhoinguyen.Model.Gender;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUtil {
    private DataSource datasource;

    public AccountUtil(DataSource datasource) throws Exception {
        this.datasource = datasource;
    }

    /*GET Account*/
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = datasource.getConnection();
            String sql = "SELECT * FROM accounts ORDER BY ID";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String fname = rs.getString("FIRSTNAME");
                String lname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                Date dateofbirth = rs.getDate("DATEOFBIRTH");
                Account acc = new Account(fname, lname, email, password, dateofbirth);
                accounts.add(acc);
                }
            } catch (Exception e) {
            throw new RuntimeException(e);
            }
        return accounts;
    }

    /*ADD Account*/
    public void addAccount(Account acc) throws Exception {
        String sql = "INSERT INTO accounts (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATEOFBIRTH) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getFirstName());
            ps.setString(2, acc.getLastName());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPassword());
            ps.setDate(5, (Date) acc.getDateOfBirth());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
