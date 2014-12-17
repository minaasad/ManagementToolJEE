package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Card;
import au.com.mina_asad.mgmttool.service.ISVCCard;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
/**
 * Card Backing Bean/Controller
 * 
 * <P>This is a controller responsible for Card operations.
 * See {@link au.com.mina_asad.mgmttool.model.Card}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class CardController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCCard ejbCard;
    /*
        Data Model/Entity injections
    */
    @Inject
    private Card card;
    
    @Inject
    private BoardListController boardListController;

    //Properties
    /**
        * Retrieves {@link #card}
        * @return Card object within scope.
    */
    public Card getCard() {
        return card;
    }
    
    /**
     * Attempt to load an existing card into the context's card object from the database.
     * Information is gathered from the database table values into the card object
     * @param cardId The id of the card.
     * @throws javax.naming.NamingException
     */
    public void loadCard(int cardId) throws NamingException {
        card = ejbCard.findById(cardId, boardListController.getBoardList().getId());
    }
    
    /**
     * Attempt to update an existing card record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String save() throws NamingException {
        ejbCard.rename(card.getTitle(), card.getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Attempt to save a new card into the database.
     * Information is gathered from the new card XHTML page field value(s) 
     * into the board object
     * @return an outcome page: the home page, showing all boards
     * @throws javax.naming.NamingException
     */
    public String saveAsNew() throws NamingException 
    {
        String returnUrl = "index?faces-redirect=true";
        
        int newCardId = ejbCard.create(card, boardListController.getBoardList().getId());
        
        return returnUrl;
    }
    
    /**
     * Attempt to update an existing card record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String move() throws NamingException {
        ejbCard.move(card.getId(), boardListController.getBoardList().getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Attempt to archive (or hide) an existing Card record 
     * in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String archive() throws NamingException {
        ejbCard.archiveById(card.getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Adds a message to the context for the messages element.
     * @param message the text of the error message to show the user
     */
    private void showError(String header, String message) 
    {
        try {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, header, message));
        } catch (Exception e) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
