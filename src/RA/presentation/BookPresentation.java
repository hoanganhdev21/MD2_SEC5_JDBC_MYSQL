package RA.presentation;

import business.BookBusiness;
import business.IBusiness;
import entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookPresentation {
    // Khỏi tạo đối tượng bookBusiness
    private static IBusiness bookBusiness = new BookBusiness();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************Book Management**********");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Book> listBook = bookBusiness.finAll();
                    listBook.stream().forEach(book -> book.displayData());
                case 2:
                    Book book = new Book(); // Khởi tạo đối tượng book
                    book.inputData(scanner); // thêm mới
                    boolean resultCreate = bookBusiness.creat(book);
                    if (resultCreate){
                        System.out.println("Thêm mới thành công");
                    }else{
                        System.err.println("Thêm mới thất bại");
                    }
                    break;
                case 3:
                    System.out.println("Nhập vào mã sách cần cập nhật:");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    Book bookUpdate = new Book(); // Tạo đối tượng bookUpdate
                    bookUpdate.setBookId(bookId);
                    bookUpdate.inputData(scanner);
                    boolean resultUpdate = bookBusiness.update(bookUpdate);
                    if (resultUpdate){
                        System.out.println("Cập nhật thành công");
                    }else {
                        System.err.println("Không tồn tại mã sách hoặc có lỗi trong quá trình thực hiện");
                    }
                    break;
                case 4:
                    System.out.println("Nhập vào mã sách cần xóa:");
                    int bookIdDelete = Integer.parseInt(scanner.nextLine());
                    boolean resultDelete = bookBusiness.delete(bookIdDelete);
                    if (resultDelete){
                        System.out.println("Xóa thành công");
                    }else{
                        System.err.println("Mã sách không tồn tại hoặc có lỗi trong quá trình thực hiện");
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-5");
            }
        }while (true);
    }
}

//echo "# MySQL-JDBC" >> README.md
//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/hoanganhdev21/MySQL-JDBC.git
//git push -u origin main
