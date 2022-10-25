/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import dal.DAO;
import java.util.ArrayList;
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
    
    // to split existedSkill
    public String handleSplitExistedSkill(String string, String regex) {
        String[] arrString = string.split(regex);
        int arrLength = arrString.length;
        int skillID;
        String existedSkill = "";
        for (int i = 0; i < arrLength; i++) {
            skillID = Integer.parseInt(arrString[i]);
            Skill skill = d.getSKill(skillID);
            existedSkill += (skill.getSkillName() + " ");
        }
        return existedSkill.trim();
    }

    // to split new skill
    public String handleSplitNewSkill(String string, String regex) {
        String[] arrString = string.split(regex);
        int arrLength = arrString.length;
        String newSkill = "";
        for (int i = 0; i < arrLength; i++) {
            newSkill += (arrString[i] + " ");
        }
        return newSkill.trim();
    }
    
    public User getUserbyUsername(String username) {
        for(User u : getListUser()) {
            if(username.equals(u.getUsername()))
                return u;
        }
        return null;
    }
    
    public MentorRegister getMentorRegisterByUserID(int userId) {
        for(MentorRegister o : d.getAllMentorRegister()) {
            if(userId == o.getUser().getUserId()) {
                return o;
            }
        }
        return null;
    }
    
    //get USer by user id
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
   
    //main to test function working or not
    public static void main(String[] args) {
       
    }
}
