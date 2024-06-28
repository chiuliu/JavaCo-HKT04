package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement
{
    public static void main(String[] args)
    {
        Book[] bookList = new Book[100];//Mảng đại diện cho thư viện có sức chứa 100 cuốn sách.
        Scanner scanner = new Scanner(System.in);
        int bookCount = 0; // đếm số lượng sách có trong thư viện.
        int maxId = 1;

        while (true)
        {
            System.out.println("**********____JAVA-HACKATHON-05-BASIC-MENU____**********");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả sách");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.println("=======================================================");
            System.out.print("Nhập hành động muốn thực hiện theo các số từ 1-7 :");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice)
            {
                case 1:
                    // addListBook
                    System.out.println("Nhập số lượng sách mà bạn muốn thêm vào danh sách trong thư viện");
                    int n = Integer.parseInt(scanner.nextLine());
                    if (n + bookCount > bookList.length - bookCount)
                    {
                        System.out.printf("Không có đủ chỗ trống trong thư viện. \n" +
                                "Thư viện chỉ có thể lưu trữ tối đa 100 đầu sách. \n" +
                                "Hiện đã có %d đầu sách. \n", bookCount);
                        break;
                    }
                    for (int i = 0; i < n; i++)
                    {
                        System.out.println("Mời nhập thông tin về cuốn sách thứ " + (i + 1));
                        Book newBook = new Book();
                        newBook.inputData(scanner);
                        newBook.setBookId(maxId);//Đặt mã sách
                        bookList[bookCount] = newBook;
                        bookCount++;
                        maxId++;
                    }
                    break;
                case 2:
                    //displayListBook
                    if (bookCount <= 0)
                    {
                        System.out.println("Hiện chưa có cuốn sách nào trong thư viện để hiển thị");
                        break;
                    }
                    for (int i = 0; i < bookCount; i++)
                    {
                        System.out.println("Thông tin của sách ở vị trí thứ " + (i + 1));
                        bookList[i].displayData();
                    }
                    break;
                case 3:
                    // SortListBook
                    if (bookCount <= 0)
                    {
                        System.out.println("Hiện chưa có cuốn sách nào trong thư viện để sắp xếp");
                        break;
                    }
                    for (int i = 1; i < bookCount; i++)
                    {
                        int j = i;
                        Book tempBook = bookList[j];
                        while (j > 0 && bookList[j - 1].getInterest() > tempBook.getInterest())
                        {
                            bookList[j] = bookList[j - 1];
                            j--;
                        }
                        bookList[j] = tempBook;
                    }
                    System.out.println("Danh sách đã sắp xếp dựa theo lợi nhuận: ");
                    for (int i = 0; i < bookCount; i++)
                    {
                        System.out.println(bookList[i].getBookName());
                        System.out.println("Lợi nhuận: " + bookList[i].getInterest());
                    }
                    break;
                case 4:
                    // DeteleListBook
                    if (bookCount <= 0)
                    {
                        System.out.println("Hiện chưa có cuốn sách nào trong thư viện để xóa");
                        break;
                    }
                    System.out.println("Danh sách các đầu sách hiện có dựa trên mã sách");
                    for (int i = 0; i < bookCount; i++)
                    {
                        System.out.print(bookList[i].getBookId() + "\t");
                    }
                    System.out.println("Mời nhập mã sách muốn xóa");
                    int deleteChoice = Integer.parseInt(scanner.nextLine());
                    boolean bookExist = false;
                    for (int i = 0; i < bookCount; i++)
                    {
                        if (deleteChoice == bookList[i].getBookId())
                        {
                            bookList[i] = null;
                            bookExist = true;
                            for (int j = i; j < bookCount - 1; j++)
                            {
                                bookList[j] = bookList[j + 1];
                            }
                            bookList[bookCount - 1] = null;//Xóa sách ở vị trí cuối để không bị trùng
                            bookCount--;
                            break;
                        }
                    }
                    if (!bookExist)
                    {
                        System.out.println("Mã số mà bạn đã chọn không tồn tại");
                        break;
                    }
                    System.out.println("Danh sách mới sau khi xóa");
                    for (int i = 0; i < bookCount; i++)
                    {
                        System.out.print(bookList[i].getBookId() + "\t");
                    }
                    break;
                case 5:
                    // SearchListBook
                    if (bookCount <= 0)
                    {
                        System.out.println("Hiện chưa có cuốn sách nào trong thư viện để tìm kiếm");
                        break;
                    }
                    System.out.println("Nhập vào thông tin muốn tìm kiếm");
                    String searchInput = scanner.nextLine();
                    boolean isExist = false;
                    for (int i = 0; i < bookCount; i++)
                    {
                        if (bookList[i].getBookName().contains(searchInput))
                        {
                            System.out.println("Đã tìm thấy sách có tên giống với yêu cầu");
                            bookList[i].displayData();
                            isExist = true;
                        } else if (bookList[i].getDescription().contains(searchInput))
                        {
                            System.out.println("Đã tìm thấy sách có mô tả giống với yêu cầu");
                            bookList[i].displayData();
                            isExist = true;
                        }
                    }
                    if (!isExist)
                        System.out.println("Không tìm thấy sách giống yêu cầu");
                    break;
                case 6:
                    // EditListBook
                    if (bookCount <= 0)
                    {
                        System.out.println("Hiện chưa có cuốn sách nào trong thư viện để chỉnh sửa");
                        break;
                    }
                    System.out.println("Danh sách các đầu sách hiện có dựa trên mã sách");
                    for (int i = 0; i < bookCount; i++)
                    {
                        System.out.print(bookList[i].getBookId() + "\t");
                    }
                    System.out.println("Mời nhập mã sách muốn chỉnh sửa :");
                    int updateChoice = Integer.parseInt(scanner.nextLine());
                    boolean bookFound = false;
                    for (int i = 0; i < bookCount; i++)
                    {
                        if (updateChoice == bookList[i].getBookId())
                        {
                            System.out.println("Mời nhập thông tin mới cho sách");
                            bookList[i].inputData(scanner);
                            bookFound = true;
                            System.out.println("Thông tin cập nhật của cuốn sách: ");
                            bookList[i].displayData();
                            break;
                        }
                    }
                    if (!bookFound)
                        System.out.println("Không tìm thấy mã sách theo yêu cầu");
                    break;
                case 7:
                    // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số từ 1-7.");
                    break;
            }
        }

    }

}

