package au.com.mina_asad.mgmttool.service.impl;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.jpa.JPACard;
import au.com.mina_asad.mgmttool.model.Card;
import au.com.mina_asad.mgmttool.service.ISVCCard;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * Card Service Object.
 * 
 * <P>This is a service class responsible for operations related to the Card object.
 * See {@link au.com.mina_asad.mgmttool.model.Card}.
 * See {@link au.com.mina_asad.mgmttool.model.Card_}.
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPACard}.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class SVCCard implements ISVCCard, Serializable {
    /*
        Enterprise Java Bean References
    */
    @EJB
    JPACard jCard;
    
    /**
     * Service method to assist in creating a new Card.
     * 
     * @param newCard New Card object.
     * @return Newly generated Card id.
     */
    @Override
    public int create(Card newCard) {
        return jCard.create(newCard);
    }

    @Override
    public Card findById(int cardId) {
        return jCard.findById(cardId);
    }

    @Override
    public boolean updateDueDate(int cardId, Date updatedDueDate) {
        return jCard.updateDueDate(cardId, updatedDueDate);
    }

    @Override
    public boolean archiveById(int existingCardId) {
        return jCard.archiveById(existingCardId);
    }
}
