// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.feedback.domain;

import java.lang.String;

privileged aspect Feedback_Roo_ToString {
    
    public String Feedback.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Sessionname: ").append(getSessionname()).append(", ");
        sb.append("Speaker: ").append(getSpeaker()).append(", ");
        sb.append("Fullname: ").append(getFullname()).append(", ");
        sb.append("Feedback: ").append(getFeedback()).append(", ");
        sb.append("Id: ").append(getId());
        return sb.toString();
    }
    
}