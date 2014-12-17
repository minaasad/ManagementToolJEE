package au.com.mina_asad.mgmttool.model;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * BoardList JPA Entity Object.
 * 
 * <P>A database entity model related to the "BoardList" table
 * See @Entity(name="BoardList").
 * 
 * <p>Note: Creates NamedQueries so that they can be used by the JPA entity manager
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoardList} and 
 * {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: This entity has @OneToMany and @ManyToOne relationships.
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
@Entity(name = "BoardList")
/**
*  Named Queries.
*/
@NamedQueries({
    @NamedQuery(
        name="findAllNonHiddenBoardLists",
        query="select bl from BoardList bl " +
        "where bl.hidden = false"),
    @NamedQuery(
        name="findCountCardsBelongingToBoardList",
        query="select count(c.id) from Card c " +
        "where c.owner.id = :boardlistid")
})
public class BoardList implements Serializable {
    /*
        Attributes
    */
        private int id, orderNumber, currentCardCount;
        private String name;
        /*
            Reverse relationship declaration to the Board entity.
        */
        private Board owner;
        /*
            Lists can have zero to many cards.
        */
        private List<Card> cards = new ArrayList<>();
        /*
            By default, a list is NOT hidden.
        */
        private boolean hidden = false;
    
    // Constructors
    /**
    *  Empty Constructor.
    */
    public BoardList() {}
    
    // Properties
    /** 
    * BoardList Id. Primary key.
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
     * @return BoardList name.
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
    *  Retrieve {@link #lists}
     * @return List<> of Board lists.
    */
    @OneToMany(mappedBy = "owner")
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    /**
     *  Retrieve {@link #currentCardCount}.
     *  This is a Transient value, and thus cannot be
     *  stored in the BoardList database table. It
     *  is helpful during runtime only.
     *
     * @return List's current card count.
     */
    @Transient
    public int getCurrentCardCount() {
        return currentCardCount;
    }

    public void setCurrentCardCount(int currentCardCount) {
        this.currentCardCount = currentCardCount;
    }
    
    /** 
    *  Retrieve {@link #owner}.
     * @return BoardList Board owner.
    */
    @ManyToOne
    public Board getOwner() {
        return owner;
    }

    public void setOwner(Board owner) {
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
        return "BoardList [id="   + id + 
                ", name="       + name + 
                ", isHidden="   + hidden +
                ", order=" + orderNumber;
    }
}
