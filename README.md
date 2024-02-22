# Foldouts

## Overview
Welcome to the initial prototype implementation of the Foldouts backend application. This project supports the Foldouts brand by enabling customers to add in-store products to their wish list via SMS. The current implementation focuses exclusively on the backend API, designed to seamlessly integrate with the Twilio messaging service. The application exposes an API endpoint, utilized as a webhook by Twilio, successfully receiving messages and adding products to customers' wish lists, all stored in a MongoDB database.

## Hosting
The application is currently hosted on Render, a reliable hosting service. The provided Dockerfile facilitates the building and serving of the application. The MongoDB database is hosted on Mongo Atlas.

## Secrets
For security reasons, essential secrets are stored in a non-publicly accessible `secrets.yaml` file. This file includes crucial credentials for communication with the MongoDB service. Without the presence of `secrets.yaml` in the project's root directory, the application cannot function. To set up your MongoDB instance, use the following template for the `secrets.yaml` file:
```yaml
secrets:
  mongodb:
    authentication-database:
    username: 
    password: 
    database:
    port: 
    host: 
    uri:
```

The necessity of certain fields may vary depending on your chosen MongoDB hosting configuration.

## Endpoints
- `/v1/sms/receive` : This currently serves as the sole exposed endpoint, responsible for receiving messages from the Twilio messaging service and responding with the appropriate `TwimlResponse`.

## Messaging Formats Via SMS
Messages via SMS currently support the following commands, most commands are dynamic checking if the customer is opted-in to the foldouts service and responsing with a relevant message based on opt-in status.
- `product-id:[Insert product id string here between brackets]` : As long as an incoming message has this text included in the body, the product id will be extracted and added to the customer's wish list.
- `YES` : Signal to opt-in to foldouts service.
- `STOP` : Signal to stop/deny opting in to foldouts service.
- `HELP` : Displays helpful information.
