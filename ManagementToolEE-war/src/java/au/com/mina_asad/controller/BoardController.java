package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Board;
import au.com.mina_asad.mgmttool.service.ISVCBoard;
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
 * Board Backing Bean/Controller
 * 
 * <P>This is a controller responsible for Board operations.
 * See {@link au.com.mina_asad.mgmttool.model.Board}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class BoardController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCBoard ejbBoard;
    /*
        Data Model/Entity injections
    */
    @Inject
    private Board board;

    //Properties
    /**
        * Retrieves {@link #board}
        * @return Board object within scope.
    */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Attempt to load an existing board into the context's board object from the database.
     * Information is gathered from the database table values into the board object
     * @param boardId The id of the board.
     * @throws javax.naming.NamingException
     */
    public void loadBoard(int boardId) throws NamingException {
        board = ejbBoard.findById(boardId);
    }
    
    /**
     * Attempt to update an existing board record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String save() throws NamingException {
        if (ejbBoard.isNameTaken(board.getName()))
        {
            showError("This name is already taken. Please try another", null);
            return null;
        }
        
        ejbBoard.rename(board.getName(), board.getId());
        return "index?faces-redirect=true";
    }
    
    /**
     * Attempt to save a new board into the database.
     * Information is gathered from the new board XHTML page field value(s) 
     * into the board object
     * @return an outcome page: the home page, showing all boards
     * @throws javax.naming.NamingException
     */
    public String saveAsNew() throws NamingException 
    {
        String returnUrl = "index?faces-redirect=true";
        
        if (ejbBoard.isNameTaken(board.getName()))
        {
            showError("This name is already taken. Please try another", null);
            return null;
        }
        
        int newBoardId = ejbBoard.create(board);
        
        return returnUrl;
    }
    
    /**
     * Attempt to archive (or hide) an existing board record 
     * in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String archive() throws NamingException {
        ejbBoard.archiveById(board.getId());
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
