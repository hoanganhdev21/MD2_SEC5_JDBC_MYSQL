package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    // The 2 constants contain the following information
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/fresher_book_management";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";

    // Cho phép mở connection -> return đối tượng connection để làm việc
    public static Connection openConnection() {
        // 1. set connection
        Connection conn = null;
        try {
            // set Driver cho DriverManager
            Class.forName(DRIVER); // => có thể xảy ra exception -> bắt lỗi
            // Khởi tạo đối tượng connection từ Driver Manager
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // => truyền vào 3 đối số

        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e); // => Không muốn dừng chương trình thì không ủuwr dụng throw new
            e.printStackTrace(); // => In ra
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn; // => trả về connection
        // => Khi trả về null sẽ bị lỗi chương trình khác null => thành công
    }

    // close connection
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close(); // => bắt try/catch
            } catch (SQLException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    // Test connection database
//    public static void main(String[] args) {
//        Connection conn = openConnection();
//        if(conn == null){
//            System.err.println("Connection Database Error !");
//        }else{
//            System.out.println("Connection Database Success !");
//        }
//    }

}
