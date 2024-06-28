package ra.bussiness;

import java.util.Scanner;

public class Book
{
    private int bookId;
    private String bookName, author, description;
    private double importPrice, exportPrice;
    private float interest;
    private boolean bookStatus = true;

    public Book()
    {
    }

    public Book(int bookId, String bookName, String author, String description, double importPrice, double exportPrice, float interest, boolean bookStatus)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getImportPrice()
    {
        return importPrice;
    }

    public void setImportPrice(double importPrice)
    {
        this.importPrice = importPrice;
    }

    public double getExportPrice()
    {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice)
    {
        this.exportPrice = exportPrice;
    }

    public float getInterest()
    {
        return interest;
    }

    public void setInterest(float interest)
    {
        this.interest = interest;
    }

    public boolean isBookStatus()
    {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus)
    {
        this.bookStatus = bookStatus;
    }

    public void inputData(Scanner scanner)
    {
        while (true)//Book name
        {
            System.out.println("Nhập tên sách (không được để trống) : ");
            this.bookName = scanner.nextLine().trim();
            if (!this.bookName.isBlank())
                break;
            System.out.println("Tên sách không được để trống.");
        }
        while (true)//Author
        {
            System.out.println("Nhập tên tác giả (không được để trống) : ");
            this.author = scanner.nextLine().trim();
            if (!this.author.isBlank())
                break;
            System.out.println("Không được để trống tên tác giả.");
        }
        while (true)//Book description
        {
            System.out.println("Nhập mô tả về sách : ");
            this.description = scanner.nextLine().trim();
            if (this.description.isBlank())
            {
                System.out.println("Mô tả về sách không được bỏ trống");
                continue;
            }
            if (this.description.length() < 10)
            {
                System.out.println("Mô tả về sách phải có ít nhất 10 ký tự");
                continue;
            }
            break;
        }
        while (true)//Import price
        {
            System.out.println("Giá nhập của sách này là: ");
            this.importPrice = Double.parseDouble(scanner.nextLine());
            if (this.importPrice <= 0)
            {
                System.out.println("Giá nhập phải lớn hơn 0 .");
            } else break;
        }
        while (true)//Export price
        {
            System.out.println("Giá bán của sách này là: ");
            this.exportPrice = Double.parseDouble(scanner.nextLine());
            if (this.exportPrice / this.importPrice < 1.2)
            {
                System.out.println("Giá bán phải lớn hơn giá nhập ít nhất 20%");
            } else break;
        }

        this.interest = (float) (this.exportPrice - this.importPrice);//Interest

        while (true)//Book status
        {
            System.out.println("Trạng thái của sách này là: (chỉ được nhập true hoặc false)");
            String tempStatus = scanner.nextLine();
            if (tempStatus.equalsIgnoreCase("true") || tempStatus.equalsIgnoreCase("false"))
            {
                this.bookStatus = Boolean.parseBoolean(tempStatus);
                break;
            } else
            {
                System.out.println("Vui lòng nhập đúng chữ true hoặc false");
            }
        }
    }

    public void displayData()
    {
        System.out.println("Mã sách: " + this.bookId);
        System.out.println("Tên sách: " + this.bookName);
        System.out.println("Tên tác giả: " + this.author);
        System.out.println("Mô tả về sách: " + this.description);
        System.out.println("Giá nhập sách: " + this.importPrice);
        System.out.println("Giá bán sách: " + this.exportPrice);
        System.out.println("Lợi nhuận khi bán sách này: " + this.interest);
        System.out.println("Trạng thái của sách: " + (this.bookStatus ? "Đang bán" : "Ngừng bán"));
    }
}