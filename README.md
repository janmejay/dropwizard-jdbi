# Dropwizard JDBI

A codebase for an org-internal training around. It does not do anything useful.

## Setup MySql

Creates an MySql DB populated with the migration scripts in src/main/resources/db/migration

```
gradle flywayMigrate -i
```

## Run Dropwizard service

To compile:

```
gradle build
```

To run:

```
gradle run
```


