// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.kingkey.qn.domain;

import com.kingkey.qn.domain.Part;
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

privileged aspect Part_Roo_Entity {
    
    declare @type: Part: @Entity;
    
    @PersistenceContext
    transient EntityManager Part.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Part.id;
    
    @Version
    @Column(name = "version")
    private Integer Part.version;
    
    public Long Part.getId() {
        return this.id;
    }
    
    public void Part.setId(Long id) {
        this.id = id;
    }
    
    public Integer Part.getVersion() {
        return this.version;
    }
    
    public void Part.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Part.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Part.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Part attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Part.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Part Part.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Part merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Part.entityManager() {
        EntityManager em = new Part().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Part.countParts() {
        return entityManager().createQuery("select count(o) from Part o", Long.class).getSingleResult();
    }
    
    public static List<Part> Part.findAllParts() {
        return entityManager().createQuery("select o from Part o", Part.class).getResultList();
    }
    
    public static Part Part.findPart(Long id) {
        if (id == null) return null;
        return entityManager().find(Part.class, id);
    }
    
    public static List<Part> Part.findPartEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Part o", Part.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
