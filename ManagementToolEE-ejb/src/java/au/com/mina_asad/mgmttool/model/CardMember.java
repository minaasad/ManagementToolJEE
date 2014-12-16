package au.com.mina_asad.mgmttool.model;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * CardMember JPA Entity Object.
 * 
 * <P>A database entity model related to the "CardMember" table
 * See @Entity(name="CardMember").
 * 
 * <p>Note: Creates NamedQueries so that they can be used by the JPA entity manager
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPACardMember} and 
 * {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: This entity has @ManyToOne relationships.
 * 
 * <p>Note: This entity uses Java Validation Constraints.
 * 
 * <p>Note: This is a CDI @Dependent Bean. It can be injected via an @Inject annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Dependent
/**
*  Persistence Entity Name.
*/
@Entity(name = "CardMember")
public class CardMember implements Serializable {
    /*
        Attributes
    */
        private int id;
        private String name;
        /*
            Reverse relationship declaration to the Card entity.
        */
        private Card owner;
        /*
            By default, a CardMember is NOT hidden.
        */
        private boolean hidden = false;
    
    // Constructors
    /**
    *  Empty Constructor.
    */
    public CardMember() {}
    
    // Properties
    /** 
    * CardMember Id. Primary key.
    *  <P>Retrieve {@link #id}.
     * @return Guest id.
    */
    @Id 
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
    *  Retrieve {@link #name}
     * @return CardMember name.
    */
    @NotNull
    @Size(min=2, max=22)
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    *  Retrieve {@link #hidden}
     * @return True if record should be hidden, otherwise false.
    */
    public boolean isHidden() {
        return hidden;
    }
    
    /**
    *  Retrieve {@link #hidden}
     * @return True if record should be hidden, otherwise false.
    */
    @Column(nullable = false)
    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /** 
    *  Retrieve {@link #owner}.
     * @return CardMember Card owner.
    */
    @ManyToOne
    public Card getOwner() {
        return owner;
    }

    public void setOwner(Card owner) {
        this.owner = owner;
    }
    
    // Operations
    /**
    *  Return some values of this BoardList object in string format.
    *  Useful while debugging. 
    */
    @Override
    public String toString()
    {
        return "CardMember [id="   + id + 
                ", name="       + name + 
                ", isHidden="   + hidden;
    }
}
