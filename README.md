# RepoWiz

[![Build Status](https://travis-ci.com/qbicsoftware/repowiz.svg?branch=development)](https://travis-ci.com/qbicsoftware/repowiz)[![Code Coverage]( https://codecov.io/gh/qbicsoftware/repowiz/branch/development/graph/badge.svg)](https://codecov.io/gh/qbicsoftware/repowiz)

RepoWiz, version 1.0.0-SNAPSHOT - RepoWiz helps you to find a suitable repository for your data and prepares your submission.

![wizard](./docs/logo/wizard_repo.png)

## Author
Created by Jennifer Bödker (jennifer.boedker@student.uni-tuebingen.de)
 
## Description

The **__Fair Data Principles__** demands the submission of data of funded research in order to make it publicly accessible. 
Since there are a lot of repositories for different data types or even multiple repositories for the same data types but with different requirements for the submission, 
in order to facilitate FAIR data RepoWiz supports the upload from any local management systems to different repositories.

## How to Install
In order to execute the code you need to clone the repository

```git clone https://github.com/qbicsoftware/RepoWiz```

and then package the jar with Maven

```mvn clean package```

The jar file will be located in the target folder of the **repowiz-application** module.

```repowiz-application/target/repowiz-application-VERSION-jar-with-dependencies.jar```

## Run

RepoWiz runs with different subcommands:


#### Guide
For inexperienced users RepoWiz offers a __guide__ that suggest a suitable repository for your project

```java -jar RepoWiz-1.0.0.jar guide -p QFSVI -conf credentials.properties```

#### Select
If you are already familiar with repositories and just want to upload your data to a repository use the __select__ command:

```java -jar RepoWiz-1.0.0.jar select -r geo -p QFSVI -conf credentials.properties```

#### List
If you just want to know which repositories are already supported use the __list__ command:

```java -jar RepoWiz-1.0.0.jar list```

### Config
For the subcommands **guide** and **select** a config file is required.
This is a json file that looks like this:
```
{
"server_url" : "openbis-serverurl",
"user": "username",
"password": "password"
}
``` 
The example config shows all required fields for the connection with the openBIS system.
For data from another local database management system the config may vary. 

