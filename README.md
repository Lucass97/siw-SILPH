# SILPH

L’agenzia **SILPH SPA** gestisce album e fotografie per conto di fotografi e fa da mediatore per il loro utilizzo da parte di clienti (ad esempio aziende terze che vogliono usarle per campagne pubblicitarie).
Per ulteriori informazioni [clicca qui.](https://uniroma3-my.sharepoint.com/:p:/g/personal/pmerialdo_os_uniroma3_it/EWquRO72NQhIkXoXdv0Z2PkBE4swwAwqqLUYSjVex2T-lQ?rtime=6qxX59H01kg)

Autori del progetto:
- Luca Gregori
- Silviu Cristian Rotaru

Credenziali funzionario:
- Username: admin
- Password: ciao1234

### Consigli:

Si consiglia l'inserimento manuale nel DB del funzionario poiché la registrazione non è stata implementata.

**esempio:**

*INSERT INTO public.funzionario(
	id, email, password, username)
	VALUES (1, 'luca@example.com', $2a$10$H8fl2lxJIBMY46CGlT773uQDfNVYM8eFMHIbW4kRxwSgNcvAmQRJW, 'luca');*
  
- la password è criptata e in particolar modo quella nell'esempio corrisponde alla stringa "ciao1234"
