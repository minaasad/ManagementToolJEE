package au.com.mina_asad.controller;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import au.com.mina_asad.mgmttool.model.CardMember;
import au.com.mina_asad.mgmttool.service.ISVCCardMember;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;
/**
 * CardMember(s) Backing Bean/Controller
 * 
 * <P>This is a controller responsible for CardMember operations on a 
 * different level than of CardMemberController.
 * See {@link au.com.mina_asad.mgmttool.model.CardMember}.
 * 
 * <p>Note: This is a @RequestScoped bean. No sessions are initiated.
 * 
 * @author minaasad
 * @version 1.0
 */
@Named
@RequestScoped
public class CardMembersController {
    /*
        Local EJB references.
    */
    @EJB
    ISVCCardMember ejbCardMember;
    
    /**
     * Attempt to return a list of all CardMembers that exist from the database
     * Information is gathered from the database table values into a collection of CardMember objects
     * @return a collection of CardMember
     * @throws javax.naming.NamingException
     */
    public Collection<CardMember> getListOfAllCardMembers() throws NamingException {
        return new ArrayList<>(ejbCardMember.findAllNonHidden());
    }
}
