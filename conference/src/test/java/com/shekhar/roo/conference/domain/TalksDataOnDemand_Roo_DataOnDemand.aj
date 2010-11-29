// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.conference.domain;

import com.shekhar.roo.conference.domain.Talks;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect TalksDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TalksDataOnDemand: @Component;
    
    private Random TalksDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Talks> TalksDataOnDemand.data;
    
    public Talks TalksDataOnDemand.getNewTransientTalks(int index) {
        com.shekhar.roo.conference.domain.Talks obj = new com.shekhar.roo.conference.domain.Talks();
        obj.setConference(null);
        obj.setDescription("description_" + index);
        obj.setSpeaker(null);
        obj.setTitle("title_" + index);
        return obj;
    }
    
    public Talks TalksDataOnDemand.getSpecificTalks(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Talks obj = data.get(index);
        return Talks.findTalks(obj.getId());
    }
    
    public Talks TalksDataOnDemand.getRandomTalks() {
        init();
        Talks obj = data.get(rnd.nextInt(data.size()));
        return Talks.findTalks(obj.getId());
    }
    
    public boolean TalksDataOnDemand.modifyTalks(Talks obj) {
        return false;
    }
    
    public void TalksDataOnDemand.init() {
        data = com.shekhar.roo.conference.domain.Talks.findTalksEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Talks' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.shekhar.roo.conference.domain.Talks>();
        for (int i = 0; i < 10; i++) {
            com.shekhar.roo.conference.domain.Talks obj = getNewTransientTalks(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
