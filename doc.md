author: Jean ROUILLIER
summary: How to dev a REST API stack
id: #TODO
categories: codelab,markdown,java,REST,API
environments: Web
status: Draft

# Create a REST API application

## CodeLab Overview
Duration: 0:02:00

During this codelab, you will learn how to develop an entire applicative stack using Spring.
This stack will contains each classic applicative layers: 
1. Controller - with everything relative to REST API
1. Service - To do our business
1. DAO - entities stuff and data persistance

But more than just the basic code, you will have some tips and best practices about what to avoid and important things. 


**Resources:** 
* Code source repository: [Link to sources](https://github.com/JeanRouillier/formation)
* You favorite IDE
* Java > 8 
* Your attention

## Short recap about REST API
Duration: 0:04:00

ReprEsentational State Transfer defined in 2000 by Roy Fielding.

Compete agains SOAP.
It’s a specification, not an implementation.

Distinguish message Metadata (headers) from Content (body).
It also provides specifications about general behaviour whit http status code.

#### Design an endpoint
This part may be the hardest in your developer's life. No joking.

And endpoint is composed by:
1. A HTTP Verb
1. A path describing resources we want to manipulate

##### HTTP Verbs
REST Comes with various HTTP verbs, they indicate the kind of action you want to do on a resource.

Verbs are equivalent of database CRUD. 

The most known are 
* GET       - Simple access
* POST      - Create 
* PATCH     - Update
* DELETE    - Delete
* OPTION    - Try access
* PUT       - Idempotent update

##### Path
As mentioned before a path is the way to access a resource.

When you read a path, it's like a funnel. The more your read, the specified the resources are.

```
    GET     /api/v1/tweets
    GET     /api/v1/tweets/authors/{author}
    DELETE  /api/v1/tweets/{tweetId}
```

Reading a path should be easy and fluent, even for a non tech person.

Only few rules must be followed when designing a REST API:
1. Always use nouns in plural
1. Respect the funneling
1. Do not use verbs


```
OK      GET /api/v1/work_sites
KO      GET /api/v1/getWorkSites

OK      PATCH /api/v1/work_sites/{ws_id}/addresses
KO      PATCH /api/v1/getWorkSite/{ws_id}/addAddress

```  

#### Status code

You will find the complete updated REST rfc [Here](https://tools.ietf.org/html/rfc2616).

A status code is a global number associated to a common behaviour.
Codes are split between categories:
* 1XX - Informational
* 2XX - Successfull
* 3XX - Redirection
* 4XX - Client Error
* 5XX - Server Error

You may already faces some of these codes
```bash
    200 - OK
    204 - No Content
    400 - Bad Request
    404 - Not Found
    418 - ?
    500 Internal server error
```

#### What's headers ?
Headers are metadata which are associated to each REST API calls.
They provides necessary information like status code, the body's format, CORS, custom data, pagination, date ...

Some headers are standard like CORS's.
But you are allowed to add your custom headers. They used to be prefixed by ```x-MY-HEADER```.

Example: 
```x-correaltion-id: "f5607d38-8fc1-43ef-b44e-34967083c80c```

#### And now Body !
The body represents the data. More precisely, a body represents a resource the client tries to access.
The format is specified is headers. 

Most of the times data is represented in JSON. But it can be binaries, XML ...

Here is some body example:
```json
    [
      {
        "id": 55,
        "label": "Random store",
        "services": [
          {
            "support_date": "2019-04-01T00:00:00Z",
            "activated": true,
            "id": 49407393,
            "label": "FORFAIT RANDOM"
          }
        ]
      },
      {
        "id": 49,
        "label": "Random store",
        "services": [
          {
            "support_date": "2019-04-01T00:00:00Z",
            "activated": true,
            "id": 491,
            "label": "FORFAIT RANDOM"
          }
        ]
      }
    ]
```
Another example
```json
[
  {
    "date": "2018-09-24T13:00:15Z",
    "type": "SMS_SENT",
    "metadata": {
      "another_random_ey": "second comment",
      "random_key": "first comment"
    }
  }
]
```

By standard dates are represented in [ISO 8601 format](https://en.wikipedia.org/wiki/ISO_8601).

Defining if your application returns camelCase or snake_case even kebab-case is up to you.


## Let's go implementing it !
Duration: 0:05:00

This was a short reminder about what is a REST API. You mey be ready to implements your first API.

But as this codelabs intend to help you developing the entire applicative stack, we will begin by the very beginning.


#### Our objective
``` 
GET localhost:8080/demo/api/v1/timelines
```
This endpoint is the one will develop during this codelabs.

It should return :

```json
[
  {
    "date": "2019-01-05T11:34:24+01:00",
    "correlation_id": "a5607d38-8fc1-43ef-b44e-34967083c80a",
    "metadata": {
      "start_date": "2019-07-02 14:00:00+02",
      "stop_date": "2019-07-02 14:00:00+02"
    }
  }
]
```

##### What we know ?

Analyzing previous requirements informs us a lot of things !
* We will manipulate `timelines`
* The API is a GET
* If the database contains multiple timelines, all of them will be returned


## Let's dev 
As we know what we should dev let's do it.

### Persistence Layer
In this layer we will implement a database with a scheme.
Here are steps to follow:
1. Define table scheme
1. Use Spring Data JPA
1. Write entities
1. Use FlywayDB
1. Test it !


We will use a simple database schema using 2 tables:
* Timeline
* TimelineMetadata

In the first table we will store our data. In our simple example we will follow the API json structure.

With Spring Data, the scheme and data can be provisioning in the ```resource``` folder.

==> schema.sql

==> data.sql

Do not forget tests !

####Flyway
Flyway is a schema migration tool. With this tool, the version of your application is synced with the version of your database schema.
With this tool, every one is able to have a database up to date.

Have a look at the documentation [Flyway web site](https://flywaydb.org/)

#### Docker
This lab don't intend to present you docker but you will find a docker-compose & a dockerfile in this project.

Using this tool on this project will provide you with a simple command to have a database.

To generate an empty databse, this project choose to use a Postgres 9.6.

Just run this command: 

```docker-compose db up```

It will start an empty database on port 5432.

### Service layer
Nothing special here, we only describe Spring Service.

Do not forget tests !
### Controller layer
Let's write our endpoints !

For the documentation, an implementation of Open API specification is SpringFox. Unfortunately Sprongfox only implements OpenAPI 2.

We can use Springdoc to genrate an OpenAPI 3 compliant documentation. 

Do not forget tests !

When you run your application go to 
```localhost:8080/demo/swagger-ui.html```
