# SILPH

Si consiglia l'inserimento manuale nel DB del funzionario, poiché la registrazione non è stata implementata.

esempio:

INSERT INTO public.funzionario(
	id, email, password, username)
	VALUES (1, 'luca@example.com', $2a$10$H8fl2lxJIBMY46CGlT773uQDfNVYM8eFMHIbW4kRxwSgNcvAmQRJW, 'luca');
  
la password è criptata e in particolar modo quella nell'esempio corrisponde alla stringa "ciao1234"
