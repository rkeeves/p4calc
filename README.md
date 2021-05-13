## p4calc

A basic calculator application for Patrician 4.

## If you just want a simple spreadsheet for Pat 4

Please refer to [this](https://github.com/rkeeves/p4calc/blob/main/SPREADSHEET.md).

## Build

The project uses only maven.
Use the commands below to jar creation and running it on a 15+ Java VM.

```
mvn package
java -jar /target/p4calc-1.0-SNAPSHOT.jar
```

## Site

The project uses only maven.
Site includes javadoc, checkstyle and jacoco.

```
mvn site
```