# Telepass
Telepass Challenge

Clonare il repository in locale tramite il seguente indirizzo github:
https://github.com/FrancescodeFilippo/Telepass

Importare il progetto nell'ide di sviluppo (nel mio caso Intellij IDEA)
Per la build del progetto lanciare i comandi maven clean install.

Per lanciare l'applicazione runnare la classe ChallengeApplication.

Tramite le collection PostMan allegate è possibile chiamare e testare tutte le api del progetto.

Sarà possibile creare/aggiornare/eliminare/recuperare i customer e i device.

Le api risponderanno:
- 200 in caso di Success
- 404 nel caso in cui il customer/device non venga trovato
- 500 in caso di errore generico del server
- 204 con un messaggio nell'header della response http in caso di messaggio di errore customizzato
