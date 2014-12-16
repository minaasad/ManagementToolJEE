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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Label JPA Entity Object.
 * 
 * <P>A database entity model related to the "Label" table
 * See @Entity(name="Label").
 * 
 * <p>Note: Creates NamedQueries so that they can be used by the JPA entity manager
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPALabel} and 
 * {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: This entity has @OneToOne and @ManyToOne relationships.
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
@Entity(name = "Label")
public class Label implements Serializable {
    /*
        Attributes
    */
        private int id;
        private String name;
        /*
            Reverse relationship declarations.
        */
        private Card cardowner;
        private Board boardowner;
    
    // Constructors
    /**
    *  Empty Constructor.
    */
    public Label() {}
    
    // Properties
    /** 
    * Label Id. Primary key.
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
     * @return Member name.
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
    *  Retrieve {@link #cardowner}.
     * @return Label Card owner.
    */
    @OneToOne(mappedBy = "label")
    public Card getCardOwner() {
        return cardowner;
    }

    public void setCardOwner(Card cardowner) {
        this.cardowner = cardowner;
    }
    
    /** 
    *  Retrieve {@link #boardowner}.
     * @return Label Board owner.
    */
    @ManyToOne
    public Board getBoardOwner() {
        return boardowner;
    }

    public void setBoardOwner(Board boardowner) {
        this.boardowner = boardowner;
    }
    
    // Operations
    /**
    *  Return some values of this Label object in string format.
    *  Useful while debugging. 
    */
    @Override
    public String toString()
    {
        return "Label [id="   + id + 
                ", name="       + name;
    }
}
