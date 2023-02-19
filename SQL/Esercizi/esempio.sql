--file di esempio sql
--PRIMO ESERCIZIO
USE airport;
SELECT airports.name AS Aeroporto 
FROM airports
WHERE airports.code NOT IN (SELECT arr_code FROM flights) AND airports.code IN (SELECT dep_code FROM flights) 


--SECONDO ESERCIZIO GIUSTO
USE airport;
SELECT pilots.first_name AS Nome, pilots.last_name AS Cognome
FROM pilots
WHERE pilots.h_exp > (SELECT AVG(h_exp)
					  FROM pilots);
                      
SELECT pilots.first_name AS Nome, pilots.last_name AS Cognome
FROM pilots
WHERE pilots.h_exp < (SELECT AVG(h_exp)
					  FROM pilots)
--SECONDO ESERCIZIO SBAGLIATO
USE airport;
SELECT pilots.first_name AS Nome, pilots.last_name AS Cognome
FROM pilots
GROUP BY pilots.h_exp
HAVING pilots.h_exp > (SELECT AVG(h_exp)     
					  FROM pilots);
/* a parer mio questa è sbagliata, in questo caso dà comunque i risultati voluti, ma nel caso in cui 2 piloti avessero avuto le stesse ore di esperienza, ne avrebbe mostrato solo uno 
(ho fatto una prova aggiungendo un pilota con ore di esperienza sopra la media e le stesse del pilota "Erminia Martino" e fra i record ha mostrato solo quest'ultimo)
questo perchè per usare having bisogna aver utlizzato precedentemente il group by e quest'ultimo prende il record distinto nel caso ce ne siano 2 uguali*/

--TERZO ESERCIZIO
USE airport;
SELECT airlines.name AS Codice_compagnia_aerea
FROM airlines
LEFT JOIN flights ON airlines.id = flights.airline_id
GROUP BY airlines.id 
HAVING COUNT( flights.airline_id) > 2

SELECT airlines.name AS Codice_compagnia_aerea
FROM airlines
LEFT JOIN flights ON airlines.id = flights.airline_id
GROUP BY airlines.id 
HAVING COUNT( flights.airline_id) < 2
