package br.com.ot4.proposta.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;



public class UniqueValidator implements ConstraintValidator<UniqueValue, Object> {
	

	 	@PersistenceContext
	 	private EntityManager manager;
	 	
	    private String domainAttribute;
	    private Class<?> klass;

	    @Override
	    public void initialize(UniqueValue unique) {
	    	domainAttribute = unique.fieldName();
	    	klass = unique.domainClass();
	    }

	    @Override
	    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
	    	Query query = manager.createQuery("select 1 from " +klass.getName()+" where "+domainAttribute+"=:value");
	        query.setParameter("value", o);
	        List<?> list = query.getResultList();
	        Assert.state(list.size()<=1, "Foi encontrado mais de um "+klass+" com o atributo "+domainAttribute+" = "+o);
	    	return list.isEmpty();
	    }

}
