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
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;
/**
 * Board(s) Backing Bean/Controller
 * 
 * <P>This is a controller responsible for Board operations on a 
 * different level than of BoardController.
 * See {@link au.com.mina_asad.mgmttool.model.Board}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class BoardsController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCBoard ejbBoard;
    
    /**
     * Attempt to return a list of all boards that exist from the database
     * Information is gathered from the database table values into a collection of board objects
     * @return a collection of Board
     * @throws javax.naming.NamingException
     */
    public Collection<Board> getListOfAllBoards() throws NamingException {
        return new ArrayList<>(ejbBoard.findAllNonHidden());
    }
    
    /**
     * Attempt to return a list of all boards that exist from the database
     * Information is gathered from the database table values into a collection of board objects
     * @return a collection of Board
     * @throws javax.naming.NamingException
     */
    public Collection<Board> getListOfAllBoardsIncludingHidden() throws NamingException {
        return new ArrayList<>(ejbBoard.findAll());
    }
}
