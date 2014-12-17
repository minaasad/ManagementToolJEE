package au.com.mina_asad.mgmttool.service.impl;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.jpa.JPABoard;
import au.com.mina_asad.mgmttool.model.Board;
import au.com.mina_asad.mgmttool.service.ISVCBoard;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * Bride Service Object.
 * 
 * <P>This is a service class responsible for operations related to the Board object.
 * See {@link au.com.mina_asad.mgmttool.model.Board}.
 * See {@link au.com.mina_asad.mgmttool.model.Board_}.
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoard}.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class SVCBoard implements ISVCBoard, Serializable {
    /*
        Enterprise Java Bean References
    */
    @EJB
    JPABoard jBoard;

    /**
     * Service method to assist in creating a new Board.
     * 
     * @param newBoard New Board object.
     * @return Newly generated Board id.
     */
    @Override
    public int create(Board newBoard) {
        return jBoard.create(newBoard);
    }

    @Override
    public List<Board> findAll() {
        return jBoard.findAll();
    }

    @Override
    public Board findById(int boardId) {
        return jBoard.findById(boardId);
    }
    
    @Override
    public Board findByName(String boardName) {
        return jBoard.findByName(boardName);
    }

    @Override
    public boolean archiveById(int existingBoardId) {
        return jBoard.archiveById(existingBoardId);
    }
    
    /**
     * Service method to assist in determining whether a board name is
     * already being used or not.
     *
     * @param boardName Suggested board name to check.
     * @return True if name is taken, otherwise false;
     */
    @Override
    public boolean isNameTaken(String boardName)
    {
        return (findByName(boardName)!=null);
    }
}
