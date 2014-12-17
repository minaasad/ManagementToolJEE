package au.com.mina_asad.mgmttool.db;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Board;
import java.util.List;
/**
 * Board DAO Interface.
 * 
 * <P>This is a DAO interface responsible for database operations mainly related to the Board entity.
 * <P>See {@link au.com.mina_asad.mgmttool.db.jpa.JPABoard}.
 * 
 * @author minaasad
 * @version 1.0
 */
public interface IBoard
{
    public int                  create(Board newBoard);
    public List<Board>          findAll();
    public List<Board>          findAllNonHidden();
    public Board                findById(int boardId);
    public Board                findByName(String boardName);
    public int                  findListsCountBelongingToBoardId(int boardId);
    public boolean              rename(String newBoardName, int existingBoardId);
    public boolean              archiveById(int existingBoardId);
}
