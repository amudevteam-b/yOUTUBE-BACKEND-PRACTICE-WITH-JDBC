import java.lang.*;
import java.util.*;
import java.sql.*;

public class YoutubeBackend
{
    private Connection con;

    public YoutubeBackend(Connection connection) {
        this.con = connection;
    }

    public static void main(String[] args) throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/youtube.db");

        YoutubeBackend backend = new YoutubeBackend(con);
        backend.youtube();
    }

    void youtube() throws Exception
    {
        System.out.println("*************Youtube**************\n1,regster to youtube\n2,watch video\n3,exit");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                regster();
                break;
            case 2:
                watchVideo();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                youtube();
        }
    }

    void regster() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int noo = 0;
        int userr_id = 0;
        System.out.println("--Sign up---\nEnter your name ");
        String name = sc.nextLine();
        Statement stm2 = con.createStatement();
        ResultSet rs1 = stm2.executeQuery("select * from members");
        while (rs1.next()) {
            noo = rs1.getInt("no");
            userr_id = rs1.getInt("user_id");
        }
        noo++;
        userr_id++;
        
        String insertQuery = "INSERT INTO members (name, user_id, no) VALUES ('" + name + "', " + userr_id + ", " + noo + ")";
        stm2.executeUpdate(insertQuery);

        System.out.print("Adding to database.");
        Thread.sleep(2000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(3000);
        System.out.print(".");
        Thread.sleep(3000);
        System.out.println("...");
        Thread.sleep(500);

        System.out.println("You have successfully regstered");
        youtube();
    }

    void watchVideo() throws Exception
    {
        System.out.println("               ______________________");
        System.out.println("        search(______________________)");
        String frame = """ 
                __________________________________________________________________________
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                             THE VIDEO                                    |
               |                          Directed by yobi!!                              |
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                                                                          |
               |                                                               THE~~AI    |
               |__________________________________________________________________________|
   """;
        System.out.println(frame);

        int[] videoStats = getVideoStats();
        int subscriberNo = videoStats[0];
        int likeNo = videoStats[1];
        int shareNo = videoStats[2];

        System.out.println("            THE~~AI Tube");
        System.out.println("              _____________                                      _______       ________");
        System.out.println("           " + subscriberNo + " |Subscribers  |" + "                                  " + likeNo + " | Likes |   " + shareNo + " | shares |" );
        System.out.println("              -------------                                      -------       --------");
       

        System.out.println("You have to Register before you can like,share and subscribe press reg to register and ex to exit if u have already regstered input yes ");
        Scanner sc = new Scanner(System.in);
        String lett = sc.nextLine();
        if (lett.equalsIgnoreCase("reg")) {
            regster();
        } else if (lett.equalsIgnoreCase("ex")) {
            System.exit(0);
        } else if (lett.equalsIgnoreCase("yes")) {
            System.out.println("\noption\n1,Like\n2,Share\n3,Subscribe");
            int choise = sc.nextInt();
            sc.nextLine();
            switch (choise) {
                case 1:
                    like();
                    break;
                case 2:
                    share();
                    break;
                case 3:
                    subscribe();
                    break;
                default:
                    System.out.println("invalid input");
                    watchVideo();
                    break;
            }
        } else {
            System.exit(1);
        }
    }

    public int[] getVideoStats() throws Exception {
        int subscriberNo = 0;
        int likeNo = 0;
        int shareNo = 0;
        
        Statement stm3 = con.createStatement();
        String counter = "select * from video";
        ResultSet rs3 = stm3.executeQuery(counter);
        while(rs3.next()) {
            int j = rs3.getInt("statusid");
            if(j == 1) { likeNo++; }
            
            int k = rs3.getInt("statusS_id");
            if(k == 1) { subscriberNo++; }
            
            int l = rs3.getInt("statusR_id");
            if(l == 1) { shareNo++; }
        }
        
        return new int[]{subscriberNo, likeNo, shareNo};
    }

    void like() throws Exception {
        int n = 0;
        int o = 0;

        Statement stm4 = con.createStatement();
        Statement stm5 = con.createStatement();
        ResultSet rs4 = stm4.executeQuery("select * from video");
        ResultSet rs5 = stm5.executeQuery("select * from members");
        
        while(rs4.next()) {
            n = rs4.getInt("no");
        }
        n++;
        
        while(rs5.next()) {
            o = rs5.getInt("user_id");
        }
        o++;

        String upd = "INSERT INTO video VALUES (" + n + ", " + o + ", 1, NULL, NULL)";
        Statement stm6 = con.createStatement();
        stm6.executeUpdate(upd);
        watchVideo();
    }

    void share() throws Exception {
        Statement stm8 = con.createStatement();
        ResultSet rs8 = stm8.executeQuery("select * from video");
        while(rs8.next()) {
            if(rs8.getInt("statusR_id") == 0) {
                int l = rs8.getInt("no");
                PreparedStatement pstm = con.prepareStatement("Update video SET statusR_id=1 where no=" + l + " ");
                pstm.executeUpdate();
            }
        }
        watchVideo();
    }

    void subscribe() throws Exception {
        Statement stm10 = con.createStatement();
        ResultSet rs10 = stm10.executeQuery("select * from video");
        while(rs10.next()) { 
            if(rs10.getInt("statusS_id") == 0) {
                int q = rs10.getInt("no");
                PreparedStatement pstm1 = con.prepareStatement("Update video SET statusS_id=1 where no=" + q + " ");
                pstm1.executeUpdate();
            }
        }
        watchVideo();
    }
}






















