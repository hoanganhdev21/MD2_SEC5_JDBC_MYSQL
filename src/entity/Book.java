package entity;

import java.util.Scanner;

public class Book {
    private int bookId;
    private String bookName;
    private float price;
    private String title;
    private String author;
    private boolean status; // => bit MySQL tương đương với boolean in java

    // create constructor
    public Book() {
    }

    public Book(int bookId, String bookName, float price, String title, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    // create get set

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        System.out.println("Nhập tên sách:");
        this.bookName = scanner.nextLine();
        System.out.println("Nhập giá sách:");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập tiêu đề sách:");
        this.title = scanner.nextLine();
        System.out.println("Nhập tác giả:");
        this.author = scanner.nextLine();
        System.out.println("Nhập trạng thái sách:");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("Mã sách: %d - Tên sách: %s - Giá: %f\n", this.bookId, this.bookName, this.price);
        System.out.printf("Tiêu đề: %s - Tác giả: %s - Trạng thái: %s\n", this.title, this.author, this.status ? "Hoạt động" : "Không hoạt động");
    }
}
