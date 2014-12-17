package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.ICardMember;
import au.com.mina_asad.mgmttool.model.CardMember;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 * CardMember JPA Data Access Object.
 * 
 * <P>Responsible for database operations mainly related to the CardMember entity.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember} for more information.
 * 
 * <p>Dependency: Uses JPA persistence XML extended from JPAS class
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: Uses NamedQueries from the Board entity/object.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember} for more information.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be called with an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class JPACardMember extends JPAS implements ICardMember, Serializable {
    /**
     * Create a new CardMember record in the CardMember table.
     * 
     * @param newCardMember The newly created CardMember object.
     * @return Newly generated CardMember id.
     */
    @Override
    public int create(CardMember newCardMember) {
        em.persist(newCardMember);
        em.flush();
        return newCardMember.getId();
    }
    
    /**
     * Retrieves all CardMember records from the CardMember table.
     * 
     * @return List<> of CardMember Data Transfer Objects.
     */
    @Override
    public List<CardMember> findAll() {
        TypedQuery<CardMember> query = 
        em.createNamedQuery("findAllCardMembers", CardMember.class);
        return query.getResultList();
    }
    
    /**
     * Retrieves all CardMember records from the CardMember table 
     * that are not marked as hidden.
     * 
     * @return List<> of Board Data Transfer Objects.
     */
    @Override
    public List<CardMember> findAllNonHidden() {
        TypedQuery<CardMember> query = 
        em.createNamedQuery("findAllNonHiddenCardMembers", CardMember.class);
        return query.getResultList();
    }

    /**
     * Retrieves a CardMember from the CardMember table, 
     * corresponding to a given CardMember id.
     * 
     * @param cardMemberId The id of an existing CardMember record.
     * @return A CardMember object accordingly.
     */
    @Override
    public CardMember findById(int cardMemberId) {
        CardMember cardMemberToFind = em.find(CardMember.class, cardMemberId);
        if (cardMemberToFind == null) {
            throw new EntityNotFoundException("Can't find CardMemberId with ID"
                    + cardMemberId);
        }
        return cardMemberToFind;
    }
    
    /**
     * Retrieves a CardMember from the CardMember table, 
     * corresponding to a given CardMember name.
     * 
     * @param cardMemberName The name of the CardMember.
     * @return A CardMember DTO, or null if no matching board was found.
     */
    @Override
    public CardMember findByName(String cardMemberName) {
        try 
        {
            TypedQuery<CardMember> query = 
                em.createNamedQuery("findByCardMemberName", CardMember.class);
            query.setParameter("cardMemberName", cardMemberName);
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        }
    }
    
    /**
     * Renames an existing CardMember record 
     * 
     * @param newCardMemberName The new name to apply to the CardMember.
     * @param existingCardMemberId The id of an existing CardMember record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean rename(String newCardMemberName, int existingCardMemberId) {
        CardMember dbCM = findById(existingCardMemberId);
            dbCM.setName(newCardMemberName);
            em.flush();
        return true;
    }

    /**
     * Archives (or sets hidden=true) an existing CardMember record 
     * 
     * @param existingCardMemberId The id of an existing CardMember record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean archiveById(int existingCardMemberId) {
        CardMember dbCM = findById(existingCardMemberId);
            dbCM.setHidden(true);
        return true;
    }
}
