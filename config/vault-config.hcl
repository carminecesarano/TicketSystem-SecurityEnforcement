listener "tcp" {
 address = "0.0.0.0:8200"
 tls_cert_file = "/users/ccesa/git/repository2/vault/certs/cert.pem"
 tls_key_file  = "/users/ccesa/git/repository2/vault/private/key.pem"
}

storage "file" {
  path = "/users/ccesa/git/repository2/vault/file"
}

disable_mlock = true
api_addr = "https://localhost:8200"
ui=true
