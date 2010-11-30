package com.shekhar.roo.gae.server.domain;

import org.datanucleus.jpa.annotations.Extension;

privileged aspect Person_Roo_GAE {
        
    declare @field: * Person.id: @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true");

    @Extension(vendorName="datanucleus", key="gae.pk-id", value="true")
    private Long Person.autoId;

    public Long Person.getAutoId() {
        return this.autoId;
    }
    
    public void Person.setAutoId(Long autoId) {
        this.autoId = autoId;
    }
    
}