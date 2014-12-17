package au.com.mina_asad.mgmttool.service;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.BoardList;
import java.util.List;
import javax.ejb.Local;
/**
 * Local BoardList Service Interface.
 * 
 * <P>This is a service interface responsible for operations related to the BoardList object.
 * See {@link au.com.mina_asad.mgmttool.service.impl.SVCBoardList}.
 * 
 * <p>Note: This is a Local Enterprise Java Bean Interface. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Local
public interface ISVCBoardList {
    public int                  create(BoardList newBoardList);
    public List<BoardList>      findAllBelongingToBoardId(int boardId);
    public BoardList            findById(int boardListId);
    public int                  findCountBelongingToBoardId(int boardId);
    public boolean              archiveById(int existingBoardListId);
}
