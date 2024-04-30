import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Number {
    private static final String url = "jdbc:mysql://localhost:3306/project1";
    private static final String username = "root";
    private static final String password = "YOUR_PASSWORD";
    private Connection connection;
    private Scanner scanner;
    private Statement statement;
    String cal, rev;

    public Number(Connection connection, Scanner scanner, Statement statement) {
        this.connection = connection;
        this.scanner = scanner;
        this.statement = statement;
    }

    public boolean name_exist(String name) {
        String query = "SELECT * FROM contact WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean number_exist(int number) {
        String query = "SELECT * FROM contact WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int input_user() {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Number: ");
        int num = scanner.nextInt();
        if (name_exist(name) || number_exist(num)) {
            System.out.println("Name or Number is Entered Duplicate ");
            System.out.println("Please Try again");
            return 0;
        }
        String input_query = "INSERT INTO contact(name,number) VALUES( ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(input_query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, num);
            int rows_affected = preparedStatement.executeUpdate();
            if (rows_affected > 0) {
                System.out.println("Insertion Successfull!");
                return  1;
            } else {
                System.out.println("Intersion Failed!");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public void display_user() {
        System.out.println("\n    Total Contacts");
        try {
            String display_query = "select * from contact";
            ResultSet resultSet = statement.executeQuery(display_query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int number = resultSet.getInt("number");
                System.out.println("id:" + id + " name:" + name + " number:" + number);
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete_user() {
        try {
            String delete_query = "TRUNCATE TABLE contact;";
            int row_ffected = statement.executeUpdate(delete_query);
            if (row_ffected == 0) {
                System.out.println("data deleted successfully");
            } else {
                System.out.println("data not deleted");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update_name() {
        System.out.print("Enter Old Name: ");
        String old_name = scanner.next();
        try {
            String nameUpdate_query = ("SELECT * FROM contact WHERE name = ?");
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(nameUpdate_query);
                preparedStatement.setString(1, old_name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("OLd Name Found");
                    System.out.print("Enter New Name: ");
                    String new_name = scanner.next();
                    if (name_exist(new_name)) {
                        System.out.println("Name is Already Present in Contact List");
                    } else {
                        String name_update_query = "UPDATE contact set name = ? where name=?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(name_update_query);
                        preparedStatement1.setString(1, new_name);
                        preparedStatement1.setString(2, old_name);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Name Update Successfully");
                            return;
                        } else {
                            System.out.println("Name Update Failed!");
                        }
                    }
                } else {
                    System.out.println("Old Name Not Found");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } finally {
        }
    }

    public void update_num() {
        System.out.print("Enter Old Number: ");
        int old_num = scanner.nextInt();
        try {
            String nameUpdate_query = ("SELECT * FROM contact WHERE number = ?");
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(nameUpdate_query);
                preparedStatement.setInt(1, old_num);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("OLd Number Found");
                    System.out.print("Enter New Number: ");
                    int new_num = scanner.nextInt();
                    if (number_exist(new_num)) {
                        System.out.println("Number is Already Present in Contact List");
                    } else {
                        String number_update_query = "UPDATE contact set number = ? where number=?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(number_update_query);
                        preparedStatement1.setInt(1, new_num);
                        preparedStatement1.setInt(2, old_num);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Number Update Successfully");
                            return;
                        } else {
                            System.out.println("Number Update Failed!");
                        }
                    }
                } else {
                    System.out.println("Old Number Not Found");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } finally {
        }
    }

    int rndm(int mem) {
        int min = 1;
        int max = 1 + mem + 2;
        int randam=(int) (Math.random() * (max - min + 1) + min);
        System.out.println(randam);
        return randam;
    }

    void calling(int mem) {
        System.out.println("Enter the caller Name");
        cal = scanner.next();
        System.out.println("Enter the Receiver Name");
        rev = scanner.next();
        int rm = rndm(mem);
        if (name_exist(cal) && name_exist(rev)) {
            System.out.println("\nCaller Name and Receiver Name Present : Valid Input\n");
            String query1 = "SELECT name from contact WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setInt(1, rm);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name_rm = resultSet.getString("name");
                    System.out.println();
                    if (name_rm.equalsIgnoreCase(cal) || name_rm.equalsIgnoreCase(rev))// rm == caller or receiver
                    {
                        System.out.println(rev + " is free to call...." + cal + " can call now");
                        System.out.println("Available");
                    }
                    if (!name_rm.equalsIgnoreCase(cal) && !name_rm.equalsIgnoreCase(rev))// rm is someone other than
                                                                                         // caller or reveivrr
                    {
                        System.out.println(rev + " is talking to ...." + name_rm);
                        System.out.println("Busy");

                    }
                } else {
                    System.out.println(rev + " is free to call...." + cal + " can call now");
                    System.out.println("Available");
                }
            } catch (SQLException e) {
                System.out.println("\n" + e);
            }

        } else {
            System.out.println("Error in Caller Name or Receiver Name ");
        }
    }

    int choice() {
        System.out.println("\n");
        System.out.println("1 :New Game");
        System.out.println("2 :Display");
        System.out.println("3 :Update");
        System.out.println("4 :Delete All");
        System.out.println("5 :Check by Calling"); 
        System.out.println("Enter Your Choice........");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        int i, mem = 3;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            Number obj = new Number(connection, scanner, statement);
            System.out.println("WELCOME TO CONTACT GAME");
            for (i = 1; i > 0; i++) {
                System.out.println("Press Any Number to Play But 0 To Exit Game......");
                int choi = scanner.nextInt();
                if (choi == 0) {
                    break;
                } else {
                    int ch = obj.choice();
                    switch (ch) {
                        case 0:
                            break;
                        case 1:
                            System.out.println("Enetr the number of Players(3 to 6 ) : ");
                            mem = scanner.nextInt();
                            if (mem >= 3 && mem < 7) {
                                    obj.delete_user();
                                    System.out.println("Enter New Players Detail");
                                    for (i = 1; i <= mem; i++) {
                                          int n_in= obj.input_user();
                                          if(n_in==1)
                                          {
                                            obj.display_user();
                                          }
                                          else
                                          {
                                            i--;
                                          }
                                      }
                            } else {
                                System.out.println("Invalid Input ");
                            }
                            break;
                        case 2:
                            obj.display_user();

                            break;
                        case 3:
                            for (int j = 1; j > 0; j++) {
                                System.out.println("\nPress 1 to Update Namer");
                                System.out.println("\nPress 2 to Update Number");
                                System.out.println("\nPress random number to exit option");
                                int chk = scanner.nextInt();
                                if (chk == 1) {
                                    obj.update_name();
                                    obj.display_user();
                                } else if (chk == 2) {
                                    obj.update_num();
                                    obj.display_user();
                                } else
                                    break;
                            }
                            break;
                        case 4:
                            obj.delete_user();
                            obj.display_user();
                            break;
                        case 5:
                            for (int j = 1; j > 0; j++) {
                                System.out.println("\nPress 1 to Check Calling");
                                int chk = scanner.nextInt();
                                if (chk == 1) {
                                    obj.calling(mem);
                                    obj.display_user();
                                } else {
                                    break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}