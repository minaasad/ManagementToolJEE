package au.com.mina_asad.mgmttool.service;
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
import javax.ejb.Local;
/**
 * Local Card Service Interface.
 * 
 * <P>This is a service interface responsible for operations related to the Card object.
 * See {@link au.com.mina_asad.mgmttool.service.impl.SVCCard}.
 * 
 * <p>Note: This is a Local Enterprise Java Bean Interface. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Local
public interface ISVCCard {
    public int                  create(Card newCard, int BoardListId);
    public List<Card>           findAllNonHidden();
    public Card                 findById(int cardId, int ownerId);
    public boolean              updateDueDate (int cardId, Date updatedDueDate);
    public boolean              move(int existingCardId, int existingBoardListLocation);
    public boolean              rename(String newCardTitle, int existingCardId);
    public boolean              archiveById(int existingCardId);
}
