# Telepass
Telepass Challenge

Una volta clonato il repository ed importato il progetto nell'ide di sviluppo (nel mio caso Intellij IDEA),
eseguire i comandi maven "clean install" per la build del progetto stesso.

Completata la build, 
eseguire tramite la run di springboot la classe com.telepass.challenge.ChallengeApplication.

Una volta avviato il server sarà possibile, 
tramite le collection PostMan allegate nella cartella di progetto (Customer API.postman_collection.json, Device API.postman_collection.json)
effettuare chiamate http e testare tutte le api.

Sarà possibile creare/aggiornare/eliminare/recuperare i customer e i device.

Le api risponderanno:

200 in caso di Success.
404 nel caso in cui il customer/device non venga trovato.
500 in caso di errore generico del server.
204 con un messaggio nell'header della response http in caso di errore customizzato.

Note
E' stato utilizzato un database embedded h2 e predisposti dei dati fittizi utilizzati solo per i test unitari/integration.
Al primo avvio dall'applicazione il database sarà sempre vuoto, per cui aggiungere customer e/o device (tramite le apposite api) per popolare il db.
