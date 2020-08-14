# Classic Models

Classic-Models-Anwendung zum Kapitel ? des Buches 
*JavaServer Faces - Ein Arbeitsbuch für die Praxis*, 3. Auflage (noch nicht erschienen)

Siehe auch <https://www.jsfpraxis.de>.

Achtung: * ich arbeite noch dran, die Beispiele sind alles andere als vollständig *

Das Beispiel verwendet die Daten des Eclipse-Projekts [BIRT](https://www.eclipse.org/birt/),
die man [herunterladen](http://www.eclipse.org/birt/documentation/sample-database.php) kann.

Die Anwendung ist nicht vollständig in dem Sinne, dass alle Use-Cases bzw. CRUD-Funktionen
realisiert sind.

## Getting Started
```
mvn clean package
```
Dann deployen Sie wie gewohnt auf Ihrem Java-EE-8-Application-Server


### Voraussetzungen

Java-EE-8-Application-Server!

Wir haben mit WildFly 19 unter Java 13 getestet. 

Die Verwendung mit Java 14 ist aktuell nicht möglich, da in Java 14 das Package ``java.security.acl``
entfernt wurde, dies aber von WildFly verwendet wird.
Siehe auch [Remove deprecated java.security.acl APIs](https://bugs.openjdk.java.net/browse/JDK-8217101).


## Intgrationstests

Wir verwenden [Arquillian](http://arquillian.org/) und einen gestarteten WildFly.
Außerdem verwenden wir die eingebaute DataSource, Hibernates import.sql und die
Standard ``persistence.xml``. Das bedeutet, dass alle Tabellen nach den Tests gedropt
werden, also auch die der eventuell deployten Anwendung.

```
mvn clean test failsafe:integration-test
```

## License

See the [LICENSE](LICENSE.txt) file for license rights and limitations (MIT).
