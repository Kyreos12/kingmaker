package fr.rjacot.application.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.springframework.dao.DataAccessException;

import fr.rjacot.application.util.exception.runtime.DAOException;

public class DataAccessExceptionAspect {

	@Around("execution(* fr.alteca.service.CalculService.*(..))")
	public Object aroundMethod(final ProceedingJoinPoint pjp) throws Throwable {

		Object retVal;
		try {
			retVal = pjp.proceed();
		} catch (Exception e) {
			throw new DAOException("Une erreur est survenue dans la couche DAO.", e);
		}

		return retVal;
	}
}