package au.com.mina_asad.mgmttool.db.jpa;
/* Github source: https://github.com/minaasad/ManagementToolJEE
 * Copyright (C) 2014 mina asad
 *
 * This program is FREE software; you MAY redistribute it and/or
 * modify it in exchange for some acknowledgement to the author.
 *
*/
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * General JPA extended/supporting class
 * 
 * <P>Responsible for database (JavaDB) operations by providing a @PersistenceContext. See
 * {@link EntityManager} for more information.
 * 
 * @author minaasad
 * @version 1.0
 */
public class JPAS 
{   
    /**
    *  Declare EntityManager em to be used by all JPA objects
    *  <P>See {@link au.com.mina_asad.mgmttool.db.jpa}.
    */
    @PersistenceContext
    EntityManager em;
}
