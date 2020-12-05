package com.psss.ticketsystem.services;

import javax.persistence.AttributeConverter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

public class TransitConverter implements AttributeConverter<String, String> {
	
	@Override
	public String convertToDatabaseColumn(String column) {	
		VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
		Plaintext plaintext = Plaintext.of(column);	
		String cipherText = vaultOps.opsForTransit().encrypt("data", plaintext).getCiphertext();
		return cipherText;
	}

	@Override
	public String convertToEntityAttribute(String column) {
		VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
		Ciphertext ciphertext = Ciphertext.of(column);
        String plaintext = vaultOps.opsForTransit().decrypt("data", ciphertext).asString();
		return plaintext;
	}

}

@Service
class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}