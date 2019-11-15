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



#### Fill-in the header metadata
Copy and paste the headers below into your markdown file and change the values appropriately. 
Guidelines are available below the sample headers. 

``` bash
author: Author Name
summary: Summary of your codelab that is human readable
id: unique-codelab-identifier
categories: codelab,markdown
environments: Web
status: Published
feedback link: A link where users can go to provide feedback (Maybe the git repo)
analytics account: Google Analytics ID
```

Metadata consists of key-value pairs of the form "key: value". Keys cannot
contain colons, and separate metadata fields must be separated by blank lines.
At present, values must all be on one line. All metadata must come before the
title. Any arbitrary keys and values may be used; however, only the following
will be understood by the renderer:

* Summary: A human-readable summary of the codelab. Defaults to blank.
* Id: An identifier composed of lowercase letters ideally describing the
  content of the codelab. This field should be unique among
  codelabs.
* Categories: A comma-separated list of the topics the codelab covers.
* Environments: A list of environments the codelab should be discoverable in.
  Codelabs marked "Web" will be visible at the codelabs index. Codelabs marked
  "Kiosk" will only be available at codelabs kiosks, which have special
  equipment attached.
* Status: The publication status of the codelab. Valid values are:
  - Draft: Codelab is not finished.
  - Published: Codelab is finished and visible.
  - Deprecated: Codelab is considered stale and should not be widely advertised.
  - Hidden: Codelab is not shown in index.
* Feedback Link: A link to send users to if they wish to leave feedback on the
  codelab.
* Analytics Account: A Google Analytics ID to include with all codelab pages.

#### Add the Title
Next add your title using a single '#' character
```
# Title of codelab
```

#### Add Sections and Durations
Then for each section use Header 2 or '##' and specify an optional duration beneath for time remaining calculations
Optional section times will be used to automatically total and remaining tutorial times
In markdown I've found that the time is formatted hh:mm:ss

Example
``` bash
## Section 1
Duration: 0:10:00

## Section 2
Duration: 0:05:00
```

#### Add Section Content
Now that we have 2 sections to our titled codelab let's go ahead and add some content to each section. 
Make up your own or copy and paste the example below: 

Copy into section 1 (Below Duration and above Section 2):
```
### Info Boxes
Plain Text followed by green and yellow info boxes 

Negative
: This will appear in a yellow info box.

Positive
: This will appear in a green info box.

You created info boxes!

### Bullets
Plain Text followed by bullets
* Hello
* CodeLab
* World

You created bullets!

### Numbered List
1. List
1. Using
1. Numbers

You created a numbered list!

```

Copy into section 2 (Below Duration): 
```
### Add a Link
Adding a link!
[Example of a Link](https://www.google.com)

### Add an Image
Adding an image!
![image_caption](https://googlecloud.tips/img/031/codelabs.png)

### Embed an iframe
![https://codepen.io/tzoght/embed/yRNZaP](https://en.wikipedia.org/wiki/File:Example.jpg "Try Me Publisher")
```

More Markdown Parser examples can be found [here](https://github.com/googlecodelabs/tools/tree/master/claat/parser/md).

## Export and Serve
Duration: 0:02:00

Now that you have an initial codelab defined in your markdown file let's go ahead and generate the static site content. 
We can export and serve the content locally using the `claat` command that we installed earlier. 

``` bash
$ claat export codelab.md
$ claat serve
```

* Your browser should have opened (if it doesn't then try going to localhost:9090 in your browser). 
* Choose the directory that matches your "id" that you put in the headers. 
* Viola! You should have your first codelab!

## Host Your CodeLab
Duration: 0:01:00

When you ran the `claat export` command you created the static web content needed to host your codelab. 
It placed static web content in a directory specified by your unique "id" and you can view it locally by opening the index.html page. 

Negative
: Note that when you view it locally by opening index.html some of the graphics may not show up (such as access_time, Next, Back), but they work once online. 


Now that you have the static content you can host it however you want.
One option is pushing it to github and serving it up from Netlify.  

If you'd like to create your own landing page for codelabs, [like this one](https://codelabs.developers.google.com), there is a tool to do that as well! 
Check it out here: [CodeLabs Site](https://github.com/googlecodelabs/tools/blob/master/site/README.md)
