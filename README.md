# contabo-api
## Java 8 Wrapper for the Contabo REST-API using the Guardian Framework

This wrapper makes it easy to use the new [Contabo API](https://api.contabo.com) with Java 8 or higher.

### User-related methods are currently missing, as well as some other convenience methods.
#### All endpoints are implemented; it is possible to use all functionalities of the API. Just not with convenience methods and classes.

## Gradle and Maven copy-pasta

This library is hosted under the comroid repository
at [maven.comroid.org](https://maven.comroid.org/org/comroid)

Alternatively, it is possible to use the JitPack Pipeline to aquire all modules.

### Gradle

```groovy
repositories {
    maven { url 'https://maven.comroid.org' }
}

dependencies {
    implementation 'org.comroid:contabo-api:0.1.+'
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>comroid</id>
        <url>https://maven.comroid.org</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.comroid</groupId>
        <artifactId>contabo-api</artifactId>
        <version>0.1.0.1640455686</version> <!-- or higher -->
    </dependency>
</dependencies>
```