package com.shekhar.roo.gae.server.domain;

import org.datanucleus.jpa.annotations.Extension;

privileged aspect Address_Roo_GAE {

    declare @field: * Address.id: @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true");

    @Extension(vendorName="datanucleus", key="gae.pk-id", value="true")
    private Long Address.autoId;

    public Long Address.getAutoId() {
        return this.autoId;
    }
    
    public void Address.setAutoId(Long autoId) {
        this.autoId= autoId;
    }

}
