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
import java.util.HashMap;
import model.*;

/**
 *
 * @author Account
 */
public class DAO extends DBContext {

    ArrayList<User> userList;
    Connection con;
    String status;

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
        String sql = "select * from Request where "+dbid+" = ?";
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
                Skill skill = getSkill(rs.getInt(7));
                reqList.add(new Request(requestID, mentor, mentee, time, title, reqContent, status, skill));
            }
        } catch (Exception e) {
            status = "Error load user: " + e.getMessage();
        }
        return reqList;
    }

    //load response from database
    public ArrayList<Response> loadResponse(int id, String dbid) {
        ArrayList<Response> resList = new ArrayList<>();
        String sql = "select * from Response where "+dbid+" = ?";
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
        String sql = "INSERT INTO [dbo].[Request]([menteeID],[mentorID],[title],[reqcontent],[status],[skillID],[time])\n"
                + "VALUES(?,?,?,?,?,?,GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            Request req = getRequest(reqId);
            ps.setInt(1, req.getMentee().getMenteeID());
            ps.setInt(2, req.getMentor().getMentorID());
            ps.setString(3, "request title");
            ps.setString(4, resContent);
            ps.setString(5, "open");
            ps.setInt(6, req.getSkill().getSkillId());
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

    // update response
    public void updateResponse(int resId, String ResContent) {
        String sql = "UPDATE [dbo].[Response]\n"
                + "SET [resContent] = ?,[time] = GETDATE()\n"
                + "WHERE responseID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ResContent);
            ps.setInt(2, resId);
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

    public ArrayList<Mentor> getMentorWithTech(String tech) {
        ArrayList<Mentor> listMentor = new ArrayList<Mentor>();

        String sql = "select m.mentorID, m.userID from Mentor m,\n"
                + "(select s.skillID,s.skillName,es.mentorID from Skill s, EnrollSkill es\n"
                + "where s.skillID = es.skillID and s.skillName = ? ) a\n"
                + "where m.mentorID = a.mentorID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tech);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();

                int mentorID = rs.getInt(1);
                user.setUserId(rs.getInt(2));

                Mentor m = new Mentor(mentorID, user);
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
        String sql = "select r.mentorID,cast((sum(rateStar)) as float) / cast((count(rateStar)) as float) as 'averageStar' \n"
                + "from Rating r\n"
                + "group by r.mentorID";

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

    // get skill by id
    public Skill getSkill(int skillId) {
        String sql = "select * from Skill where skillID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt(1));
                skill.setSkillName(rs.getString(2));
                skill.setSkillDescription(rs.getString(3));
                return skill;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return null;
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
                Skill skill = getSkill(rs.getInt(7));
                Request req = new Request(requestID, mentor, mentee, time, title, reqContent, status, skill);
                return req;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }
        return null;
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

    //get user by mentorID 
    public User getUser(Mentor m) {
        int mentorID = m.getMentorID();
        String sql = "select u.fullname,u.dob,u.email,u.address,u.phonenumber,u.gender\n"
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
                return u;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
        }

        return null;
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

    //get mentor by user
    public Mentor getMentor(User u) {
        int userID = (u != null) ? u.getUserId() : -1;
        String sql = "select mt.mentorID from Mentor mt, [User] u\n"
                + "where mt.userID=u.userID and u.userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor mt = new Mentor();
                mt.setUser(u);
                mt.setMentorID(rs.getInt(1));
                return mt;
            }
        } catch (Exception e) {
            status = "Error load enroll skill: " + e.getMessage();
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

    //get mentor by mentorID
    public Mentor getMentor(int mentorID) {
        String sql = "select mt.mentorID,u.fullname,u.userID from Mentor mt, [User] u\n"
                + "where mt.userID=u.userID and mt.mentorID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mentorID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor mt = new Mentor();
                User u = new User();
                u.setUserId(rs.getInt(3));
                u.setFullname(rs.getString(2));
                mt.setMentorID(rs.getInt(1));
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

//load rating
//    //load post from database
//    public void loadPost() {
//        postList = new ArrayList<Post>();
//        String sql = "select * from post_he161531";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int postId = Integer.parseInt(rs.getString(1));
//                String title = rs.getString(2);
//                String content = rs.getString(3);
//                String image = rs.getString(4);
//                String time = rs.getString(5);
//                String hastag = rs.getString(6);
//                postList.add(new Post(postId, title, content, image, time, hastag));
//            }
//        } catch (Exception e) {
//            status = "Error load post: " + e.getMessage();
//        }
//    }
//    
//    //load post from database
//    public ArrayList<Post> loadListPost() {
//        postList = new ArrayList<Post>();
//        String sql = "select * from post_he161531";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int postId = Integer.parseInt(rs.getString(1));
//                String title = rs.getString(2);
//                String content = rs.getString(3);
//                String image = rs.getString(4);
//                String time = rs.getString(5);
//                String hastag = rs.getString(6);
//                postList.add(new Post(postId, title, content, image, time, hastag));
//            }
//        } catch (Exception e) {
//            status = "Error load post: " + e.getMessage();
//        }
//        return postList;
//    }
//
//    //load comment of a post by postId
//    public void loadComment(int id) {
//        cmtList = new ArrayList<Comment>();
//        String sql = "select * from comment_he161531 where postId = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int accId = Integer.parseInt(rs.getString(1));
//                int postId = Integer.parseInt(rs.getString(2));
//                String comment = rs.getString(3);
//                String time = rs.getString(4);
//                cmtList.add(new Comment(accId, postId, comment, time));
//            }
//        } catch (Exception e) {
//            status = "Error load comment: " + e.getMessage();
//        }
//    }
//    
//
//    //insert account to database
//    public void signup(String fname, String user, String pass, String firstAns, String secondAns, String email) {
//        String sql = "insert into account_he161531(fullname,username,[password],firstSecureAns,secondSecureAns,email) values (?,?,?,?,?,?)";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, fname);
//            st.setString(2, user);
//            st.setString(3, pass);
//            st.setString(4, firstAns);
//            st.setString(5, secondAns);
//            st.setString(6, email);
//            st.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    //insert post to database
//    public void insertPost(String title, String content, String image, String time, String hastag) {
//        String sql = "insert into post_he161531(title,content,image,time,hastag) values (?,?,?,?,?)";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, title);
//            st.setString(2, content);
//            st.setString(3, image);
//            st.setString(4, time);
//            st.setString(5, hastag);
//            st.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    //insert comment to database
//    public void insertComment(int accountId, int postId, String comment, String time) {
//        String sql = "insert into comment_he161531 values (?,?,?,?)";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setInt(1, accountId);
//            st.setInt(2, postId);
//            st.setString(3, comment);
//            st.setString(4, time);
//            st.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    // reset password
//    public void updatePass(String pass, String username) {
//        String sql = "update account_he161531 set password = ? where username like ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, pass);
//            ps.setString(2, username);
//            ps.execute();
//        } catch (Exception e) {
//            status = "Error at update password" + e.getMessage();
//        }
//    }
//
//    // to update post
//    public void updatePost(String title, String content, String imageFilename, String strDate, String hastag, int postId) {
//        String sql = "update post_he161531 set title=?,content=?,image=?,time=?,hastag=? where postId = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, title);
//            ps.setString(2, content);
//            ps.setString(3, imageFilename);
//            ps.setString(4, strDate);
//            ps.setString(5, hastag);
//            ps.setInt(6, postId);
//            ps.execute();
//        } catch (Exception e) {
//            status = "Error at update post" + e.getMessage();
//        }
//    }
//
//    // to delete post
//    public void deletePost(int deleteId) {
//        String sql = "Delete from post_he161531 where postId = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, deleteId);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            status = "Error at delete post" + e.getMessage();
//        }
//    
//    }
//
//    public void updateActiveAcc(String username) {
//        String sql = "update account_he161531 set status='active' where username like ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.execute();
//        } catch (Exception e) {
//            status = "Error at update account active" + e.getMessage();
//        }
//
//    }
    public static void main(String[] args) {
        User u = new User();
        u.setUserId(10);
        DAO dao = new DAO();
        dao.updateStatus(2);
    }
}
