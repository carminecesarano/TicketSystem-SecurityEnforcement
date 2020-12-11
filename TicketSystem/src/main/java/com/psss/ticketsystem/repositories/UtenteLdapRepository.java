package com.psss.ticketsystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import com.psss.ticketsystem.entities.Utente;

import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;

@Repository("utenteRepositoryLDAP")
public class UtenteLdapRepository implements BaseLdapNameAware {

	 @Autowired
	 private LdapTemplate ldapTemplate;
	 private LdapName baseLdapPath;
	 
	 public void setBaseLdapPath(LdapName baseLdapPath) {
	        this.baseLdapPath = baseLdapPath;
	    }

    public void create(Utente user) {
        Name dn = buildDn(user);
        ldapTemplate.bind(dn, null, buildAttributes(user));
        addMemberToGroup(user.getRole(),user);
    }
    
    public void update(Utente user) {
        ldapTemplate.rebind(buildDn(user), null, buildAttributes(user));
    }

    public void delete(Utente user) {
        ldapTemplate.unbind(buildDn(user));
        removeMemberFromGroup(user.getRole(), user);
    }
    
    public void addMemberToGroup(String groupName, Utente user) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildDn2(user);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("uniqueMember", personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    public void removeMemberFromGroup(String groupName, Utente user) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildDn2(user);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("uniqueMember", personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance()
                .add("ou", "groups")
                .add("cn", groupName)
                .build();
    }
    
    private Name buildDn(Utente user) {
    	 return LdapNameBuilder.newInstance()
	                .add("ou", "people")
	                .add("uid", user.getUsername())
	                .build();
    }
    
    private Name buildDn2(Utente user) {
    	 return LdapNameBuilder.newInstance()
    			 	.add("dc","com")
    			 	.add("dc","ssdgroup")
	                .add("ou", "people")
	                .add("uid", user.getUsername())
	                .build();
    }

    private Attributes buildAttributes(Utente user) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("person");
        ocAttr.add("organizationalPerson");
        ocAttr.add("inetOrgPerson");
        attrs.put(ocAttr);
        attrs.put("ou", "people");
        attrs.put("uid", user.getUsername());
        attrs.put("cn", user.getFullName());
        attrs.put("sn", user.getFullName());
        attrs.put("userPassword",user.getPassword());
        return attrs;
    }
	 	  
}
