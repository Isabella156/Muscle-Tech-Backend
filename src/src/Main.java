import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
  public static void main(String[] args) throws Exception{
    createTable();
  }

  public static void createTable() throws Exception{
    try{
      Connection con = getConnection();
      String UserSql, VenueSql, OrderVenueSql, CourseSql, OrderCourseSql, Sql;
      UserSql = "CREATE TABLE IF NOT EXISTS User(userId BIGINT NOT NULL AUTO_INCREMENT, " +
              "userName VARCHAR(30) DEFAULT 'user', " +
              "userPhoneNumber CHAR(11) NOT NULL, " +
              "userPassword VARCHAR(20) NOT NULL, " +
              "userType TINYINT DEFAULT 0, " +
              "PRIMARY KEY(userId));\n";
      VenueSql = "CREATE TABLE IF NOT EXISTS Venue(venueId BIGINT NOT NULL AUTO_INCREMENT, " +
              "venueName VARCHAR(30) NOT NULL, " +
              "venueInformation TEXT NOT NULL, " +
              "venueType TINYINT DEFAULT 0, " +
              "venueCapacity MEDIUMINT NOT NULL, " +
              "venuePresentNumber MEDIUMINT DEFAULT 0, " +
              "venuePrice SMALLINT NOT NULL, " +
              "PRIMARY KEY(venueId));\n";
      OrderVenueSql = "CREATE TABLE IF NOT EXISTS OrderVenue(orderVenueId BIGINT NOT NULL AUTO_INCREMENT, " +
              "userId BIGINT NOT NULL, " +
              "venueId BIGINT NOT NULL, " +
              "orderVenueDate DATE NOT NULL, " + 
              "orderVenueSession TINYINT NOT NULL, " + 
              "orderVenueLocation SMALLINT NOT NULL, " + 
              "orderVenueAttendance TINYINT DEFAULT 0, " +
              "orderVenueFeedback TEXT, " +
              "FOREIGN KEY (userId) REFERENCES User(userId), " +
              "FOREIGN KEY (venueId) REFERENCES Venue(venueId), " + 
              "PRIMARY KEY(orderVenueId));\n";
      CourseSql = "CREATE TABLE IF NOT EXISTS Course(courseId BIGINT NOT NULL AUTO_INCREMENT, " +
              "venueId BIGINT NOT NULL REFERENCES Venue(venueId), " +
              "courseName VARCHAR(30) NOT NULL, " + 
              "courseCoach VARCHAR(10) NOT NULL, " +
              "courseInformation TEXT NOT NULL, " + 
              "courseCapacity MEDIUMINT NOT NULL, " +
              "coursePrice SMALLINT NOT NULL, " + 
              "PRIMARY KEY(courseId));\n";
      OrderCourseSql = "CREATE TABLE IF NOT EXISTS OrderCourse(orderCourseId BIGINT NOT NULL AUTO_INCREMENT, " +
              "userId BIGINT NOT NULL, " +
              "courseId BIGINT NOT NULL, " +
              "orderCourseDate DATE NOT NULL, " +
              "orderCourseSession TINYINT NOT NULL, " +
              "orderCourse TINYINT NOT NULL, " +
              "orderCourseAttendance TINYINT DEFAULT 0, " +
              "orderCourseFeedback TEXT, " +
              "FOREIGN KEY (userId) REFERENCES User(userId), " + 
              "FOREIGN KEY (courseId) REFERENCES Course(courseId), " + 
              "PRIMARY KEY(orderCourseId));\n";
      PreparedStatement create = con.prepareStatement(UserSql);
      create.executeUpdate();
      create = con.prepareStatement(VenueSql);
      create.executeUpdate();
      create = con.prepareStatement(OrderVenueSql);
      create.executeUpdate();
      create = con.prepareStatement(CourseSql);
      create.executeUpdate();
      create = con.prepareStatement(OrderCourseSql);
      create.executeUpdate();
    }catch(Exception e){
      System.out.println(e);
    }
    finally{
      System.out.println("Function Complete");
    };
  }
  public static Connection getConnection() throws Exception{
    try{
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/test";
      String username = "root";
      String password = "tz20081103";
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(url, username, password);
      System.out.println("Connected");
      return conn;
    } catch(Exception e){
      System.out.println(e);
    }
    return null;
  }
}