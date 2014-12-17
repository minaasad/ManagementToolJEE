package au.com.mina_asad.mgmttool.db;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.CardMember;
import java.util.List;
/**
 * CardMember DAO Interface.
 * 
 * <P>This is a DAO interface responsible for database operations mainly related to the CardMember entity.
 * <P>See {@link au.com.mina_asad.mgmttool.db.jpa.JPACardMember}.
 * 
 * @author minaasad
 * @version 1.0
 */
public interface ICardMember
{
    public int                  create(CardMember newCardMember);
    public List<CardMember>     findAll();
    public List<CardMember>     findAllNonHidden();
    public CardMember           findById(int cardMemberId);
    public CardMember           findByName(String cardMemberName);
    public boolean              rename(String newCardMemberName, int existingCardMemberId);
    public boolean              archiveById(int existingCardMemberId);
}
