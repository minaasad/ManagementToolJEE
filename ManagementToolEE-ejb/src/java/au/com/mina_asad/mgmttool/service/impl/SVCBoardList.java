package au.com.mina_asad.mgmttool.service.impl;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.jpa.JPABoardList;
import au.com.mina_asad.mgmttool.model.BoardList;
import au.com.mina_asad.mgmttool.service.ISVCBoardList;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * BoardList Service Object.
 * 
 * <P>This is a service class responsible for operations related to the BoardList object.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList}.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList_}.
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoardList}.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class SVCBoardList implements ISVCBoardList, Serializable {
    /*
        Enterprise Java Bean References
    */
    @EJB
    JPABoardList jBoardList;

    /**
     * Service method to assist in creating a new BoardList.
     * 
     * @param newBoardList New BoardList object.
     * @return Newly generated BoardList id.
     */
    @Override
    public int create(BoardList newBoardList) {
        return jBoardList.create(newBoardList);
    }

    @Override
    public List<BoardList> findAllBelongingToBoardId(int boardId) {
        return jBoardList.findAllBelongingToBoardId(boardId);
    }

    @Override
    public BoardList findById(int boardListId) {
        return jBoardList.findById(boardListId);
    }

    @Override
    public int findCountBelongingToBoardId(int boardId) {
        return jBoardList.findCountBelongingToBoardId(boardId);
    }

    @Override
    public boolean archiveById(int existingBoardListId) {
        return jBoardList.archiveById(existingBoardListId);
    }
}
