package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.db.ILabel;
import au.com.mina_asad.mgmttool.model.Label;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
/**
 * Label JPA Data Access Object.
 * 
 * <P>Responsible for database operations mainly related to the Label entity.
 * See {@link au.com.mina_asad.mgmttool.model.Label} for more information.
 * 
 * <p>Dependency: Uses JPA persistence XML extended from JPAS class
 * See {@link au.com.mina_asad.mgmttool.db.jpa.JPAS} for more information.
 * 
 * <p>Note: Uses NamedQueries from the Label entity/object.
 * See {@link au.com.mina_asad.mgmttool.model.Label} for more information.
 * 
 * <p>Note: This is a @Stateless Enterprise Java Bean. It can be called with an @EJB annotation.
 * 
 * @author minaasad
 * @version 1.0
 */
@Stateless
@LocalBean
public class JPALabel extends JPAS implements ILabel, Serializable {
    /**
     * Create a new Label record in the Label table.
     * 
     * @param newLabel The newly created Label object.
     * @return Newly generated Label id.
     */
    @Override
    public int create(Label newLabel) {
        em.persist(newLabel);
        em.flush();
        return newLabel.getId();
    }
    
    /**
     * Retrieves all Label records from the Label table.
     * 
     * @return List<> of Label Data Transfer Objects.
     */
    @Override
    public List<Label> findAll() {
        TypedQuery<Label> query = 
        em.createNamedQuery("findAllLabels", Label.class);
        return query.getResultList();
    }

    /**
     * Retrieves a Label from the Label table, 
     * corresponding to a given Label id.
     * 
     * @param labelId The id of an existing Label record.
     * @return A Label object accordingly.
     */
    @Override
    public Label findById(int labelId) {
        Label labelToFind = em.find(Label.class, labelId);
        if (labelToFind == null) {
            throw new EntityNotFoundException("Can't find Label with ID"
                    + labelId);
        }
        return labelToFind;
    }
    
    /**
     * Retrieves a Label from the Label table, 
     * corresponding to a given Label name.
     * 
     * @param labelName The name of the Label.
     * @return A Board DTO, or null if no matching board was found.
     */
    @Override
    public Label findByName(String labelName) {
        try 
        {
            TypedQuery<Label> query = 
                em.createNamedQuery("findByLabelName", Label.class);
            query.setParameter("labelName", labelName);
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        }
    }

    @Override
    public int findCountBelongingToBoardId(int boardId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Renames an existing Card record 
     * 
     * @param newLabelTitle The new title to apply to the Label.
     * @param existingLabelId The id of an existing Label record.
     * @return True if no problems were encountered, otherwise false.
     */
    @Override
    public boolean rename(String newLabelTitle, int existingLabelId) {
        Label dbLB = findById(existingLabelId);
            dbLB.setName(newLabelTitle);
            em.flush();
        return true;
    }
}
