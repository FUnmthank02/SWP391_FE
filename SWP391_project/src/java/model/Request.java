/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Request {
    private int requestID;
    private Mentor mentor;
    private Mentee mentee;
    private Date time;
    private String title;
    private String reqContent;
    private String status;

    public Request() {
    }

    public Request(int requestID, Mentor mentor, Mentee mentee, Date time, String title, String reqContent, String status) {
        this.requestID = requestID;
        this.mentor = mentor;
        this.mentee = mentee;
        this.time = time;
        this.title = title;
        this.reqContent = reqContent;
        this.status = status;
    }

    public int getRequestID() {
        return requestID;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public Date getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getReqContent() {
        return reqContent;
    }

    public String getStatus() {
        return status;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReqContent(String reqContent) {
        this.reqContent = reqContent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
