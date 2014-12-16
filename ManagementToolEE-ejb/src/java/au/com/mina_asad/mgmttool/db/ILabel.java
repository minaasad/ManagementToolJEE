package au.com.mina_asad.mgmttool.db;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Label;
import java.util.List;
/**
 * Label DAO Interface.
 * 
 * <P>This is a DAO interface responsible for database operations mainly related to the Label entity.
 * <P>See {@link au.com.mina_asad.mgmttool.db.jpa.JPALabel}.
 * 
 * @author minaasad
 * @version 1.0
 */
public interface ILabel
{
    public int                  create(Label newLabel);
    public List<Label>          findAll();
    public Label                findById(int labelId);
    public int                  findCountBelongingToBoardId(int boardId);
}
