package au.com.mina_asad.mgmttool.db;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Card;
import java.util.Date;
import java.util.List;
/**
 * Card DAO Interface.
 * 
 * <P>This is a DAO interface responsible for database operations mainly related to the Card entity.
 * <P>See {@link au.com.mina_asad.mgmttool.db.jpa.JPACard}.
 * 
 * @author minaasad
 * @version 1.0
 */
public interface ICard
{
    public int                  create(Card newCard);
    public List<Card>           findAllNonHidden();
    public Card                 findById(int cardId);
    public boolean              updateDueDate (int cardId, Date updatedDueDate);
    public boolean              rename(String newCardTitle, int existingCardId);
    public boolean              archiveById(int existingCardId);
}
