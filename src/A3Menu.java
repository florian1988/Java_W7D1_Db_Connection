import java.sql.*;
import java.util.Scanner;

public class A3Menu {

    Statement statement = null;

    public void menu(){

        Scanner scan = new Scanner(System.in);
        boolean mainLoop = true;
        int choice;

        do{
           System.out.println("Main Menu\n");
           System.out.println("1) Print Authors");
           System.out.println("2) Print Books");
           System.out.println("3) Print Publishers");
           System.out.println("4) Search book with Author Name");
           System.out.println("5) Exit");
           System.out.println("Enter your Menu Choice");

            choice = scan.nextInt();

        }while(choice>6);


        switch(choice){
            case 1:
                printAuthors();
                do{
                    menu();
                }
                while(choice < 5 );
                break;

            case 2:
                printBooks();
                do{
                    menu();
                }
                while(choice < 5 );
                break;

            case 3:
                printPublisher();
                do{
                    menu();
                }
                while(choice < 5 );
                break;

            case 4:
                Scanner authorScan = new Scanner(System.in);
                System.out.println("Write the Author'S name:");
                    String author = authorScan.nextLine();
                searchWithAuthor(author);
                do{
                    menu();
                }
                while(choice < 5 );
                break;

            case 5:
                System.out.println("Exit Program");
                System.exit(0);
                break;
        }

        if( choice > 5 || choice < 1){
            System.out.println("This is no Menu Option");
            do{
                choice = scan.nextInt();
            }while(choice < 6);
        }
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            e.getSQLState();
           e.printStackTrace();
        }

        return connection;
    }



    public void printAuthors(){
        Connection con = connect();
        try{
            try{
                statement = con.createStatement();
                ResultSet resultSet = null;
                try {
                    String sql = "Select * from Author";
                    resultSet = statement.executeQuery(sql);

                    String toppic = String.format("%s %15s %20s","Id","Name", "Country");
                    System.out.println(toppic);
                    System.out.println("______________________________________________________");

                    while(resultSet.next()){
                        Integer id_author = resultSet.getInt("id_author");
                        String authorName = resultSet.getString("author.name");
                        String authorCountry = resultSet.getString("author.country");

                        String output = String.format("%d %20s %20s", id_author, authorName, authorCountry);
                        System.out.println(output);

                    }

                }finally {
                    if(resultSet != null) resultSet.close();
                }

            } finally {
                    if(statement != null) statement.close();
                }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void printBooks(){
        Connection con = connect();

        try{
            try{
                statement = con.createStatement();
                ResultSet resultSet = null;
                try{
                    String sql = "Select * from book";
                    resultSet = statement.executeQuery(sql);

                    String toppic = String.format("%s %20s %20s","Id","Name", "Genre");
                    System.out.println(toppic);
                    System.out.println("______________________________________________________");

                    while (resultSet.next()){
                        Integer idBook = resultSet.getInt("Id_Book");
                        String bookName = resultSet.getString("Name");
                        String bookGenre = resultSet.getString("Genre");

                        String output = String.format("%d %25s %20s",idBook, bookName, bookGenre);
                        System.out.println(output);
                    }

                }finally {
                    if(resultSet != null) resultSet.close();
                }
            }finally {
                if(statement != null) statement.close();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void printPublisher(){
        Connection con = connect();

        try{
            try{
                statement = con.createStatement();
                ResultSet resultSet = null;
                try{
                    String sql = "Select * from Publisher";
                    resultSet = statement.executeQuery(sql);

                    String toppic = String.format("%s %15s %20s","Id","Name", "Address");
                    System.out.println(toppic);
                    System.out.println("______________________________________________________");

                    while(resultSet.next()){
                        Integer idPublisher = resultSet.getInt("Id_Publisher");
                        String publisherName = resultSet.getString("Publisher.Name");
                        String publisherAddress =  resultSet.getString("Publisher.Address");

                        String output = String.format("%d %20s %20s", idPublisher, publisherName, publisherAddress);
                        System.out.println(output);
                    }

                }finally {
                    if(resultSet != null) resultSet.close();
                }
            }finally {
                if(statement != null) statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void searchWithAuthor(String nameInput){

        Connection con = connect();

        try{

            try{
                statement = con.createStatement();
                ResultSet resultSet = null;
                try{
                    String sql = "Select book.*, author.*, publisher.* from book right join author on book.Id_Author = author.Id_Author right join publisher on book.Id_Publisher = publisher.Id_Publisher where author.name like '"+ nameInput + "' ";
                    resultSet =  statement.executeQuery(sql);

                    while(resultSet.next()){
                        String nameAuthor = resultSet.getString("author.name");
                        String nameBook = resultSet.getString("book.name");
                        String namePublisher = resultSet.getString("publisher.name");

                        System.out.println(nameAuthor+ "  " + nameBook + "  " + namePublisher);
                    }

                }finally {
                    if(resultSet != null) resultSet.close();
                }
            }finally {
                if(statement != null) statement.close();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }





}
