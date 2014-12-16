package au.com.mina_asad.mgmttool.db;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.BoardList;
import java.util.List;
/**
 * BoardList DAO Interface.
 * 
 * <P>This is a DAO interface responsible for database operations mainly related to the BoardList entity.
 * <P>See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoardList}.
 * 
 * @author minaasad
 * @version 1.0
 */
public interface IBoardList
{
    public int                  create(BoardList newBoardList);
    public List<BoardList>      findAllBelongingToBoardId(int boardId);
    public BoardList            findById(int boardListId);
    public int                  findCountBelongingToBoardId(int boardId);
    public boolean              archiveById(int existingBoardListId);
}
