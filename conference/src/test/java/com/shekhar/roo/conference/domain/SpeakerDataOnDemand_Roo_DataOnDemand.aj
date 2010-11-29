// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.conference.domain;

import com.shekhar.roo.conference.domain.Speaker;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect SpeakerDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SpeakerDataOnDemand: @Component;
    
    private Random SpeakerDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Speaker> SpeakerDataOnDemand.data;
    
    public Speaker SpeakerDataOnDemand.getNewTransientSpeaker(int index) {
        com.shekhar.roo.conference.domain.Speaker obj = new com.shekhar.roo.conference.domain.Speaker();
        obj.setEmail("email_" + index);
        obj.setFirstName("firstName_" + index);
        obj.setGender(com.shekhar.roo.conference.domain.Gender.class.getEnumConstants()[0]);
        obj.setLastName("lastName_" + index);
        obj.setOrganization("organization_" + index);
        obj.setPassword("password_" + index);
        return obj;
    }
    
    public Speaker SpeakerDataOnDemand.getSpecificSpeaker(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Speaker obj = data.get(index);
        return Speaker.findSpeaker(obj.getId());
    }
    
    public Speaker SpeakerDataOnDemand.getRandomSpeaker() {
        init();
        Speaker obj = data.get(rnd.nextInt(data.size()));
        return Speaker.findSpeaker(obj.getId());
    }
    
    public boolean SpeakerDataOnDemand.modifySpeaker(Speaker obj) {
        return false;
    }
    
    public void SpeakerDataOnDemand.init() {
        data = com.shekhar.roo.conference.domain.Speaker.findSpeakerEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Speaker' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.shekhar.roo.conference.domain.Speaker>();
        for (int i = 0; i < 10; i++) {
            com.shekhar.roo.conference.domain.Speaker obj = getNewTransientSpeaker(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}