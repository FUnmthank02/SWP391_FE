
package model;

/**
 *
 * @author Admin
 */
public class EnrollSkill {
    private int enrollId;
    private Mentor mentor;
    private Skill skill;

    public EnrollSkill() {
    }

    public EnrollSkill(int enrollId, Mentor mentor, Skill skill) {
        this.enrollId = enrollId;
        this.mentor = mentor;
        this.skill = skill;
    }


    public int getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "EnrollSkill{" + "enrollId=" + enrollId + ", mentor=" + mentor + ", skill=" + skill + '}';
    }

    
}
