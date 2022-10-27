/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;
import sun.nio.cs.MS1250;

/**
 *
 * @author Account
 */
public class DAO extends DBContext {

    private ArrayList<User> userList;
    private Connection con;
    private String status;

    public DAO(ArrayList<User> userList, String status) {
        this.userList = userList;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public DAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Error connection " + e.getMessage();
        }
    }

    public void updateStatus(int reqId) {
        String sql = "UPDATE [dbo].[Request] SET status = 'processing' WHERE requestID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reqId);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }
    }

    //get mentor with userid
    public Mentor getMentorByUserId(User user) {
        String sql = "select * from Mentor where userId = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int mentorId = rs.getInt(1);
                User u = new User();
                u.setUserId(rs.getInt(2));
                String status = rs.getString(3);
                Mentor m = new Mentor(mentorId, u, status);
                return m;
            }
        } catch (Exception e) {
            status = "Error get mentor by userId: " + e.getMessage();
        }
        return null;
    }

    //get all request
    public ArrayList<Request> getRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        String sql = "select * from Request";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setRequestID(rs.getInt(1));
                Mentee mentee = new Mentee();
                mentee.setMenteeID(rs.getInt(2));
                mentee = getMentee(rs.getInt(2));
                Mentor mentor = new Mentor();
                mentor.setMentorID(rs.getInt(3));
                mentor = getMentor(rs.getInt(3));
                r.setMentee(mentee);
                r.setMentor(mentor);
                r.setTitle(rs.getString(4));
                r.setReqContent(rs.getString(5));
                r.setStatus(rs.getString(6));
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt(7));
                skill = getSKill(rs.getInt(7));
                r.setTime(rs.getDate(8));
                requests.add(r);
            }
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }

        return requests;
    }

    //load account from database
    public ArrayList<User> loadListUser() {
        userList = new ArrayList<>();
        String sql = "select * from [User]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = Integer.parseInt(rs.getString(1));
                String username = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                Date dob = rs.getDate(5);
                String fullname = rs.getString(6);
                String address = rs.getString(7);
                boolean gender = rs.getBoolean(8);
                int role = Integer.parseInt(rs.getString(9));
                String status = rs.getString(10);
                String avatar = rs.getString(11);
                String phonenum = rs.getString(12);

                userList.add(new User(userId, username, password, email, dob, fullname, address, gender, status, avatar, phonenum, role));
            }
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }
        return userList;
    }

    //load request from database
    public ArrayList<Request> loadRequest(int id, String dbid) {
        ArrayList<Request> reqList = new ArrayList<>();
        String sql = "select * from Request where " + dbid + " = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int requestID = rs.getInt(1);
                Mentor mentor = getMentor(rs.getInt(2));
                Mentee mentee = getMentee(rs.getInt(3));
                Date time = null;
                String title = rs.getString(4);
                String reqContent = rs.getString(5);
                String status = rs.getString(6);
                reqList.add(new Request(requestID, mentor, mentee, time, title, reqContent, status));
            }
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }
        return reqList;
    }

    //load response from database
    public ArrayList<Response> loadResponse(int id, String dbid) {
        ArrayList<Response> resList = new ArrayList<>();
        String sql = "select * from Response where " + dbid + " = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int responseID = rs.getInt(1);
                Mentor mentor = getMentor(rs.getInt(3));
                Mentee mentee = getMentee(rs.getInt(2));
                String resContent = rs.getString(4);
                String status = rs.getString(5);
                Date time = null;
                Request request = getRequest(rs.getInt(6));
                resList.add(new Response(responseID, mentor, mentee, resContent, status, time, request));
            }
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }
        return resList;
    }

    //insert comment to DB
    public void insertComment(Comment c) {
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([mentorID]\n"
                + "           ,[menteeID]\n"
                + "           ,[cmtContent]\n"
                + "           ,[time])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, c.getMentor().getMentorID());
            ps.setInt(2, c.getMentee().getMenteeID());
            ps.setString(3, c.getCmtContent());
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user profile" + e.getMessage();
        }
    }

    //insert request to DB
    public void insertRequest(Request r) {
        String sql = "INSERT INTO [dbo].[Request]\n"
                + "           ([menteeID]\n"
                + "           ,[mentorID]\n"
                + "           ,[title]\n"
                + "           ,[reqcontent]\n"
                + "           ,[status]\n"
                + "           ,[time])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getMentee().getMenteeID());
            ps.setInt(2, r.getMentor().getMentorID());
            ps.setString(3, r.getTitle());
            ps.setString(4, r.getReqContent());
            ps.setString(5, r.getStatus());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //insert rate to DB
    public void insertRate(Rating r) {
        String sql = "INSERT INTO [dbo].[Rating]\n"
                + "           ([mentorID]\n"
                + "           ,[menteeID]\n"
                + "           ,[rateStar])\n"
                + "     VALUES\n"
                + "           (?,\n"
                + "           ?,\n"
                + "           ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, r.getMentor().getMentorID());
            ps.setInt(2, r.getMentee().getMenteeID());
            ps.setInt(3, r.getRateStar());
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user profile" + e.getMessage();
        }
    }

    //insert response to DB
    public void insertResponse(int reqId, String resContent) {
        String sql = "INSERT INTO [dbo].[Response]([menteeID],[mentorID],[resContent],[status],[requestID],[time])\n"
                + "VALUES(?,?,?,?,?,GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            Request req = getRequest(reqId);
            ps.setInt(1, req.getMentee().getMenteeID());
            ps.setInt(2, req.getMentor().getMentorID());
            ps.setString(3, resContent);
            ps.setString(4, "open");
            ps.setInt(5, reqId);
            updateStatus(reqId);
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user profile" + e.getMessage();
        }
    }

    //insert request to DB
    public void insertRequest(int reqId, String resContent) {
        String sql = "INSERT INTO [dbo].[Request]([menteeID],[mentorID],[title],[reqcontent],[status],[time])\n"
                + "VALUES(?,?,?,?,?,GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            Request req = getRequest(reqId);
            ps.setInt(1, req.getMentee().getMenteeID());
            ps.setInt(2, req.getMentor().getMentorID());
            ps.setString(3, "request title");
            ps.setString(4, resContent);
            ps.setString(5, "processing");
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user profile" + e.getMessage();
        }
    }

    // update User Profile
    public void updateUserProfile(String avatar, String fullname, Date dob, String address, String email, String phonenum, boolean gender, int userid) {
        String sql = "update [User] set avatar = ?, fullname = ?, dob = ?, address = ?, email = ?, phonenumber = ?, gender = ? where userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, avatar);
            ps.setString(2, fullname);
            ps.setDate(3, dob);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, phonenum);
            ps.setBoolean(7, gender);
            ps.setInt(8, userid);
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user profile" + e.getMessage();
        }
    }

    // Change password
    public void changePassword(int userId, String newPass) {
        String sql = "update [User] set password = ? where userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPass);
            ps.setInt(2, userId);
            ps.execute();

        } catch (Exception e) {
            status = "Error at change password: " + e.getMessage();
        }
    }

    //register
    public void register(String username, String password, String email, Date dob, String fullname, String address, boolean gender, String avata, String phonenmuner) {
        String sql = "INSERT INTO [dbo].[User]([username],[password],[email],[dob],[fullname],[address],[gender],[role],[status],[avatar],[phonenumber])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setDate(4, dob);
            ps.setString(5, fullname);
            ps.setString(6, address);
            ps.setBoolean(7, gender);
            ps.setInt(8, 1);
            ps.setString(9, "inactive");
            ps.setString(10, avata);
            ps.setString(11, phonenmuner);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at register: " + e.getMessage());
        }
    }

    //active account
    public void activeUser(String username) {
        String sql = "update [User] set status='active' where username like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at update user active" + e.getMessage();
        }
    }

    public ArrayList<Mentor> getMentorWithTech(int skillId) {
        ArrayList<Mentor> listMentor = new ArrayList<Mentor>();

        String sql = "select m.mentorID, m.userID, m.status from Mentor m,\n"
                + "(select s.skillID,s.skillName,es.mentorID from Skill s, EnrollSkill es\n"
                + "where s.skillID = es.skillID and s.skillId = ? ) a\n"
                + "where m.mentorID = a.mentorID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, skillId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();

                int mentorID = rs.getInt(1);
                user.setUserId(rs.getInt(2));
                String status = rs.getString(3);

                Mentor m = new Mentor(mentorID, user, status);
                listMentor.add(m);
            }
        } catch (Exception e) {
            status = "Error load mentor with technology: " + e.getMessage();
        }

        return listMentor;
    }

    public ArrayList<String> getTechOfMentor(int userId) {
        ArrayList<String> listTech = new ArrayList<>();

        String sql = "select s.skillName from [Skill] s,\n"
                + "(select es.skillID, es.mentorID from EnrollSkill es, Mentor m where es.mentorID = m.mentorID and m.userID = ?) a\n"
                + "where s.skillID = a.skillID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tech = rs.getString(1);

                listTech.add(tech);
            }
        } catch (Exception e) {
            status = "Error load technology of mentor: " + e.getMessage();
        }

        return listTech;
    }

    //load skill
    public ArrayList<Skill> getSkill() {
        ArrayList<Skill> listSkill = new ArrayList<>();
        String sql = "select * from [Skill]";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int skillId = rs.getInt(1);
                String skillName = rs.getString(2);
                String description = rs.getString(3);

                listSkill.add(new Skill(skillId, skillName, description));
            }
        } catch (Exception e) {
            status = "Error load all skill: " + e.getMessage();
        }

        return listSkill;
    }

    //load enrollskill
    public ArrayList<EnrollSkill> getEnrollSkills() {
        ArrayList<EnrollSkill> listEnrollSkill = new ArrayList<>();
        String sql = "select * from [EnrollSkill]";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                Skill s = new Skill();
                int enrollId = rs.getInt(1);
                m.setMentorID(rs.getInt(2));
                s.setSkillId(rs.getInt(3));

                listEnrollSkill.add(new EnrollSkill(enrollId, m, s));
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return listEnrollSkill;
    }

    //get rating by mentorId
    public HashMap<Integer, Float> getRateByMentorID() {
        HashMap<Integer, Float> ratesHashMap = new HashMap<>();
        String sql = "select r.mentorID,cast(cast((sum(rateStar)) as float) / cast((count(rateStar)) as float) as decimal(10,1)) as 'averageStar' \n"
                + "from Rating r\n"
                + "group by r.mentorID";
//        String sql = "select r.mentorID,cast((sum(rateStar)) as float) / cast((count(rateStar)) as float) as 'averageStar' \n"
//                + "from Rating r\n"
//                + "group by r.mentorID";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer mentorID = rs.getInt(1);
                Float averageStar = rs.getFloat(2);
                ratesHashMap.put(mentorID, averageStar);

            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return ratesHashMap;
    }

    //Get list of skills belong to a mentor 
    public ArrayList<Skill> getSkills(Mentor m) {
        ArrayList<Skill> skills = new ArrayList<>();
        int mentorID = m.getMentorID();
        String sql = "select a.skillID,a.skillName from Mentor m,\n"
                + "(select s.skillID,es.mentorID,s.skillName from Skill s inner join EnrollSkill es \n"
                + "on s.skillID = es.skillID) a\n"
                + "where m.mentorID = a.mentorID and m.mentorID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setSkillId(Integer.parseInt(rs.getString(1)));
                s.setSkillName(rs.getString(2));
                skills.add(s);
            }
        } catch (Exception e) {
            status = "Error load skill: " + e.getMessage();
        }

        return skills;
    }

    //get profile by mentorID
    public Profile getProfile(Mentor m) {
        int mentorID = m.getMentorID();
        String sql = "select * from [Profile] p\n"
                + "where p.mentorID=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Profile p = new Profile();
                p.setProfileID(rs.getInt(1));
                p.setMentor(m);
                p.setExperience(rs.getString(4));
                p.setAchievement(rs.getString(3));
                p.setBio(rs.getString(5));
                return p;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    //get user information of mentor by mentorID 
    public User getUser(Mentor m) {
        int mentorID = m.getMentorID();
        String sql = "select u.fullname,u.dob,u.email,u.address,u.phonenumber,u.gender,u.userID\n"
                + "from [User] u, Mentor m\n"
                + "where u.userID=m.userID and m.mentorID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setFullname(rs.getString(1));
                u.setDob(rs.getDate(2));
                u.setEmail(rs.getString(3));
                u.setAddress(rs.getString(4));
                u.setPhonenumber(rs.getString(5));
                u.setGender(rs.getBoolean(6));
                u.setUserId(rs.getInt(7));
                return u;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    //get user information of mentee by menteeID
    public User getUserMentee(Mentee mt) {
        int menteeID = mt.getMenteeID();
        String sql = "select u.fullname,u.dob,u.email,u.address,u.phonenumber,u.gender,u.userID\n"
                + "from [User] u, Mentee mt\n"
                + "where u.userID=mt.userID and mt.menteeID = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menteeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setFullname(rs.getString(1));
                u.setDob(rs.getDate(2));
                u.setEmail(rs.getString(3));
                u.setAddress(rs.getString(4));
                u.setPhonenumber(rs.getString(5));
                u.setGender(rs.getBoolean(6));
                u.setUserId(rs.getInt(7));
                return u;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    //get user information of mentors belong to a mentee
    public ArrayList<User> getUser(Mentee mt) {
        if (mt == null) {
            return null;
        }
        ArrayList<User> users = new ArrayList<>();
        String sql = "select m.mentorID,u.fullname,u.dob,u.email,u.address,u.phonenumber,u.gender,u.userID\n"
                + "from [User] u, Mentor m\n"
                + "where u.userID=m.userID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setMentorID(rs.getInt(1));
                if (isMentored(m, mt)) {
                    User u = new User();
                    u.setFullname(rs.getString(2));
                    Date d = rs.getDate(3);
                    u.setDob(d);
                    u.setEmail(rs.getString(4));
                    u.setAddress(rs.getString(5));
                    u.setPhonenumber(rs.getString(6));
                    u.setGender(rs.getBoolean(7));
                    u.setUserId(rs.getInt(8));
                    users.add(u);
                }
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return users;
    }

    // get request by id
    public Request getRequest(int requestId) {
        String sql = "select * from Request where requestID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int requestID = rs.getInt(1);
                Mentor mentor = getMentor(rs.getInt(2));
                Mentee mentee = getMentee(rs.getInt(3));
                Date time = null;
                String title = rs.getString(4);
                String reqContent = rs.getString(5);
                String status = rs.getString(6);
                Request req = new Request(requestID, mentor, mentee, time, title, reqContent, status);
                return req;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return null;
    }

    //get mentor information with mentorID
    public Mentor getMentor(int mentorID) {
        String sql = "select * from Mentor\n"
                + "where\n"
                + "mentorID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setMentorID(rs.getInt(1));
                User u = getUser(m);
                m.setUser(u);
                return m;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return null;
    }

    //get user information of mentees belong to a mentor
    public ArrayList<User> getMenteeUsers(Mentor m) {
        if (m == null) {
            return null;
        }
        ArrayList<User> users = new ArrayList<>();
        String sql = "select mt.menteeID,u.fullname,u.dob,u.email,u.address,u.phonenumber,u.gender,u.userID\n"
                + "from [User] u, Mentee mt\n"
                + "where u.userID=mt.userID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentee mt = new Mentee();
                mt.setMenteeID(rs.getInt(1));
                if (isMentored(m, mt)) {
                    User u = new User();
                    u.setFullname(rs.getString(2));
                    Date d = rs.getDate(3);
                    u.setDob(d);
                    u.setEmail(rs.getString(4));
                    u.setAddress(rs.getString(5));
                    u.setPhonenumber(rs.getString(6));
                    u.setGender(rs.getBoolean(7));
                    u.setUserId(rs.getInt(8));
                    users.add(u);
                }
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return users;
    }

    //get information of mentors with userID
    public Mentor getMentor(User user) {
        int userID = user.getUserId();
        System.out.println(userID);
        String sql = "select m.mentorID,m.userID from\n"
                + "Mentor m,[User] u\n"
                + "where \n"
                + "m.userID = u.userID\n"
                + "and m.userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setUser(user);
                m.setMentorID(rs.getInt(1));
                System.out.println(m.toString());
                return m;
            }
        } catch (Exception e) {
            status = "Error get mentor: " + e.getMessage();
        }
        return null;
    }

    //get request from a user 
    public ArrayList<Request> getRequests(User user, boolean role) {
        ArrayList<Request> requests = new ArrayList<>();
        int roleID = 0;
        String sql = "select * from\n"
                + "Request r\n"
                + "where\n"
                + "r.";
        if (role) {
            System.out.println("x1");
            sql += "mentorID=?";
            Mentor mentor = getMentor(user);
            roleID = mentor.getMentorID();
        } else {
            System.out.println("x2");
            sql += "menteeID=?";
            Mentee mentee = getMentee(user);
            roleID = mentee.getMenteeID();
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("x3");
                Request r = new Request();
                r.setRequestID(rs.getInt(1));
                Mentor mentor = new Mentor();
                mentor.setMentorID(rs.getInt(3));
                User userMentor = getUser(mentor);
                mentor.setUser(userMentor);
                Mentee mentee = new Mentee();
                mentee.setMenteeID(rs.getInt(2));
                User userMentee = getUserMentee(mentee);
                mentee.setUser(userMentee);
                r.setMentee(mentee);
                r.setMentor(mentor);
                r.setTitle(rs.getString(4));
                r.setReqContent(rs.getString(5));
                r.setStatus(rs.getString(6));
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt(7));
                requests.add(r);
            }
        } catch (Exception e) {
        }
        return requests;
    }

    //get status between a mentor and a mentee
    public boolean isMentored(Mentor m, Mentee mt) {
        boolean result = false;
        int mentorID = m.getMentorID();
        int menteeID = mt.getMenteeID();
        String sql = "select i.status \n"
                + "from \n"
                + "Invitation i, Mentee mt\n"
                + "where \n"
                + "i.menteeID=mt.menteeID\n"
                + "and i.menteeID = ?\n"
                + "and i.mentorID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menteeID);
            ps.setInt(2, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase("accepted")) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return result;
    }

    public Connection getCon() {
        return con;
    }

    //get rates of a mentor
    public ArrayList<Rating> getRates(Mentor m) {
        ArrayList<Rating> rates = new ArrayList<>();
        int mentorID = m.getMentorID();
        String sql = "select * from Rating r\n"
                + "where r.mentorID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rating r = new Rating();
                r.setMentor(m);
                Mentee mt = new Mentee();
                mt.setMenteeID(rs.getInt(3));
                r.setMentee(mt);
                r.setRateID(rs.getInt(1));
                r.setRateStar(rs.getInt(4));
                rates.add(r);
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return rates;
    }

    //get comments of a mentor
    public ArrayList<Comment> getComments(Mentor m) {
        ArrayList<Comment> comments = new ArrayList<>();
        int mentorID = m.getMentorID();
        String sql = "select * from Comment c\n"
                + "where c.mentorID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setMentor(m);
                Mentee mt = new Mentee();
                mt = getMentee(rs.getInt(3));
                //mt.setMenteeID(rs.getInt(3));
                c.setMentee(mt);
                c.setCommentID(rs.getInt(1));
                c.setCmtContent(rs.getString(4));
                c.setTime(rs.getDate(5));
                comments.add(c);
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return comments;
    }

    //get hash map with mentorID-key formatted time-value
    public HashMap<Integer, String> formattedDate(Mentor m) {
        HashMap<Integer, String> dates = new HashMap<>();
        int mentorID = m.getMentorID();
        String sql = "select c.commentID,format(c.time,'dd-MM-yyyy HH:ss') as 'date' \n"
                + "from Comment c\n"
                + "where c.mentorID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dates.put(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            status = e.getMessage();
        }
        return dates;
    }

    //get mentee by user
    public Mentee getMentee(User u) {
        int userID = (u != null) ? u.getUserId() : -1;
        String sql = "select mt.menteeID from Mentee mt, [User] u\n"
                + "where mt.userID=u.userID and u.userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentee mt = new Mentee();
                mt.setUser(u);
                mt.setMenteeID(rs.getInt(1));
                return mt;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    //get skill by skill ID
    public Skill getSKill(int skillID) {
        String sql = "select * from Skill\n"
                + "where\n"
                + "skillID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, skillID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setSkillId(rs.getInt(1));
                s.setSkillName(rs.getString(2));
                return s;
            }
        } catch (Exception e) {
            status = "Error get skill by skillID: " + e.getMessage();
        }
        return null;
    }

    //get mentee by menteeID
    public Mentee getMentee(int menteeID) {
        String sql = "select mt.menteeID,u.fullname,u.userID from Mentee mt, [User] u\n"
                + "where mt.userID=u.userID and mt.menteeID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, menteeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentee mt = new Mentee();
                User u = new User();
                u.setUserId(rs.getInt(3));
                u.setFullname(rs.getString(2));
                mt.setMenteeID(rs.getInt(1));
                mt.setUser(u);
                return mt;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    public Invitation getInvitation(Mentor m, Mentee mt) {
        int mentorID = m.getMentorID();
        int menteeID = (mt != null) ? mt.getMenteeID() : -1;
        String sql = "select * from Invitation i\n"
                + "where i.menteeID=? and i.mentorID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ps.setInt(2, menteeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Invitation i = new Invitation();
                i.setInvitationID(rs.getInt(1));
                i.setMentor(m);
                i.setMentee(mt);
                i.setStatus(rs.getString(4));
                i.setTime(rs.getDate(5));
                return i;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
    }

    //get mentors who teach a skill
    public ArrayList<Mentor> getMentors(Skill s) {

        String sql = "select * from EnrollSkill\n"
                + "where skillID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getSkillId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setMentorID(rs.getInt(2));
                s.getMentors().add(m);
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return s.getMentors();
    }

    public User getUserByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    //get all mentor
    public ArrayList<Mentor> getAllMentor() {
        ArrayList<Mentor> list = new ArrayList<>();
        String sql = "select * from Mentor";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int mentorId = rs.getInt(1);
                User u = new User();
                u.setUserId(rs.getInt(2));
                String status = rs.getString(3);
                list.add(new Mentor(mentorId, u, status));
            }
        } catch (Exception e) {
            System.out.println("Error at get All mentor: " + e.getMessage());
        }

        return list;
    }

    //get all mentor register
    public ArrayList<MentorRegister> getAllMentorRegister() {
        ArrayList<MentorRegister> list = new ArrayList<>();
        String sql = "select * from MentorRegister";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int mentorRegisterId = rs.getInt(1);
                String achievement = rs.getString(2);
                String exp = rs.getString(3);
                String bio = rs.getString(4);
                String existedSkill = rs.getString(5);
                String newSkill = rs.getString(6);
                User user = new User();
                user.setUserId(rs.getInt(7));
                String seenStatus = rs.getString(8);
                list.add(new MentorRegister(mentorRegisterId, achievement, exp, bio, existedSkill, newSkill, user, seenStatus));
            }
        } catch (Exception e) {
            System.out.println("Error at get All mentor register: " + e.getMessage());
        }

        return list;
    }

    public void insertMentorRegister(String achievement, String exp, String bio, String existedSkill, String otherSkills, int userId, int type) {
        String sql = "";
        if (type == 0) {
            sql = "INSERT INTO [dbo].[MentorRegister]\n"
                    + "           ([achievement]\n"
                    + "           ,[experience]\n"
                    + "           ,[bio]\n"
                    + "           ,[existedSkill]\n"
                    + "           ,[newSkill]\n"
                    + "           ,[userID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT INTO [dbo].[MentorRegister]\n"
                    + "           ([achievement]\n"
                    + "           ,[experience]\n"
                    + "           ,[bio]\n"
                    + "           ,[existedSkill]\n"
                    + "           ,[userID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, achievement);
            ps.setString(2, exp);
            ps.setString(3, bio);
            ps.setString(4, existedSkill);
            if (type == 0) {
                ps.setString(5, otherSkills);
                ps.setInt(6, userId);
            } else {
                ps.setInt(5, userId);

            }
            ps.execute();
        } catch (Exception e) {
            status = "Error at insert mentor register" + e.getMessage();
        }
    }

    public void insertUserToMentee(int userId) {
        String sql = "INSERT INTO [dbo].[Mentee] ([userID]) VALUES(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.execute();
        } catch (Exception e) {
            status = "Error at insert user to mentee" + e.getMessage();
        }
    }

    public void insertUserToMentor(int userId) {
        String sql = "INSERT INTO [dbo].[Mentor] ([userID]\n VALUES(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.execute();
        } catch (Exception e) {
            status = "Error at insert user to mentor" + e.getMessage();
        }
    }

    //update active mentor
    public void updateActiveMentor(int userId) {
        String sql = "update [Mentor] set status = 'active' where userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.execute();
        } catch (Exception e) {
            status = "Error at update mentor active" + e.getMessage();
        }
    }

    //delete mentor register
    public void deleteMentorRegister(int userId) {
        String sql = "DELETE FROM [dbo].[MentorRegister] WHERE userId = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.execute();
        } catch (Exception e) {
            status = "Error at delete mentor register" + e.getMessage();
        }
    }

    //delete mentor
    public void deleteMentor(int userId) {
        String sql = "DELETE FROM [dbo].[Mentor] WHERE userId = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.execute();
        } catch (Exception e) {
            status = "Error at delete mentor" + e.getMessage();
        }
    }

    //get hash map with mentorID-key formatted time-value
    public ArrayList<String> formatDate(int id, String db, String dbid) {
        String sql = "select format(r.time,'dd/MM/yyyy HH:mm') as 'date' from " + db + " r where r." + dbid + " = ?";
        ArrayList<String> dateList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dateList.add(rs.getString(1));
            }
        } catch (Exception e) {
        }
        return dateList;
    }
}
