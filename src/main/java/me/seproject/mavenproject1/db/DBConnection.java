package me.seproject.mavenproject1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_URL  = "jdbc:derby:SEProjectDB;create=true";
    private static final String USER    = "bue";
    private static final String PASS    = "bue";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Derby driver not found", e);
            }
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // STUDENTS
            stmt.executeUpdate(
                "CREATE TABLE BUE.STUDENTS (" +
                "studentID VARCHAR(50) PRIMARY KEY," +
                "name VARCHAR(100)," +
                "email VARCHAR(100)," +
                "phoneNumber VARCHAR(20)," +
                "username VARCHAR(50)," +
                "password VARCHAR(100)," +
                "role VARCHAR(20)," +
                "financialAidStatus BOOLEAN," +
                "absenceThreshold INT" +
                ")"
            );

            // STAFF
            stmt.executeUpdate(
                "CREATE TABLE BUE.STAFF (" +
                "staffID VARCHAR(50) PRIMARY KEY," +
                "name VARCHAR(100)," +
                "email VARCHAR(100)," +
                "phoneNumber VARCHAR(20)," +
                "username VARCHAR(50)," +
                "password VARCHAR(100)," +
                "position VARCHAR(50)" +
                ")"
            );

            // PROFESSORS
            stmt.executeUpdate(
                "CREATE TABLE BUE.PROFESSORS (" +
                "professorID VARCHAR(50) PRIMARY KEY," +
                "name VARCHAR(100)," +
                "email VARCHAR(100)," +
                "phoneNumber VARCHAR(20)," +
                "username VARCHAR(50)," +
                "password VARCHAR(100)," +
                "department VARCHAR(100)" +
                ")"
            );

            // MODULES
            stmt.executeUpdate(
                "CREATE TABLE BUE.MODULES (" +
                "moduleID VARCHAR(50) PRIMARY KEY," +
                "moduleName VARCHAR(100)," +
                "credits INT," +
                "capacity INT" +
                ")"
            );

            // GRADES
            stmt.executeUpdate(
                "CREATE TABLE BUE.GRADES (" +
                "gradeID VARCHAR(50) PRIMARY KEY," +
                "studentID VARCHAR(50)," +
                "moduleID VARCHAR(50)," +
                "gradeValue VARCHAR(10)," +
                "semester VARCHAR(20)," +
                "academicYear INT" +
                ")"
            );

            // TRANSCRIPTS
            stmt.executeUpdate(
                "CREATE TABLE BUE.TRANSCRIPTS (" +
                "transcriptID VARCHAR(50) PRIMARY KEY," +
                "studentID VARCHAR(50)," +
                "generationDate TIMESTAMP," +
                "cumulativeGPA FLOAT," +
                "honors VARCHAR(50)" +
                ")"
            );

            // PAYMENTS
            stmt.executeUpdate(
                "CREATE TABLE BUE.PAYMENTS (" +
                "paymentID VARCHAR(50) PRIMARY KEY," +
                "studentID VARCHAR(50)," +
                "amount DOUBLE," +
                "paymentMethod VARCHAR(50)," +
                "paymentDate TIMESTAMP," +
                "status VARCHAR(20)," +
                "transactionID VARCHAR(100)" +
                ")"
            );

            // RECEIPTS
            stmt.executeUpdate(
                "CREATE TABLE BUE.RECEIPTS (" +
                "receiptID VARCHAR(50) PRIMARY KEY," +
                "paymentID VARCHAR(50)," +
                "studentID VARCHAR(50)," +
                "amount FLOAT," +
                "paymentDate TIMESTAMP," +
                "paymentMethod VARCHAR(50)," +
                "transactionID VARCHAR(100)" +
                ")"
            );

            // ATTENDANCE_RECORDS
            stmt.executeUpdate(
                "CREATE TABLE BUE.ATTENDANCE_RECORDS (" +
                "recordID VARCHAR(50) PRIMARY KEY," +
                "studentID VARCHAR(50)," +
                "moduleID VARCHAR(50)," +
                "date DATE," +
                "timeSlot VARCHAR(50)," +
                "status VARCHAR(20)," +
                "isFlagged BOOLEAN" +
                ")"
            );

            // SAVED_PAYMENT_METHODS
            stmt.executeUpdate(
                "CREATE TABLE BUE.SAVED_PAYMENT_METHODS (" +
                "savedMethodID VARCHAR(50) PRIMARY KEY," +
                "studentID VARCHAR(50)," +
                "methodType VARCHAR(50)," +
                "nickname VARCHAR(100)," +
                "last4digits VARCHAR(4)," +
                "isDefault BOOLEAN" +
                ")"
            );

            System.out.println("Database tables created successfully.");

        } catch (SQLException e) {
            if ("X0Y32".equals(e.getSQLState())) {
                System.out.println("Database already initialized.");
            } else {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
                if (e.getNextException() != null) {
                    System.err.println("Next exception: ");
                    e.getNextException().printStackTrace();
                }
            }
        }
    }
}
