package au.com.mina_asad.mgmttool.service.impl;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.jpa.JPACardMember;
import au.com.mina_asad.mgmttool.model.CardMember;
import au.com.mina_asad.mgmttool.service.ISVCCardMember;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * CardMember Service Object.
 * 
 * <P>This is a service class responsible for operations related to the CardMember object.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember}.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember_}.
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPACardMember}.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class SVCCardMember implements ISVCCardMember, Serializable {
    /*
        Enterprise Java Bean References
    */
    @EJB
    JPACardMember jCardMember;

    /**
     * Service method to assist in creating a new CardMember.
     * 
     * @param newCardMember New CardMember object.
     * @return Newly generated CardMember id.
     */
    
    @Override
    public int create(CardMember newCardMember) {
        return jCardMember.create(newCardMember);
    }

    @Override
    public List<CardMember> findAll() {
        return jCardMember.findAll();
    }

    @Override
    public CardMember findById(int cardMemberId) {
        return jCardMember.findById(cardMemberId);
    }

    @Override
    public boolean archiveById(int existingCardMemberId) {
        return jCardMember.archiveById(existingCardMemberId);
    }
}
