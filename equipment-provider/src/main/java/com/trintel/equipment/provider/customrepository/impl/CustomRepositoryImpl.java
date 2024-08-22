package com.trintel.equipment.provider.customrepository.impl;

import java.lang.reflect.Field;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.trintel.equipment.provider.customrepository.CustomRepository;

public class CustomRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

	private EntityManager em;
	
	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	@Override
	public T saveWithPkReturn(T entity, String fieldName) {
		//fetching the pk generated by oracle db trigger
		try{
		//getting hold of the @Id attribute of EO and making it accessible as it is private
		Field fld = entity.getClass().getDeclaredField(fieldName);
		fld.setAccessible(true);
		String uid = UUID.randomUUID().toString();
		fld.set(entity, uid);
		//persisting the EO with the program generated uid and then detaching it
	    em.persist(entity);
		em.flush();
		em.detach(entity);
		// getting the trigger created pk and storing in EO
		fld.set(entity,(String) em.createNativeQuery("SELECT PK_VAL FROM TEMP_ID_STORE WHERE TEMP_ID = :uid").setParameter("uid", uid).getSingleResult());
		//clearing the field from temp table
		em.createNativeQuery("DELETE FROM TEMP_ID_STORE WHERE TEMP_ID = :uid").setParameter("uid", uid).executeUpdate();
		return entity;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	

}
