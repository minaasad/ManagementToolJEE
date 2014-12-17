package au.com.mina_asad.mgmttool.service.impl;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.jpa.JPALabel;
import au.com.mina_asad.mgmttool.model.Label;
import au.com.mina_asad.mgmttool.service.ISVCLabel;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * CardMember Service Object.
 * 
 * <P>This is a service class responsible for operations related to the CardMember object.
 * See {@link au.com.mina_asad.mgmttool.model.Label}.
 * See {@link au.com.mina_asad.mgmttool.model.Label_}.
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPALabel}.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be referenced locally via an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class SVCLabel implements ISVCLabel, Serializable {
    /*
        Enterprise Java Bean References
    */
    @EJB
    JPALabel jLabel;

    /**
     * Service method to assist in creating a new Label.
     * 
     * @param newLabel New Label object.
     * @return Newly generated Label id.
     */
    @Override
    public int create(Label newLabel) {
        return jLabel.create(newLabel);
    }

    @Override
    public List<Label> findAll() {
        return jLabel.findAll();
    }

    @Override
    public Label findById(int labelId) {
        return jLabel.findById(labelId);
    }
    
    @Override
    public Label findByName(String labelName) {
        return jLabel.findByName(labelName);
    }

    @Override
    public int findCountBelongingToBoardId(int boardId) {
        return jLabel.findCountBelongingToBoardId(boardId);
    }

    @Override
    public boolean rename(String newLabelTitle, int existingLabelId) {
        return jLabel.rename(newLabelTitle, existingLabelId);
    }
    
    /**
     * Service method to assist in determining whether a label name is
     * already being used or not.
     *
     * @param labelName Suggested label name to check.
     * @return True if name is taken, otherwise false;
     */
    @Override
    public boolean isNameTaken(String labelName)
    {
        return (findByName(labelName)!=null);
    }
}
