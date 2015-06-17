# Cikmed - Progetto SIW 2015
===================================

ad opera di

- Andrea 
- Stefano

con l'utilizzo del framework JSF per il pattern MVC, e l'uso di Bootstrap per la parte grafica.
E' stata inoltre usata (molto parzialmente) una libreria di componenti per JSF, ovvero PrimeFaces, in modo
da implementare alcune funzionalità aggiuntive sulle pagine .xhtml.

-----------------------------------

-FILES IMPORTANTI-

[Requisiti](https://dl.dropboxusercontent.com/u/20172572/didattica/siw/siw-progetto-1.pptx)

[Descrizione casi d'uso](specifiche.txt)

[Diagramma delle classi (UML)](https://www.dropbox.com/s/86ab90wmng573bb/11276219_10206723248433464_1939320997_n.jpg?dl=0)

[Schema relazionale](https://www.dropbox.com/s/xva7zobqkc6r902/SCHEMA%20RELAZIONALE%20-%20Cikmed.docx?dl=0)

[Backup base di dati](https://www.dropbox.com/s/c57ix7lssb4y4gz/databaseCikmed.txt?dl=0)


*malfunzionamenti: 
- Validazione della password durante la registrazione di un cliente. Ho implementato due Validator, uno per la conferma della password e uno per verificare un determinato pattern, ma non sono riuscito a farli funzionare correttamente assieme;
- Nei dettagli del cliente non si riesce a risalire ai dettagli del suo indirizzo;

*errata corrige*: nel commit 22 si intendeva "utilizzo di EJB" invece che di JSF
