package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Label;
import au.com.mina_asad.mgmttool.service.ISVCLabel;
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
 * Label Backing Bean/Controller
 * 
 * <P>This is a controller responsible for Label operations.
 * See {@link au.com.mina_asad.mgmttool.model.Label}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class LabelController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCLabel ejbLabel;
    /*
        Data Model/Entity injections
    */
    @Inject
    private Label label;

    //Properties
    /**
        * Retrieves {@link #label}
        * @return Label object within scope.
    */
    public Label getLabel() {
        return label;
    }
    
    /**
     * Attempt to load an existing Label into the context's Label object from the database.
     * Information is gathered from the database table values into the Label object
     * @param labelId The id of the Label.
     * @throws javax.naming.NamingException
     */
    public void loadLabel(int labelId) throws NamingException {
        label = ejbLabel.findById(labelId);
    }
    
    /**
     * Attempt to update an existing Label record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String save() throws NamingException {
        if (ejbLabel.isNameTaken(label.getName()))
        {
            showError("This name is already taken. Please try another", null);
            return null;
        }
        
        ejbLabel.rename(label.getName(), label.getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Attempt to save a new Label into the database.
     * Information is gathered from the new board XHTML page field value(s) 
     * into the board object
     * @return an outcome page: the home page, showing all boards
     * @throws javax.naming.NamingException
     */
    public String saveAsNew() throws NamingException 
    {
        String returnUrl = "index?faces-redirect=true";
        
        if (ejbLabel.isNameTaken(label.getName()))
        {
            showError("This name is already taken. Please try another", null);
            return null;
        }
        
        int newLabelId = ejbLabel.create(label);
        
        return returnUrl;
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
