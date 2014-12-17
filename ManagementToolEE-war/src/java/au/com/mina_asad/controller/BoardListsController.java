package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.BoardList;
import au.com.mina_asad.mgmttool.service.ISVCBoardList;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;
/**
 * BoardList(s) Backing Bean/Controller
 * 
 * <P>This is a controller responsible for BoardList operations on a 
 * different level than of BoardListController.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class BoardListsController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCBoardList ejbBoardList;
    
    /**
     * Attempt to return a list of all boardLists that exist from the database.
     * Information is gathered from the database table values into a collection
     * of boardList objects
     * @return a collection of BoardList
     * @throws javax.naming.NamingException
     */
    public Collection<BoardList> getListOfAllBoardLists() throws NamingException {
        return new ArrayList<>(ejbBoardList.findAllNonHidden());
    }
    
    /**
     * Attempt to return a list of all boardLists that belong to an existing board.
     * Information is gathered from the database table values into a collection
     * of boardList objects
     * @param boardId The id of a valid and existing Board record
     * @return a collection of BoardList
     * @throws javax.naming.NamingException
     */
    public Collection<BoardList> getListOfAllBoardListsBelongingToBoardId(int boardId) 
            throws NamingException {
        return new ArrayList<>(ejbBoardList.findAllBelongingToBoardId(boardId));
    }
}
