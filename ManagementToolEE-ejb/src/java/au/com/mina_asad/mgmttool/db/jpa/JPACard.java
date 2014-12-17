package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.ICard;
import au.com.mina_asad.mgmttool.model.BoardList;
import au.com.mina_asad.mgmttool.model.Card;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
/**
 * Card JPA Data Access Object.
 * 
 * <P>Responsible for database operations mainly related to the Card entity.
 * See {@link au.com.mina_asad.mgmttool.model.Card} for more information.
 * 
 * <p>Dependency: Uses JPA persistence XML extended from JPAS class
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: Uses NamedQueries from the Card entity/object.
 * See {@link au.com.mina_asad.mgmttool.model.Card} for more information.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be called with an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class JPACard extends JPAS implements ICard, Serializable {
    /**
     * Create a new Card record in the Card table.
     * 
     * @param newCard The newly created Card object.
     * @return Newly generated Card id.
     */
    @Override
    public int create(Card newCard) {
        em.persist(newCard);
        em.flush();
        return newCard.getId();
    }
    
    /**
     * Retrieves all Card records from the Card table 
     * that are not marked as hidden.
     * 
     * @return List<> of Board Data Transfer Objects.
     */
    @Override
    public List<Card> findAllNonHidden() {
        TypedQuery<Card> query = 
        em.createNamedQuery("findAllNonHiddenCards", Card.class);
        return query.getResultList();
    }
    
    /**
     * Retrieves a Card from the Board table, 
     * corresponding to a given Card id.
     * 
     * @param cardId The id of an existing Card record.
     * @return A Card object accordingly.
     */
    @Override
    public Card findById(int cardId) {
        Card cardToFind = em.find(Card.class, cardId);
        if (cardToFind == null) {
            throw new EntityNotFoundException("Can't find Card with ID"
                    + cardId);
        }
        return cardToFind;
    }
    
    /**
     * Updates the due date value of a Card in
     * the Card table
     * 
     * @param cardId The id of an existing Card record.
     * @param updatedDueDate The new or updated due date value.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean updateDueDate(int cardId, Date updatedDueDate) {
        Card dbC = findById(cardId);
            dbC.setDateDue(updatedDueDate);
        return true;
    }
    
    /**
     * Renames an existing Card record 
     * 
     * @param newCardTitle The new title to apply to the Card.
     * @param existingCardId The id of an existing Card record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean rename(String newCardTitle, int existingCardId) {
        Card dbC = findById(existingCardId);
            dbC.setTitle(newCardTitle);
            em.flush();
        return true;
    }
    
    /**
     * Updates the entirety an existing Card record 
     * 
     * @param existingCardId Valid existing card id.
     * @param newBoardList An existing BoardList where the card will be moved.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean move(int existingCardId, BoardList newBoardList) {
        Card dbC = findById(existingCardId);
            dbC.setOwner(newBoardList);
            em.flush();
        return true;
    }

    /**
     * Archives (or sets hidden=true) an existing BoardList record 
     * 
     * @param existingCardId The id of an existing Card record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean archiveById(int existingCardId) {
        Card dbC = findById(existingCardId);
            dbC.setHidden(true);
        return true;
    }
}
