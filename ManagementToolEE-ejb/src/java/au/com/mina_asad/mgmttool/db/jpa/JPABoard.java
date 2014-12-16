package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.IBoard;
import au.com.mina_asad.mgmttool.model.Board;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
/**
 * Board JPA Data Access Object.
 * 
 * <P>Responsible for database operations mainly related to the Board entity.
 * See {@link au.com.mina_asad.mgmttool.model.Board} for more information.
 * 
 * <p>Dependency: Uses JPA persistence XML extended from JPAS class
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: Uses NamedQueries from the Board entity/object.
 * See {@link au.com.mina_asad.mgmttool.model.Board} for more information.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be called with an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class JPABoard extends JPAS implements IBoard, Serializable {
    /**
     * Create a new Board record in the Board table.
     * 
     * @param newBoard The newly created Board object.
     * @return Newly generated Board id.
     */
    @Override
    public int create(Board newBoard) {
        em.persist(newBoard);
        em.flush();
        return newBoard.getId();
    }
    
    /**
     * Retrieves all Board records from the Board table.
     * 
     * @return List<> of Board Data Transfer Objects.
     */
    @Override
    public List<Board> findAll() {
        TypedQuery<Board> query = 
        em.createNamedQuery("findAllBoards", Board.class);
        return query.getResultList();
    }

    /**
     * Retrieves a Board from the Board table, 
     * corresponding to a given Board id.
     * 
     * @param boardId The id of an existing Board record.
     * @return A Board object accordingly.
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Board findById(int boardId) {
        Board boardToFind = em.find(Board.class, boardId);
        if (boardToFind == null) {
            throw new EntityNotFoundException("Can't find Board with ID"
                    + boardId);
        }
        return boardToFind;
    }

    /**
     * Archives (or sets hidden=true) an existing Board record 
     * 
     * @param existingBoardId The id of an existing Board record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean archiveById(int existingBoardId) {
        Board dbB = findById(existingBoardId);
            dbB.setHidden(true);
        return true;
    }
}