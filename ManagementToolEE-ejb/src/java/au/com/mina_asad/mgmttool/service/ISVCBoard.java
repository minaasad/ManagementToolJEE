package au.com.mina_asad.mgmttool.service;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Board;
import java.util.List;
import javax.ejb.Local;
/**
 * Local Board Service Interface.
 * 
 * <P>This is a service interface responsible for operations related to the Board object.
 * See {@link au.com.mina_asad.mgmttool.service.impl.SVCBoard}.
 * 
 * <p>Note: This is a Local Enterprise Java Bean Interface. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Local
public interface ISVCBoard {
    public int                  create(Board newBoard);
    public List<Board>          findAll();
    public Board                findById(int boardId);
    public Board                findByName(String boardName);
    public boolean              isNameTaken(String boardName);
    public boolean              archiveById(int existingBoardId);
}
