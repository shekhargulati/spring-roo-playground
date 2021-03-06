// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.playground.roo.poll.domain;

import com.shekhar.playground.roo.poll.domain.Poll;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect PollDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PollDataOnDemand: @Component;
    
    private Random PollDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Poll> PollDataOnDemand.data;
    
    public Poll PollDataOnDemand.getNewTransientPoll(int index) {
        com.shekhar.playground.roo.poll.domain.Poll obj = new com.shekhar.playground.roo.poll.domain.Poll();
        obj.setQuestion("question_" + index);
        return obj;
    }
    
    public Poll PollDataOnDemand.getSpecificPoll(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Poll obj = data.get(index);
        return Poll.findPoll(obj.getId());
    }
    
    public Poll PollDataOnDemand.getRandomPoll() {
        init();
        Poll obj = data.get(rnd.nextInt(data.size()));
        return Poll.findPoll(obj.getId());
    }
    
    public boolean PollDataOnDemand.modifyPoll(Poll obj) {
        return false;
    }
    
    public void PollDataOnDemand.init() {
        data = com.shekhar.playground.roo.poll.domain.Poll.findPollEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Poll' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.shekhar.playground.roo.poll.domain.Poll>();
        for (int i = 0; i < 10; i++) {
            com.shekhar.playground.roo.poll.domain.Poll obj = getNewTransientPoll(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
