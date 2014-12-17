package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.CardMember;
import au.com.mina_asad.mgmttool.service.ISVCCardMember;
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
 * CardMember Backing Bean/Controller
 * 
 * <P>This is a controller responsible for CardMember operations.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class CardMemberController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCCardMember ejbCardMember;
    /*
        Data Model/Entity injections
    */
    @Inject
    private CardMember cardMember;

    //Properties
    /**
        * Retrieves {@link #cardMember}
        * @return CardMember object within scope.
    */
    public CardMember getCardMember() {
        return cardMember;
    }
    
    /**
     * Attempt to load an existing CardMember into the context's CardMember
     * object from the database.
     * Information is gathered from the database table values 
     * into the CardMember object
     * @param cardMemberId The id of the CardMember.
     * @throws javax.naming.NamingException
     */
    public void loadCardMember(int cardMemberId) throws NamingException {
        cardMember = ejbCardMember.findById(cardMemberId);
    }
    
    /**
     * Attempt to update an existing CardMember record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String save() throws NamingException {
        ejbCardMember.rename(cardMember.getName(), cardMember.getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Attempt to save a new CardMember into the database.
     * Information is gathered from the new CardMember XHTML 
     * page field value(s) into the CardMember object
     * @return an outcome page: the home page, showing all CardMembers
     * @throws javax.naming.NamingException
     */
    public String saveAsNew() throws NamingException 
    {
        String returnUrl = "index?faces-redirect=true";
        
        if (ejbCardMember.isNameTaken(cardMember.getName()))
        {
            showError("This name is already taken. Please try another", null);
            return null;
        }
        
        int newCardMemberId = ejbCardMember.create(cardMember);
        
        return returnUrl;
    }
    
    /**
     * Attempt to archive (or hide) an existing CardMember record 
     * in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String archive() throws NamingException {
        ejbCardMember.archiveById(cardMember.getId());
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
