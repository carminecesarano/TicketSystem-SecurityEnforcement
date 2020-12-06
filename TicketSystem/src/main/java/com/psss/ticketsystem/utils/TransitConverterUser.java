package com.psss.ticketsystem.utils;

import javax.persistence.AttributeConverter;

import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

public class TransitConverterUser implements AttributeConverter<String, String> {
		
		@Override
		public String convertToDatabaseColumn(String column) {	
			VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
			Plaintext plaintext = Plaintext.of(column);	
			String cipherText = vaultOps.opsForTransit().encrypt("user_data", plaintext).getCiphertext();
			return cipherText;
		}

		@Override
		public String convertToEntityAttribute(String column) {
			VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
			Ciphertext ciphertext = Ciphertext.of(column);
	        String plaintext = vaultOps.opsForTransit().decrypt("user_data", ciphertext).asString();
			return plaintext;
		}

	}