package it.omicron.constants;

public class SwaggerConstant {

	// General
	public static final String BASE_PATH = "/api/v1";
	public static final String GENERIC_UNAUTHORIZED_EXAMPLE = "Unathorized";
	public static final String GENERIC_NOT_FOUND_EXAMPLE = "Not Found";
	public static final String GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE = "Internal Server Error";
	public static final String GENERIC_OK_EXAMPLE = "Ok";
	public static final String GENERIC_BAD_REQUEST_EXAMPLE = "Bad Request";
	public static final String GENERIC_CONFLICT_EXAMPLE = "Conflict";

	// Login
	public static final String LOGIN_PATH = "/login";

	// Login request
	public static final String LOGIN_REQUEST_SUMMARY = "Servizio login per un Cliente";
	public static final String LOGIN_REQUEST_DESCRIPTION = "Servizio per effettuare la login";
	public static final String LOGIN_REQUEST_OK_EXAMPLE = "{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNzk2NTUwOCwiZXhwIjoxNjE4MDUxOTA4LCJhYmlsaXRhemlvbmkiOlsiVVRfViIsIlVUX0UiLCJBTV9WIiwiQU1fRSIsIk1LX1YiLCJNS19FIiwiUVJfViIsIlJNX1YiLCJTVF9WIiwiUkxfViIsIlJMX0UiXX0.U5Xvievy04YNquQ1oFU7GWiISgrx_Ml6P4Hm8WvPqpIxQQ86JOskLn8KKI-haQuNyDdg3vYeExE81udxGRpObA\"}";

	// Ping
	public static final String PING_PATH = "/ping";
	public static final String USER_PING_PATH = "/userPing";
	public static final String ADMIN_PING_PATH = "/adminPing";

	// Ping request
	public static final String PING_REQUEST_SUMMARY = "Servizio di PING senza protezione";
	public static final String PING_REQUEST_DESCRIPTION = "Servizio di PING senza protezione per testare le API";
	public static final String USER_PING_REQUEST_SUMMARY = "Servizio di PING per utente o admin";
	public static final String USER_PING_REQUEST_DESCRIPTION = "Servizio di PING che accetta solo utenti con ruolo utente o admin";
	public static final String ADMIN_PING_REQUEST_SUMMARY = "Servizio di PING per admin";
	public static final String ADMIN_PING_REQUEST_DESCRIPTION = "Servizio di PING che accetta solo utenti con ruolo admin";

	// Utente

	// Utente Generic
	public static final String UTENTE_PATH = "/utente";
	public static final String NEW_UTENTE_PATH = "/new-utente";

	// Utente findall
	public static final String UTENTE_PATH_FIND_ALL = "/utente/all";
	public static final String UTENTE_FIND_ALL_SUMMARY = "Servizio findAll Utente";
	public static final String UTENTE_FIND_ALL_DESCRIPTION = "Servizio per ritrovare tutti gli Utenti";
	public static final String UTENTE_FIND_ALL_OK_EXAMPLE = "{\"fromRecord\":0,\"toRecord\":10,\"totalRecord\":2,\"utenti\":[{\"nome\":\"admin\",\"cognome\":\"admin\",\"username\":\"admin\",\"indirizzo\":\"Lungo Dora Pietro Colletta 81, 10153 Torino TO\",\"ruoliUtenti\":{\"ruolo\":\"Amministratore\"},\"statoAttivo\":true},{\"nome\":\"user\",\"cognome\":\"user\",\"username\":\"user\",\"indirizzo\":\"Viale dell'Industria, 62, 35129 Padova PD\",\"ruoliUtenti\":{\"ruolo\":\"Utente\"},\"statoAttivo\":true}]}";

	// Utente find
	public static final String UTENTE_FIND_SUMMARY = "Servizio ricerca Utente";
	public static final String UTENTE_FIND_DESCRIPTION = "Servizio per ritrovare un utente tramite la sua username";
	public static final String UTENTE_FIND_OK_EXAMPLE = "{\"nome\":\"admin\",\"cognome\":\"admin\",\"username\":\"admin\",\"indirizzo\":\"Lungo Dora Pietro Colletta 81, 10153 Torino TO\",\"ruoliUtenti\":{\"ruolo\":\"Amministratore\"},\"statoAttivo\":true}";

	// Utente insert
	public static final String UTENTE_INSERT_SUMMARY = "Servizio insert Utente";
	public static final String UTENTE_INSERT_DESCRIPTION = "Servizio per creare un nuovo utente con ruolo Utente";

	public static final String ADMIN_INSERT_SUMMARY = "Servizio insert Admin";
	public static final String ADMIN_INSERT_DESCRIPTION = "Servizio per creare un nuovo utente con ruolo Admin";

	// Utente update
	public static final String UTENTE_UPDATE_SUMMARY = "Servizio update Utente";
	public static final String UTENTE_UPDATE_DESCRIPTION = "Servizio per aggiornare un utente, i campi modificabili sono solo: [nome, cognome, indirizzo]";

	// Utente remove
	public static final String UTENTE_REMOVE_SUMMARY = "Servizio remove Utente";
	public static final String UTENTE_REMOVE_DESCRIPTION = "Servizio per rimuovere un Utente";

	// Articolo generic
	public static final String ARTICOLO_PATH = "/articolo";

	// Articolo findall
	public static final String ARTICOLO_PATH_FIND_ALL = "/articolo/all";
	public static final String ARTICOLO_FIND_ALL_SUMMARY = "Servizio findAll Articolo";
	public static final String ARTICOLO_FIND_ALL_DESCRIPTION = "Servizio per ritrovare tutti gli Utenti";
	public static final String ARTICOLO_FIND_ALL_OK_EXAMPLE = "{\"nomeArticolo\":\"String\",\"prezzo\":\"String\",\"quantita\":\"String\"}";

	// Articolo find
	public static final String ARTICOLO_FIND_SUMMARY = "Servizio ricerca Articolo";
	public static final String ARTICOLO_FIND_DESCRIPTION = "Servizio per ritrovare un articolo tramite il nome";
	public static final String ARTICOLO_FIND_OK_EXAMPLE ="{\"nomeArticolo\":\"String\",\"prezzo\":\"String\",\"quantita\":\"String\"}";

	// Articolo insert
	public static final String ARTICOLO_INSERT_SUMMARY = "Servizio insert Articolo";
	public static final String ARTICOLO_INSERT_DESCRIPTION = "Servizio per creare un nuovo articolo con ruolo Admin";

	// Articolo update
	public static final String ARTICOLO_UPDATE_SUMMARY = "Servizio update articolo";
	public static final String ARTICOLO_UPDATE_DESCRIPTION = "Servizio per aggiornare un articolo, i campi modificabili sono solo: [costo]";

	// Articolo remove
	public static final String ARTICOLO_REMOVE_SUMMARY = "Servizio remove Articolo";
	public static final String ARTICOLO_REMOVE_DESCRIPTION = "Servizio per rimuovere un Articolo";

	// Ordine generic
	public static final String ORDINE_PATH = "/ordine";

	// Ordine insert
	public static final String ORDINE_INSERT_SUMMARY = "Servizio insert Ordine";
	public static final String ORDINE_INSERT_DESCRIPTION ="Servizio per inserire un prodotto in un ordine";

	// Ordine delete
	public static final String ORDINE_REMOVE_SUMMARY = "Servizio remove Ordine";
	public static final String ORDINE_REMOVE_DESCRIPTION = "Servizio per rimuovere un ordine";

	// Ordine update
	public static final String ORDINE_UPDATE_SUMMARY = "Servizio update Ordine";
	public static final String ORDINE_UPDATE_DESCRIPTION = "Servizio per aggiornare un ordine";

	// Ordine find
	public static final String ORDINE_FIND_SUMMARY = "Servizio ricerca Ordine";
	public static final String ORDINE_FIND_DESCRIPTION = "Servizio per ritrovare un ordine tramite il codiceOrdine";

	// Ordine findAll
	public static final String ORDINE_PATH_FIND_ALL = "/ordine/all";
	public static final String ORDINE_FIND_ALL_SUMMARY = "Servizio findall ordine";


	// Magazzino generic
	public static final String MAGAZZINO_PATH = "/magazzino";

	// Magazzino insertGiacenza
	public static final String MAGAZZINO_INSERT_SUMMARY = "Servizio insert giacenza";
	public static final String MAGAZZINO_INSERT_DESCRIPTION = "Servizio per inserire una giacenza";

}
