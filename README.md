
# Corsi e lezioni online

Si deve realizzare un'applicazione backend che gestisca un sistema di vendita di corsi online. 

### Funzionalità principali:
- Gestione registrazione e login utenti e tutor;
- Creazione e gestione dei corsi da parte dei tutor;
- Gestione del materiale;
- Acquisto dei corsi da parte dei clienti; 
- Sistema di valutazione da parte dei tutor;
- Sistema di recensione dei corsi;

### Entità: 
Per ogni entità gestire i campi da noi forniti ed eventuali extra.
- Utenti (Tutor e Clienti): nome utente, email, password e ruolo;
- Corsi: nome, Tutor che lo ha aperto, prezzo, durata, categoria, descrizione;
- Recensioni: id_utente, id_corso, recensione;
- Votazioni: id_utente, id_corso, votazione;
- Materiale: power_point_lezioni, argomento_lezione, id_corso;

### Azioni:
- Utente si registra o fa il login;
- Utente cerca tra i corsi attivi in base a diversi criteri;
- Utente-cliente si iscrive ad un corso;
- Utente-tutor crea un corso;
- Utente-tutor fornisce il materiale;
- Utente-cliente recensisce il corso;
- Utente-tutor da una valutazione su Utente-cliente;
- Cliente può vedere: corsi disponibili, corsi a cui è iscritto, votazioni ricevute, lista tutor, corsi di ogni tutor, recensione corsi.
- Tutor può vedere: suoi corsi attivi, clienti iscritti ai suoi corsi, recensioni dei corsi, valutazioni che lui ha dato;
