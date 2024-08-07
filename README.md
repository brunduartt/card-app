# card-app

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## CardApp
The app starts on the port 8080 and has the /api/ prefix.

### Create Card Payment
POST _/api/card-payment/_

Body:
```json
{
  "installments": 1,
  "card": {
    "cardNumber": "5324228786540745",
    "cardHolderName": "Teste",
    "expirationMonth": 8,
    "expirationYear": 2024
  },
  "value": 1
}
```
Return:
```json
{
  "id": "02cad906-e412-4d7c-a3e5-df8649a3502a",
  "value": 1.00,
  "installments": 1,
  "card": {
    "lastFourDigits": "0745",
    "cardHolderName": "Teste",
    "expirationMonth": 8,
    "expirationYear": 2024
  }
}
```
### Get Card Payment
GET _/api/card-payment/02cad906-e412-4d7c-a3e5-df8649a3502a_

Response:
```json
{
  "id": "02cad906-e412-4d7c-a3e5-df8649a3502a",
  "value": 1.00,
  "installments": 1,
  "card": {
    "lastFourDigits": "0745",
    "cardHolderName": "Teste",
    "expirationMonth": 8,
    "expirationYear": 2024
  }
}
```

## Prometheus
Prometheus can be accessed on the port 9090

### Custom metric implementation
#### errors.internal-error-exception
Increments each time the system returns a not handled exception.
Defined in ExceptionMapper class.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev -pl infra
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Running the App
### Package the application

```shell script
./mvnw package
```
### Docker
Build the docker images by running:
```shell scriptd
./docker-compose build
```
Then, run the docker compose:
```shell scriptd
./docker-compose up -d
```

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/card-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
