import java.sql.*;

public class A2Main {
    public static void main(String[] args) throws SQLException {

    String url ="jdbc:mysql://localhost:3306/university";
    String user ="root";
    String password="";
    Statement statement = null;


    Connection connection = DriverManager.getConnection(url, user, password);

    connection.setAutoCommit(true);

    try{
        statement = connection.createStatement();
        ResultSet resultSet =null;
        try{
            String sql ="Select * from students";
            resultSet = statement.executeQuery(sql);

            String toppic = String.format("%s %15s %20s","Id", "Name", "Address");
            System.out.println(toppic);
            System.out.println("______________________________________________________");

                while(resultSet.next()){

                    Integer id_student = resultSet.getInt("ID_Students");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");

                    String output = String.format("%d %20s %20s",id_student, name, address);
                    System.out.println(output);

                }

        }finally{
            if(resultSet != null) resultSet.close();
        }

    }finally{
        if(statement != null) statement.close();
    }

    System.out.println("\n");


    try{
        statement = connection.createStatement();
        ResultSet resultSet = null;
        try{
            String sql = "Select * from courses";
            resultSet = statement.executeQuery(sql);

            String toppic = String.format("%s %15s","Id", "Name");
            System.out.println(toppic);
            System.out.println("______________________________________________________");

            while(resultSet.next()){

                Integer id_courses = resultSet.getInt("id_courses");
                String name = resultSet.getString("name");

                String output = String.format("%d %20s",id_courses, name);
                System.out.println(output);
            }

        }finally {
            if(resultSet != null) resultSet.close();
        }

    }finally {
        if(statement != null) statement.close();
    }

        System.out.println("\n");

    try{
        statement = connection.createStatement();
        ResultSet resultSet = null;
        try{
            String sql = "Select students.name, courses.name from enrollments right join students on enrollments.fk_students = students.id_students right join courses on enrollments.fk_courses = courses.id_courses where students.name like 'Ivan Katschuk'";


            resultSet = statement.executeQuery(sql);

            String toppic = String.format("%20s %30s","Course", "Student");
            System.out.println(toppic);
            System.out.println("______________________________________________________");

            while(resultSet.next()){

                String nameCourse = resultSet.getString("courses.name");
                String nameStudent = resultSet.getString("students.name");

                String output = String.format("%25s %30s",nameCourse, nameStudent);
                System.out.println(output);
            }

        }finally {
            if(resultSet != null) resultSet.close();
        }

    }finally {
        if(statement != null) statement.close();
    }




    }
}
