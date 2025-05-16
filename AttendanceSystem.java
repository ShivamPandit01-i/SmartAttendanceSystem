import java.sql.*;
import java.util.Scanner;

public class AttendanceSystem {
    static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_db";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO attendance (student_id, date, status) VALUES (?, CURDATE(), 'Present')";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Attendance marked.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}