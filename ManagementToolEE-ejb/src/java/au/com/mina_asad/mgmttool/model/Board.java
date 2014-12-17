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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
/**
 * Board JPA Entity Object.
 * 
 * <P>A database entity model related to the "Board" table
 * See @Entity(name="Board").
 * 
 * <p>Note: Creates NamedQueries so that they can be used by the JPA entity manager
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoard} and 
 * {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: This entity has @OneToMany relationships.
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
@Entity(name = "Board")
/**
*  Named Queries.
*/
@NamedQueries({
    @NamedQuery(
        name="findAllBoards",
        query="select bo from Board bo"),
    @NamedQuery(
        name="findAllNonHiddenBoards",
        query="select bo from Board bo " +
        "where bo.hidden = false"),
    @NamedQuery(
        name="findByBoardName",
        query="select bo from Board bo " +
        "where bo.name = :boardName"),
    @NamedQuery(
        name="findCountListsBelongingToBoard",
        query="select count(ls.id) from BoardList ls " +
        "where ls.owner.id = :boardid"),
    @NamedQuery(
        name="findAllListsBelongingToBoard",
        query="select ls from BoardList ls " +
        "where ls.owner.id= :boardid"),
    @NamedQuery(
        name="findCountLabelsBelongingToBoard",
        query="select count(lb.id) from Label lb " +
        "where lb.boardOwner.id = :boardid")
})
public class Board implements Serializable {
    /*
        Attributes
    */
        private int id, currentListCount;
        private String name;
        /*
            By default, a board is NOT hidden.
        */
        private boolean hidden = false;
        /*
            Boards can have zero to many lists.
        */
        private List<BoardList> lists = new ArrayList<>();
        /*
            Boards can have up to 6 labels maximum.
        */
        private List<Label> labels = new ArrayList<>();

    //Constructors
    /**
    *  Empty Constructor.
    */
    public Board() {}
    
    // Properties 
    /** 
    * Board Id. Primary key.
    *  Retrieve {@link #id}
     * @return Board id.
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
     * @return Board name.
    */
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
    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    /**
     *  Retrieve {@link #currentListCount}.
     *  This is a Transient value, and thus cannot be
     *  stored in the Board database table. It
     *  is helpful during runtime only.
     *
     * @return Board's current list count.
     */
    @Transient
    public int getCurrentListCount() {
        return currentListCount;
    }

    public void setCurrentListCount(int currentListCount) {
        this.currentListCount = currentListCount;
    }

    /**
    *  Retrieve {@link #lists}
     * @return List<> of Board lists.
    */
    @OneToMany(mappedBy = "owner")
    public List<BoardList> getLists() {
        return lists;
    }

    public void setLists(List<BoardList> lists) {
        this.lists = lists;
    }
    
    /**
    *  Retrieve {@link #labels}
     * @return List<> of Board labels.
    */
    @OneToMany(mappedBy = "boardOwner")
    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
    
    // Operations
    /**
    *  Return some values of this Board object in string format.
    *  Useful while debugging. 
    */
    @Override
    public String toString()
    {
        return "Board [id="   + id + 
                ", name="       + name + 
                ", isHidden="   + hidden +
                ", currentListCount=" + currentListCount;
    }
}
