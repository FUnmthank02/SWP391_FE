
package model;

/**
 *
 * @author Admin
 */
public class MentorRegister {
    private int mentorRegisterId;
    private String achievement;
    private String exp;
    private String bio;
    private String existedSkill;
    private String newSkill;
    private User user;
    private String seenStatus;

    public MentorRegister() {
    }

    public MentorRegister(int mentorRegisterId, String achievement, String exp, String bio, String existedSkill, String newSkill, User user, String seenStatus) {
        this.mentorRegisterId = mentorRegisterId;
        this.achievement = achievement;
        this.exp = exp;
        this.bio = bio;
        this.existedSkill = existedSkill;
        this.newSkill = newSkill;
        this.user = user;
        this.seenStatus = seenStatus;
    }

    public String getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(String seenStatus) {
        this.seenStatus = seenStatus;
    }

   

    public int getMentorRegisterId() {
        return mentorRegisterId;
    }

    public void setMentorRegisterId(int mentorRegisterId) {
        this.mentorRegisterId = mentorRegisterId;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getExistedSkill() {
        return existedSkill;
    }

    public void setExistedSkill(String existedSkill) {
        this.existedSkill = existedSkill;
    }

    public String getNewSkill() {
        return newSkill;
    }

    public void setNewSkill(String newSkill) {
        this.newSkill = newSkill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MentorRegister{" + "mentorRegisterId=" + mentorRegisterId + ", achievement=" + achievement + ", exp=" + exp + ", bio=" + bio + ", existedSkill=" + existedSkill + ", newSkill=" + newSkill + ", user=" + user + '}';
    }
    
    
}
