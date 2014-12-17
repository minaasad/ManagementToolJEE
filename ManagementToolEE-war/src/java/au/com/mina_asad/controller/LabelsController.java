package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.Label;
import au.com.mina_asad.mgmttool.service.ISVCLabel;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;
/**
 * Label(s) Backing Bean/Controller
 * 
 * <P>This is a controller responsible for Label operations on a 
 * different level than of LabelController.
 * See {@link au.com.mina_asad.mgmttool.model.Label}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class LabelsController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCLabel ejbLabel;
    
    /**
     * Attempt to return a list of all Label that exist from the database
     * Information is gathered from the database table values into a collection of Label objects
     * @return a collection of Label
     * @throws javax.naming.NamingException
     */
    public Collection<Label> getListOfAllLabels() throws NamingException {
        return new ArrayList<>(ejbLabel.findAll());
    }
}
