package au.com.mina_asad.mgmttool.service;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.CardMember;
import java.util.List;
import javax.ejb.Local;
/**
 * Local CardMember Service Interface.
 * 
 * <P>This is a service interface responsible for operations related to the CardMember object.
 * See {@link au.com.mina_asad.mgmttool.service.impl.SVCCardMember}.
 * 
 * <p>Note: This is a Local Enterprise Java Bean Interface. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Local
public interface ISVCCardMember {
    public int                  create(CardMember newCardMember);
    public List<CardMember>     findAll();
    public CardMember           findById(int cardMemberId);
    public boolean              archiveById(int existingCardMemberId);
}
