package com.shekhar.roo.feedback.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.dbre.RooDbManaged;

@RooJavaBean
@RooToString
@RooEntity(versionField = "", table = "feedback", catalog = "feedback_schema")
@RooDbManaged(automaticallyDelete = true)
public class Feedback {
}
