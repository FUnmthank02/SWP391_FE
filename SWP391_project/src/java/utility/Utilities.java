/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package utility;

import dal.DAO;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import model.*;

/**
 *
 * @author Admin
 */
public class Utilities {

    public Utilities() {
    }

    DAO d = new DAO();
    ArrayList<User> listUser = d.loadListUser();

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public User getUser(int userId) {
        for (User u : getListUser()) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    //check existed email
    public boolean checkExistedEmail(String email) {
        for (User u : listUser) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    //check existed username
    public boolean checkExistedUsername(String username) {
        for (User u : listUser) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public User getExistedUser(String username, String password) {
        User user = null;
        for (User u : listUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        return user;

    }

    public String formateDate(Date date) {
        Instant now = date.toInstant();
        System.out.println(now.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
        return formatter.format(now);
    }

//    public ArrayList<User> findMentorByTech(String tech) {
//        ArrayList<Mentor> listMentor = d.getMentorWithTech(tech);
//        ArrayList<User> listFound = new ArrayList<>();
//
//        for (User u : listUser) {
//            for (Mentor i : listMentor) {
//                if (u.getUserId() == i.getUserId()) {
//                    listFound.add(u);
//                }
//            }
//        }
//        return listFound;
//    }
    //   public ArrayList<Rating> getRatingStar(String tech) {
////        int totalRate = 0;
////        int count = 0;
//        ArrayList<Mentor> listMentor = d.getMentorWithTech(tech);
//        ArrayList<Rating> listRate = new ArrayList<>();
//        for (Mentor m : listMentor) {
//            for (Rating r : d.getRating()) {
//                if (m.getMentorId() == r.getMentorId()) {
//                    listRate.add(r);
//                }
//            }
//
//        }
////        int rateStar = totalRate / count; //chia lay phan nguyen
    //return listRate;
    //  }
//    public void getRate(String tech) {
//        ArrayList<Mentor> listMentor = d.getMentorWithTech(tech);
//        int totalRate = 0;
//        int mentorId = 0;
//        int menteeId = 0;
//        for (Mentor m : listMentor) {
//            for(Rating r : getRatingStar(tech)) {
//                if(m.getMentorId() == r.getMentorId()) {
//                    totalRate += r.getRateStar();
//                    mentorId = r.getMentorId();
//                    menteeId = r.getMenteeId();
//                }
//                listMentor.add(new Rating(menteeId, mentorId, menteeId, menteeId))
//            }
//        }
//        
//        
//
//    }
//    public ArrayList<String> 
    //main to test function working or not
    public static void main(String[] args) {
        Utilities uti = new Utilities();
        DAO dao = new DAO();

        for (Response response : dao.loadResponse()) {
            System.out.println(uti.formateDate(response.getTime()));
        }
    }
}
