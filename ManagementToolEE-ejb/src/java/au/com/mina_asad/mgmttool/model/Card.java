package au.com.mina_asad.mgmttool.model;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Card JPA Entity Object.
 * 
 * <P>A database entity model related to the "Card" table
 * See @Entity(name="Card").
 * 
 * <p>Note: Creates NamedQueries so that they can be used by the JPA entity manager
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPACard} and 
 * {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: This entity has @OneToOne, @OneToMany and @ManyToOne relationships.
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
@Entity(name = "Card")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(
        name="findAllNonHiddenCards",
        query="select c from Card c " +
        "where c.hidden = false")
})
public class Card implements Serializable {
    /*
        Attributes
    */
        private int id, currentMemberCount;
        /*
            All cards must at least have a mandatory title.
        */
        private String title, description;
        /*
            Reverse relationship declaration to the Board entity.
        */
        private BoardList owner;
        private Label label = new Label();
        private CardMember member = new CardMember();
        /*
            By default, a card is NOT hidden.
        */
        private boolean hidden = false;
        private Date dateDue;
    
    // Constructors
    /**
    *  Empty Constructor.
    */
    public Card() {}
    
    // Properties
    /** 
    * BoardList Id. Primary key.
    *  <P>Retrieve {@link #id}.
     * @return Card id.
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
    *  Retrieve {@link #title}
     * @return Card title (mandatory).
    */
    @NotNull
    @Size(min=2, max=22)
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
    *  Retrieve {@link #description}
     * @return Card description (optional).
    */
    @Column(nullable = true)
    @Size(max=50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
    *  Retrieve {@link #dateDue}
     * @return Card due date.
    */
    @Temporal(TemporalType.DATE)
    @Future
    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
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
    *  Retrieve {@link #member}
     * @return Member assigned to this card.
    */
    @OneToOne
    public CardMember getMember() {
        return member;
    }

    public void setMember(CardMember member) {
        this.member = member;
    }
    
    /**
    *  Retrieve {@link #label}
     * @return Card label.
    */
    @OneToOne
    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    
    /**
     *  Retrieve {@link #currentMemberCount}.
     *  This is a Transient value, and thus cannot be
     *  stored in the Card database table. It
     *  is helpful during runtime only.
     *
     * @return Card's current member count.
     */
    @Transient
    public int getCurrentMemberCount() {
        return currentMemberCount;
    }

    public void setCurrentMemberCount(int currentMemberCount) {
        this.currentMemberCount = currentMemberCount;
    }
    
    /** 
    *  Retrieve {@link #owner}.
     * @return Card BoardList owner.
    */
    @ManyToOne
    public BoardList getOwner() {
        return owner;
    }

    public void setOwner(BoardList owner) {
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
        return "Card [id="   + id + 
                ", title="       + title + 
                ", isHidden="   + hidden +
                ", desc=" + description;
    }
}
