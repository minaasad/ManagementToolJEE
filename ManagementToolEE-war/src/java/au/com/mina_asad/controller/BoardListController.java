package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Board;
import au.com.mina_asad.mgmttool.model.BoardList;
import au.com.mina_asad.mgmttool.service.ISVCBoard;
import au.com.mina_asad.mgmttool.service.ISVCBoardList;
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
 * BoardList Backing Bean/Controller
 * 
 * <P>This is a controller responsible for BoardList operations.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class BoardListController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCBoardList ejbBoardList;
    /*
        Data Model/Entity injections
    */
    @Inject
    private BoardList boardList;
    
    @Inject
    private BoardController boardController;

    //Properties
    /**
        * Retrieves {@link #boardList}
        * @return BoardList object within scope.
    */
    public BoardList getBoardList() {
        return boardList;
    }
    
    /**
     * Attempt to load an existing BoardList into the context's BoardList object from the database.
     * Information is gathered from the database table values into the BoardList object
     * @param boardListId The id of the board list.
     * @throws javax.naming.NamingException
     */
    public void loadBoardList(int boardListId) throws NamingException {
        boardList = ejbBoardList.findById(boardListId);
    }
    
    /**
     * Attempt to update an existing BoardList record in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String save() throws NamingException {
        ejbBoardList.rename(boardList.getName(), boardList.getId());
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
        
        int newBoardListId = ejbBoardList.create(boardList, boardController.getBoard().getId());
        
        return returnUrl;
    }
    
    /**
     * Attempt to archive (or hide) an existing BoardList record 
     * in the database.
     * @return an outcome page: the home page.
     * @throws javax.naming.NamingException
     */
    public String archive() throws NamingException {
        ejbBoardList.archiveById(boardList.getId());
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
