import java.lang.*;
import java.util.*;
import java.sql.*;

class YoutubeBackend
{
Scanner sc=new Scanner(System.in);
static Connection con;

  
//--------------------------------------

public static void main(String[] args)throws Exception
  {
  
Class.forName("org.sqlite.JDBC");
   con=DriverManager.getConnection("jdbc:sqlite:C:/SQLite/youtube.db");

YoutubeBackend backend = new YoutubeBackend();
        backend.youtube();
  }

//------------------------------

void youtube()throws Exception
     {

System.out.println("*************Youtube**************\n1,regster to youtube\n2,watch video\n3,exit");
    int option=sc.nextInt();
    sc.nextLine();
     switch(option){
                  case 1: regster();
                  break;
                  case 2:watchVideo();
                  break;
                  case 3: System.exit(0);
                  break;
                  default:
                System.out.println("Invalid option. Please try again.");
                youtube();
                  }
    
     }

//-------------------------

 void regster ()throws Exception
      {
        int noo=0;
         int userr_id=0;
        System.out.println("--Sign up---\nEnter your name ");
         String name=sc.nextLine();
         Statement stm2 = con.createStatement();
         ResultSet rs1=stm2.executeQuery("select * from members");
 while(rs1.next()){
         noo=rs1.getInt("no");
         userr_id=rs1.getInt("user_id");
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
void watchVideo()throws Exception{

     System.out.println("               ______________________");
     System.out.println("        search(______________________)");
      String frame=""" 
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

      int subscriberNo=0;
      int likeNo=0;
      int dislikeNo=0;
      int shareNo=0;
      Statement stm3=con.createStatement();
      String  counter="select * from video";
      ResultSet rs3=stm3.executeQuery(counter);
      while(rs3.next())
        {
          int j=rs3.getInt("statusid");
          if(j==1){ likeNo++;}
          else if(j==2){dislikeNo++;}

          int k=rs3.getInt("statusS_id");
          if(k==1){subscriberNo++;}
          
      
          int l=rs3.getInt("statusR_id");
          if(l==1){shareNo++;}
         }
      System.out.println("            THE~~AI Tube");
      System.out.println("              _____________                                      _______       ________");
      System.out.println("           "+subscriberNo+" |Subscribers  |"+"                                  "+likeNo+" | Likes |   "+ shareNo+" | shares |" );
      System.out.println("              -------------                                      -------       --------");
     

    System.out.println("You have to Register before you can like,share and subscribe press reg to register and ex to exit if u have already regstered input yes ");
      String lett=sc.nextLine();
      if(lett.equalsIgnoreCase("reg")){regster();}
       else if(lett.equalsIgnoreCase("ex")){System.exit(0);}
       else if(lett.equalsIgnoreCase("yes")){
 System.out.println("\noption\n1,Like\n2,Share\n3,Subscribe");
      int choise=sc.nextInt();
         sc.nextLine();
      switch(choise){
                    case 1: liker();
                     break;
                    case 2: sharer();
                     break;
                    case 3: subscriber();
                     break;
                    default : System.out.println("invalid input");
                              watchVideo();
                    break;
                    }

                                             }
       else {System.exit(1);}

      
     }

//--------------------------------------

    void liker()throws Exception{
 int n=0;
 int o=0;

  Statement stm4=con.createStatement();
  Statement stm5=con.createStatement();
 ResultSet rs4=stm4.executeQuery("select * from video");
  ResultSet rs5=stm5.executeQuery("select * from members");
while(rs4.next())
{
  n=rs4.getInt("no");
}
n++;
while(rs5.next())
{
o=rs5.getInt("user_id");

}
o++;

   String upd = "INSERT INTO video VALUES (" + n + ", " + o + ", 1, NULL, NULL)";
Statement stm6 = con.createStatement();
stm6.executeUpdate(upd);
 watchVideo();
   }

//-----------------------
 void sharer()throws Exception{
 
Statement stm8=con.createStatement();
ResultSet rs8=stm8.executeQuery("select * from  video");
while(rs8.next()){
if(rs8.getInt("statusR_id")==0){
      int l=rs8.getInt("no");
 PreparedStatement pstm=con.prepareStatement("Update video SET statusR_id=1 where no="+l+" ");
  pstm.executeUpdate();
                             }
//else {System.out.println("you have to register before u can share"); watchVideo();}

}

watchVideo();
}

//----------------------------

void subscriber()throws Exception{

Statement stm10=con.createStatement();
ResultSet rs10=stm10.executeQuery("select * from video");
while(rs10.next()){ 
                  
  if(rs10.getInt("statusS_id")==0){
              int q=rs10.getInt("no");
PreparedStatement pstm1=con.prepareStatement("Update video SET statusS_id=1 where no="+q+" ");
  pstm1.executeUpdate();

                                  }
//else {System.out.println("you have to register before u can subscribe"); watchVideo();}
                 }
watchVideo();

                                }
}






















