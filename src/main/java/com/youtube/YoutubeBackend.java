package com.youtube;

import java.sql.*;

public class YoutubeBackend {
    private Connection con;

    public YoutubeBackend(Connection connection) {
        this.con = connection;
    }

    public void register(String name) throws Exception {
        int noo = 0;
        int userr_id = 0;
        
        Statement stm2 = con.createStatement();
        ResultSet rs1 = stm2.executeQuery("select * from members");
        while(rs1.next()) {
            noo = rs1.getInt("no");
            userr_id = rs1.getInt("user_id");
        }
        noo++;
        userr_id++;
        
        String insertQuery = "INSERT INTO members (name, user_id, no) VALUES ('" + name + "', " + userr_id + ", " + noo + ")";
        stm2.executeUpdate(insertQuery);
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

    public void like() throws Exception {
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
    }

    public void share() throws Exception {
        Statement stm8 = con.createStatement();
        ResultSet rs8 = stm8.executeQuery("select * from video");
        while(rs8.next()) {
            if(rs8.getInt("statusR_id") == 0) {
                int l = rs8.getInt("no");
                PreparedStatement pstm = con.prepareStatement("Update video SET statusR_id=1 where no=" + l + " ");
                pstm.executeUpdate();
            }
        }
    }

    public void subscribe() throws Exception {
        Statement stm10 = con.createStatement();
        ResultSet rs10 = stm10.executeQuery("select * from video");
        while(rs10.next()) { 
            if(rs10.getInt("statusS_id") == 0) {
                int q = rs10.getInt("no");
                PreparedStatement pstm1 = con.prepareStatement("Update video SET statusS_id=1 where no=" + q + " ");
                pstm1.executeUpdate();
            }
        }
    }
} 