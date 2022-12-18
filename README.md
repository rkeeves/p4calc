## p4calc

A basic calculator application for Patrician 4.

## If you just want a simple spreadsheet for Pat 4

Please refer to [this](https://github.com/rkeeves/p4calc/blob/main/SPREADSHEET.md).

Direct links to OpenOffice spreadsheets within this repo:

[This one is friendlier](https://github.com/rkeeves/p4calc/blob/main/spreadsheet/p4_streamlined.ods).

[This one is scarier](https://github.com/rkeeves/p4calc/blob/main/spreadsheet/p4calc.ods).

## Build

The project uses only maven.
Use the commands below to jar creation and running it on a 15+ Java VM.

```
mvn package
java -jar ./target/p4calc-1.0-SNAPSHOT.jar
```

## Site

The project uses only maven.
Site includes javadoc, checkstyle and jacoco.

```
mvn site
```