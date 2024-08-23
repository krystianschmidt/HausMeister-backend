### 1. **Main-Klasse (Starter-Klasse)**

#### `AuthAppApplication.java`

**Aufgabe:**  
Dies ist die Hauptklasse der Spring Boot-Anwendung und dient als Einstiegspunkt der Anwendung. Sie wird verwendet, um die gesamte Anwendung zu starten. Wenn diese Klasse ausgeführt wird, startet sie den eingebetteten Webserver (wie Tomcat oder Jetty) und führt die Anwendung als eigenständige Anwendung aus.

- **Initialisiert das Spring Framework.**
- **Startet die Anwendung.**

### 2. **Model-Schicht (Datenmodell)**

#### `User.java` und `RegisterUser.java`

**Aufgabe:**  
Diese Schicht enthält die Datenklassen (Modelle oder Entitäten), die die Datenstruktur definieren, die in der Datenbank gespeichert werden soll. Sie repräsentieren das Geschäftsobjekt (z. B. einen Benutzer) und enthalten Attribute (wie `username`, `password`, `name`), die die Daten beschreiben.

- **`User.java`:** Repräsentiert die Datenbankentität für einen Benutzer. Diese Klasse enthält die Felder, die in der Datenbank als Tabelle gespeichert werden (z. B. `username`, `name`, `password`). Es verwendet JPA-Annotationen wie `@Entity`, um zu definieren, dass es sich um ein Datenbankmodell handelt.

- **`RegisterUser.java`:** Dieses Modell enthält Felder, die für die Registrierung eines neuen Benutzers verwendet werden. Es wird für den Transfer von Daten zwischen Frontend und Backend verwendet, ohne direkt die Datenbank zu betreffen.

**Zusammengefasst:**
- **Speichern und Verwalten von Daten.**
- **Repräsentieren von Datenbankobjekten.**

### 3. **Repository-Schicht (Datenzugriffsschicht)**

#### `UserRepository.java`

**Aufgabe:**  
Die Repository-Schicht ist für die **Datenbankinteraktionen** verantwortlich. Sie enthält die Schnittstellen, um CRUD-Operationen (Create, Read, Update, Delete) auf der Datenbank durchzuführen. In Spring Boot kann man über die **JPA-Repository**-Schnittstellen automatisch Datenbankoperationen durchführen, ohne sie explizit zu implementieren.

- **`UserRepository.java`:** Diese Schnittstelle erweitert `JpaRepository`, was bedeutet, dass sie CRUD-Operationen auf der `User`-Tabelle (z. B. `save`, `findById`, `findByUsername`) durchführen kann. Es ist eine zentrale Klasse für den Zugriff auf die Datenbank.

**Zusammengefasst:**
- **Verwaltet den direkten Zugriff auf die Datenbank.**
- **Ermöglicht die Interaktion mit den Datenmodellen (z. B. `User`).**

### 4. **Service-Schicht (Geschäftslogik-Schicht)**

#### `AuthService.java` und `UserService.java`

**Aufgabe:**  
Die Service-Schicht enthält die **Geschäftslogik** der Anwendung. Sie steht zwischen den Controllern (die HTTP-Anfragen verarbeiten) und den Repositories (die Datenbankoperationen durchführen). Diese Schicht implementiert die eigentlichen Geschäftsprozesse und kümmert sich um komplexere Logiken, wie z. B. das Verschlüsseln von Passwörtern oder das Verwalten von Transaktionen.

- **`AuthService.java`:** Dieser Service enthält Logik, die sich mit der Benutzerregistrierung, dem Abrufen von Benutzern nach Benutzernamen und der Passwortsicherung befasst. Hier wird auch das Passwort mit einem **BCryptPasswordEncoder** verschlüsselt.

- **`UserService.java`:** Dieser Service bietet zusätzliche Logik, wie die Überprüfung, ob ein Benutzername bereits existiert. Er ruft Daten über das Repository ab und führt eventuell weitere logische Operationen aus, bevor Daten zurück an den Controller gehen.

**Zusammengefasst:**
- **Führt die Geschäftslogik aus.**
- **Koordiniert Datenbankoperationen durch das Repository.**
- **Implementiert Sicherheitsmaßnahmen wie Passwort-Hashing.**

### 5. **Controller-Schicht (Web-API-Schicht)**

#### `AuthController.java`

**Aufgabe:**  
Der Controller stellt die **API** für das Frontend zur Verfügung. Er verarbeitet HTTP-Anfragen (z. B. POST, GET) und ist der erste Kontaktpunkt für das Frontend. Der Controller empfängt Daten von den Services und sendet die Ergebnisse an das Frontend zurück.

- **`AuthController.java`:** Dieser Controller behandelt verschiedene HTTP-Anfragen, z. B. für die Registrierung (`/auth/register`) und das Prüfen der Verfügbarkeit eines Benutzernamens (`/auth/available`). Er stellt sicher, dass die Anfragen ordnungsgemäß verarbeitet werden, leitet sie an die entsprechende Service-Schicht weiter und gibt die Ergebnisse als HTTP-Response zurück.

**Zusammengefasst:**
- **Verarbeitet HTTP-Anfragen vom Frontend.**
- **Kommuniziert mit den Services, um Daten zu verarbeiten.**
- **Gibt Antworten (Responses) an das Frontend zurück.**

### 6. **Security-Schicht (Sicherheit und Authentifizierung)**

#### `SecurityConfig.java`

**Aufgabe:**  
Diese Schicht sorgt für die **Sicherheit** der Anwendung, indem sie Zugriffsbeschränkungen und Authentifizierungsmethoden definiert. Sie stellt sicher, dass nur berechtigte Benutzer auf bestimmte Endpunkte zugreifen können. Typische Sicherheitsmaßnahmen umfassen Passwortverschlüsselung, Token-basierte Authentifizierung und rollenbasierte Zugriffskontrolle.

- **`SecurityConfig.java`:** Hier wird die Sicherheitskonfiguration für die Anwendung definiert. Beispielsweise wird die CSRF-Prüfung deaktiviert und alle Endpunkte für den Zugriff ohne Authentifizierung freigegeben (dies kann später angepasst werden). Zudem wird ein `BCryptPasswordEncoder` konfiguriert, der für das Hashing von Passwörtern verwendet wird.

**Zusammengefasst:**
- **Sichert den Zugang zur Anwendung (Authentifizierung und Autorisierung).**
- **Konfiguriert Verschlüsselung und Sicherheitsmaßnahmen.**

### 7. **Konfiguration (application.properties)**

#### `application.properties`

**Aufgabe:**  
Die Konfigurationsdatei `application.properties` enthält **Einstellungen**, die für den Betrieb der Anwendung notwendig sind, wie z. B. Datenbankverbindungen, Ports, Sicherheitsparameter oder andere Umgebungsvariablen.

- In diesem Fall enthält sie die Konfiguration für die H2-Datenbank (In-Memory-Datenbank), was nützlich für die Entwicklung und das Testen ist.

**Zusammengefasst:**
- **Konfiguriert die Umgebung der Anwendung.**
- **Definiert Datenbankverbindungen, Servereinstellungen und mehr.**

---

### Zusammenfassung der gesamten Projektstruktur:

1. **Main-Klasse (Starter-Klasse):** Startet die Anwendung und lädt die Konfiguration.
2. **Model-Schicht:** Enthält die Datenstrukturen, die in der Datenbank gespeichert und zwischen Frontend und Backend ausgetauscht werden.
3. **Repository-Schicht:** Verwaltet den Datenbankzugriff (CRUD-Operationen).
4. **Service-Schicht:** Enthält die Geschäftslogik und Sicherheitsoperationen wie Passwortverschlüsselung.
5. **Controller-Schicht:** Stellt die Web-API bereit und verarbeitet HTTP-Anfragen vom Frontend.
6. **Security-Schicht:** Verwaltet die Authentifizierung und Autorisierung der Benutzer.
7. **Konfiguration:** Stellt die Einstellungen für die Anwendung und die Umgebung bereit.

Durch diese klare Trennung der Schichten wird das Projekt modular und leicht wartbar, da jede Schicht eine spezifische Aufgabe übernimmt und keine ungewollten Abhängigkeiten zwischen den Schichten bestehen.

---

Ja, deine Projektstruktur mit **Abstractioncode**, **Adapters**, **Application**, **Domain** und **Plugins** ist vergleichbar mit dem zuvor beschriebenen Ansatz, jedoch nutzt sie eine andere **Architektur**. Diese Begriffe stammen häufig aus der **Hexagonalen Architektur** (auch **Ports-and-Adapters-Architektur** genannt), die etwas anders aufgebaut ist als eine klassische **Schichtenarchitektur**.

### Vergleich der Strukturen

Um zu erklären, was jede Komponente in deinem Projekt tut und wie sie mit der traditionellen Schichtenarchitektur verglichen werden kann, schauen wir uns jede Schicht oder Komponente an:

### 1. **Abstractioncode** (Abstraktionsebene)

**Aufgabe:**  
Diese Schicht repräsentiert generelle Schnittstellen, abstrakte Klassen oder allgemeine Konzepte, die von anderen Schichten verwendet werden können. In der Hexagonalen Architektur dient diese Ebene dazu, die Implementierung von ihrer Schnittstelle zu trennen. Oft gibt es hier definierte **Ports** (Schnittstellen), die später von **Adapters** implementiert werden.

- **Vergleichbar mit:** Teile der **Service-Schicht** in der traditionellen Architektur, aber auch mit allgemeinen **Interfaces**, die von unterschiedlichen Schichten verwendet werden.

- **Beispiel:** Ein Interface, das beschreibt, wie Daten persistiert werden sollen, unabhängig von der tatsächlichen Datenbanktechnologie.

### 2. **Adapters** (Adapter-Schicht)

**Aufgabe:**  
In der Hexagonalen Architektur werden Adapter verwendet, um die eigentliche **Geschäftslogik von der Infrastruktur zu trennen**. Adapter sind Implementierungen von **Ports**, die die Interaktion mit externen Systemen, Datenbanken oder APIs handhaben. Sie konvertieren Datenformate und kommunizieren mit externen Systemen oder dem Nutzer über Eingabekanäle (z. B. REST API oder Datenbank).

- **Vergleichbar mit:**
    - **Controller-Schicht:** Adapters in der Hexagonalen Architektur sind oft die Schnittstelle zur Außenwelt (wie z. B. Web-APIs oder UI-Interaktionen).
    - **Repository-Schicht:** Wenn Adapter auf Datenbanken zugreifen, können sie der Datenzugriffsschicht entsprechen.

- **Beispiel:** Ein REST-Controller, der die Anfragen aus dem Web entgegen nimmt, oder ein Datenbankadapter, der die Daten speichert und lädt.

### 3. **Application** (Anwendungsebene)

**Aufgabe:**  
Diese Schicht enthält die **Anwendungslogik**, also das Herzstück der Geschäftsprozesse. Es orchestriert die Interaktionen zwischen den Adaptern und der **Domain-Schicht**. Es enthält Anwendungsfälle, aber keine direkte Geschäftslogik. Die Geschäftslogik wird in der **Domain-Schicht** definiert, und die **Application-Schicht** stellt sicher, dass alles richtig funktioniert und interagiert.

- **Vergleichbar mit:**
    - **Service-Schicht:** Die **Application-Schicht** orchestriert die verschiedenen Geschäftsprozesse, genauso wie es die Service-Schicht in der Schichtenarchitektur tut.

- **Beispiel:** Ein Anwendungsfall wie „Bestellung aufgeben“ oder „Benutzer registrieren“, bei dem die Services zusammenarbeiten und Adapter aufgerufen werden.

### 4. **Domain** (Domänenschicht)

**Aufgabe:**  
Die Domain-Schicht ist das zentrale Element der Hexagonalen Architektur. Hier liegt die **Geschäftslogik** und die **Kernmodelle** der Anwendung. Die Domain enthält die Geschäftsregeln, Datenstrukturen und Berechnungen, die unabhängig von der technischen Infrastruktur sind. Es wird durch Interfaces (Ports) mit der Außenwelt verbunden.

- **Vergleichbar mit:**
    - **Model-Schicht:** In der Schichtenarchitektur würden wir hier die **Datenmodelle** und Geschäftslogik haben, die in der Service-Schicht und im Domainmodell enthalten sind.

- **Beispiel:** Ein Domain-Modell für einen „Benutzer“, das definiert, wie Benutzer gespeichert, validiert und verarbeitet werden. Es könnte Methoden enthalten wie „Passwort ändern“ oder „Benutzerprofil aktualisieren“.

### 5. **Plugins** (Infrastruktur/Plugins)

**Aufgabe:**  
Diese Komponente ist spezifisch für die Infrastruktur und enthält **technologische Implementierungen**, wie z. B. Datenbank-Plugins, Messaging-Systeme, Logging, etc. Sie sind meist austauschbare Bestandteile, die durch Ports definiert werden und als **Adapter** fungieren. Plugins ermöglichen es, Technologien leicht auszutauschen, ohne dass die Kernlogik der Anwendung angepasst werden muss.

- **Vergleichbar mit:**
    - **Datenbankschicht (Repository):** In der Schichtenarchitektur wären dies die Komponenten, die sich mit Datenbanken, externen APIs oder Dateisystemen verbinden.
    - **Infrastruktur-Schicht:** Im allgemeinen Architekturansatz gibt es oft eine Infrastrukturschicht, die externe Abhängigkeiten behandelt (z. B. Persistenz oder Messaging).

- **Beispiel:** Ein Plugin könnte ein MySQL-Datenbankadapter sein, der die Datenbankinteraktion implementiert oder ein externer API-Client für einen Zahlungsdienst.

---

### Hexagonale Architektur vs. Schichtenarchitektur

| **Hexagonale Architektur** | **Schichtenarchitektur** |
|-----------------------------|---------------------------|
| **Abstractioncode:** Definiert allgemeine Schnittstellen, die von Adaptern implementiert werden. | Teils in der **Service-Schicht** und den **Interfaces** enthalten, die die Geschäftslogik trennen. |
| **Adapters:** Implementieren die Schnittstellen (Ports) und verbinden externe Systeme mit der Domäne. | Entspricht teils der **Controller-Schicht** und **Repository-Schicht**. Verbindungen zu Datenbanken und Web-APIs. |
| **Application:** Enthält Anwendungslogik, orchestriert die Geschäftsprozesse und Interaktionen. | Vergleichbar mit der **Service-Schicht** (die die Geschäftsprozesse abwickelt). |
| **Domain:** Enthält die Geschäftslogik, Modelle und Regeln, unabhängig von der Infrastruktur. | **Model-Schicht** und die Geschäftslogik in der **Service-Schicht** der Schichtenarchitektur. |
| **Plugins:** Infrastruktur-spezifische Implementierungen, die als Adapter fungieren, z. B. Datenbanken, externe APIs. | Vergleichbar mit der **Repository-Schicht** und Infrastrukturaspekten wie Datenbankzugriff. |

### Wichtige Unterschiede:

- **Hexagonale Architektur** legt viel Wert darauf, die Geschäftslogik **vollständig unabhängig von technischen Details** zu halten. Die Kommunikation zur Außenwelt läuft über **Ports (Schnittstellen)** und **Adapters**, was die Flexibilität erhöht, verschiedene Technologien oder Frontends anzuschließen.

- **Schichtenarchitektur** trennt die Anwendung in **vertikale Schichten** (z. B. Controller, Service, Repository), in denen jede Schicht eine bestimmte Verantwortung hat, aber es gibt weniger klare Trennung zwischen Geschäftslogik und Infrastruktur.

### Fazit:

In deinem Projekt spielt jede dieser Komponenten eine bestimmte Rolle in der Anwendung:

- **Abstractioncode**: Definiert Schnittstellen, um Flexibilität bei der Implementierung zu ermöglichen.
- **Adapters**: Implementieren die externen Verbindungen zu Datenbanken, APIs oder Benutzerschnittstellen.
- **Application**: Koordiniert die Geschäftsprozesse und orchestriert die Domänen- und Adapterlogik.
- **Domain**: Definiert die Kerngeschäftslogik und -modelle.
- **Plugins**: Stellt konkrete Implementierungen für Infrastruktur-Anforderungen bereit, z. B. Datenbanken, APIs, etc.

Dieses Muster kann je nach Anwendungsfall und Komplexität der Anwendung effektiver sein, da es mehr Flexibilität und Austauschbarkeit bietet.
