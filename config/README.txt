0. Add environment variable: VAULT_ADDR = https://127.0.0.1:8200

1. Import database ticket_system.sql

2. Create a webapp user on db
	CREATE USER 'webapp'@'localhost' IDENTIFIED BY '1234';
	GRANT ALL PRIVILEGES ON *.* TO '1234' WITH GRANT OPTION;

3. Install vault_cert.crt under Trusted Certificates

4. Install app_cert.crt under Trusted Certificates

6. Clone git repository with App and Vault folder

5. Edit vault-config.hcl with our user_name

6. Execute vault-start.sh and vault unseal.sh