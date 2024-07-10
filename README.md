# Ticketing Application
Das Ziel der Applikation ist es ein Backend mit einem REST-Interface zu erstellen. Als Beispiel soll eine Benutzerverwaltung erstellt werden welche anschliessend für die Authentifizierung und Authorisierung verwendet werden kann.  
Aufbauend auf dieser Implementierung soll anschliessend ein Sicherheitsmechanismus auf der Basis von [JSON Web Token](https://jwt.io/) implementiert werden.

## Projekt aufsetzen
Das Projekt kann von [GitHub](https://github.com/maexeler/ticketing_app) heruntergeladen werden.

## Applikationsdokumentation
Die Applikationsdokumentation finden sie im Ordner [./doc](./doc/appDoc.md).

## Die Applikation staren
Laden sie das Projekt in einen Editor ihrer Wahl und lassen sie [UserApplication.java](./src/main/java/ch/zli/m223/UserApplication.java) laufen.

## Testdaten
Der Server erzeugt beim Start auf Wunsch hin automatisch Testdaten.  
Die Testdatenerzeugung finden sie in [ServerInitialisation.java](./src/main/java/ch/zli/m223/init/ServerInitialisation.java), die Steuerung ob Testdaten erzeugt weden oder nicht in [application.properties](./src/main/resources/application.properties).

## REST-API Test
Sie finden die passenden Postman-Scripts im './src/test/postman' Verzeichnis. 
- [Register](./src/test/postman/Register.postman_collection.json)
- [Login](./src/test/postman/Login.postman_collection.json)
- [Admin](./src/test/postman/Admin.postman_collection.json)
- [Member](./src/test/postman/Member.postman_collection.json)