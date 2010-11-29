// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shekhar.roo.conference.domain;

import com.shekhar.roo.conference.domain.Conference;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Conference_Roo_Entity {
    
    declare @type: Conference: @Entity;
    
    @PersistenceContext
    transient EntityManager Conference.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Conference.id;
    
    @Version
    @Column(name = "version")
    private Integer Conference.version;
    
    public Long Conference.getId() {
        return this.id;
    }
    
    public void Conference.setId(Long id) {
        this.id = id;
    }
    
    public Integer Conference.getVersion() {
        return this.version;
    }
    
    public void Conference.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Conference.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Conference.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Conference attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Conference.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Conference Conference.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Conference merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Conference.entityManager() {
        EntityManager em = new Conference().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Conference.countConferences() {
        return entityManager().createQuery("select count(o) from Conference o", Long.class).getSingleResult();
    }
    
    public static List<Conference> Conference.findAllConferences() {
        return entityManager().createQuery("select o from Conference o", Conference.class).getResultList();
    }
    
    public static Conference Conference.findConference(Long id) {
        if (id == null) return null;
        return entityManager().find(Conference.class, id);
    }
    
    public static List<Conference> Conference.findConferenceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Conference o", Conference.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}