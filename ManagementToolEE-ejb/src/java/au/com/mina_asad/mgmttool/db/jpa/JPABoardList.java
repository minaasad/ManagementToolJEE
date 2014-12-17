package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.IBoardList;
import au.com.mina_asad.mgmttool.model.BoardList;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 * BoardList JPA Data Access Object.
 * 
 * <P>Responsible for database operations mainly related to the BoardList entity.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList} for more information.
 * 
 * <p>Dependency: Uses JPA persistence XML extended from JPAS class
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: Uses NamedQueries from the BoardList entity/object.
 * See {@link au.com.mina_asad.mgmttool.model.BoardList} for more information.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be called with an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class JPABoardList extends JPAS implements IBoardList, Serializable {
    /**
     * Create a new Board record in the Board table.
     * 
     * @param newBoardList The newly created BoardList object.
     * @return Newly generated BoardList id.
     */
    @Override
    public int create(BoardList newBoardList) {
        em.persist(newBoardList);
        em.flush();
        return newBoardList.getId();
    }
    
    /**
     * Retrieves all BoardList objects belonging to 
     * an existing Board.
     * 
     * @param boardId The id of an existing Board record.
     * @return List<> of BoardList Data Transfer Objects.
     */
    @Override
    public List<BoardList> findAllBelongingToBoardId(int boardId) {
        TypedQuery<BoardList> query = 
        em.createNamedQuery("findAllListsBelongingToBoard", BoardList.class);
        return query.getResultList();
    }
    
    /**
     * Retrieves all BoardList records from the BoardList table 
     * that are not marked as hidden.
     * 
     * @return List<> of Board Data Transfer Objects.
     */
    @Override
    public List<BoardList> findAllNonHidden() {
        TypedQuery<BoardList> query = 
        em.createNamedQuery("findAllNonHiddenBoardLists", BoardList.class);
        return query.getResultList();
    }
    
    /**
     * Retrieves a Board from the Board table, 
     * corresponding to a given Board id.
     * 
     * @param boardListId The id of an existing BoardList record.
     * @return A BoardList object accordingly.
     */
    @Override
    public BoardList findById(int boardListId) {
        BoardList boardListToFind = em.find(BoardList.class, boardListId);
        if (boardListToFind == null) {
            throw new EntityNotFoundException("Can't find BoardList with ID"
                    + boardListId);
        }
        return boardListToFind;
    }
    
    /**
     * Find the number of BoardList objects belonging to a Board id.
     * 
     * @param boardId Valid Board id.
     * @return Number of BoardLists belonging to a given Board id.
     */
    @Override
    public int findCountBelongingToBoardId(int boardId) {
        Query query = 
            em.createNamedQuery("findCountListsBelongingToBoard");
        query.setParameter("boardid", boardId);
        return Integer.parseInt(query.getSingleResult().toString());
    }
    
    @Override
    public int findCardsCountBelongingToBoardListId(int boardListId) {
        Query query = 
            em.createNamedQuery("findCountCardsBelongingToBoardList");
        query.setParameter("boardlistid", boardListId);
        return Integer.parseInt(query.getSingleResult().toString());
    }
    
    /**
     * Renames an existing BoardList record 
     * 
     * @param newBoardListName The new name to apply to the BoardList.
     * @param existingBoardListId The id of an existing BoardList record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean rename(String newBoardListName, int existingBoardListId) {
        BoardList dbBL = findById(existingBoardListId);
            dbBL.setName(newBoardListName);
            em.flush();
        return true;
    }

    /**
     * Archives (or sets hidden=true) an existing BoardList record 
     * 
     * @param existingBoardListId The id of an existing BoardList record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean archiveById(int existingBoardListId) {
        BoardList dbBL = findById(existingBoardListId);
            dbBL.setHidden(true);
        return true;
    }
}
