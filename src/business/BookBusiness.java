package business;

import util.ConnectionDB;
import entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness implements IBusiness<Book, Integer> {
    // IBusiness<Book, Integer> => Integer kiểu dữ liệu của primary key

    // Deploy abstract methods
    @Override
    public List<Book> finAll() {
        // Initialize the connection object
        Connection conn = ConnectionDB.openConnection();
        // Use CallableStatement to get data
        CallableStatement callSt = null;
        List<Book> listBook = null;

        try {
            callSt = conn.prepareCall("{call get_all_book()}");
            // prepareCall => call procedure truyền vào string để gọi procedure bao quanh {call procedure_name(param..)}
            // prepareCall bắt try/catch
            // Thực hiện gọi procedure return ra 1 đối tượng result
            ResultSet rs = callSt.executeQuery();
            // => Trả về 1 listbook
            listBook = new ArrayList<>(); // => Khởi tạo đối tượng listBook
            // Lấy dữ liệu rs đổ vào listBook
            // rs.next() duyệt từng line rs
            while (rs.next()) {
                Book book = new Book(); //=> Khỏi tạo đối tượng book để thêm dữ liệu
                book.setBookId(rs.getInt("id")); // => set vào đối tượng book get theo kiểu dữ liệu của cột, "id" name column
                book.setBookName(rs.getString("name"));
                book.setPrice(rs.getFloat("price"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("book_status"));
                listBook.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn); // conn -> Đối tượng truyền vào
        }

        return listBook;
    }

    @Override
    public boolean creat(Book book) {
        // use try/catch
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false); // => Nếu như khi thêm với sợ có exception xảy ra -> rollback lại
            callSt = conn.prepareCall("{call create_book(?,?,?,?,?)}"); // create_book(?,?,?,?,?) The passed params are represented by the ?
            //set giá trị cho các tham số vào
            //Đăng ký kiểu dữ liệu cho các tham số ra
            callSt.setString(1, book.getBookName());
            callSt.setFloat(2, book.getPrice());
            callSt.setString(3, book.getTitle());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.isStatus());
            //Thực hiện gọi procedure
            callSt.executeUpdate(); // K có param trả ra sử dụng executeUpdate() sau khi thực hiện xong nó sẽ trả về đối tượng book vừa thêm mới
            conn.commit(); //
            result = true; // Biến trả về
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback(); // Nếu như có lỗi sảy ra
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean update(Book book) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        // trong 1 connection chúng ta dùng nhiều CallableStatement để thực hiện gọi nhiều procedure được
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            //1. Kiểm tra bookId có tồn tại không
            callSt1 = conn.prepareCall("{call get_book_by_id(?,?)}"); // call procedure get_book_by_id
            callSt1.setInt(1, book.getBookId());
            // Đăng ký tham số trả ra
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute(); // => Thực hiện gọi procedure
            // Lấy giá trị tham số trả ra
            int cnt_book = callSt1.getInt(2);
            if (cnt_book > 0) {
                //2. Thực hiện cập nhật
                callSt2 = conn.prepareCall("{call update_book(?,?,?,?,?,?)}");
                //set giá trị cho các tham số vào
                callSt2.setInt(1, book.getBookId());
                callSt2.setString(2, book.getBookName());
                callSt2.setFloat(3, book.getPrice());
                callSt2.setString(4, book.getTitle());
                callSt2.setString(5, book.getAuthor());
                callSt2.setBoolean(6, book.isStatus());
                //Đăng ký kiểu dữ liệu cho các tham số ra
                //Thực hiện gọi procedure
                callSt2.executeUpdate();
            } else {
                result = false;
            }
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean delete(Integer bookId) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            // 1. Kiểm tra bookId có tồn tại không
            callSt1 = conn.prepareCall("{call get_book_by_id(?,?)}");
            callSt1.setInt(1, bookId);
            // Đăng ký tham số trả ra
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            // Lấy giá trị tham số trả ra
            int cnt_book = callSt1.getInt(2);
            if (cnt_book > 0) {
                // 2. Thực hiện cập nhật
                callSt2 = conn.prepareCall("{call delete_book(?)}");
                //set giá trị cho các tham số vào
                callSt2.setInt(1, bookId);
                // Đăng ký kiểu dữ liệu cho các tham số ra
                // Thực hiện gọi procedure
                callSt2.executeUpdate();
            } else {
                result = false;
            }
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Book findById(Integer integer) {
        return null;
    }


}
