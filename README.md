agent-tooling
=============

The goal of this project is to produce a general toolkit library for the creation of multi-agent systems for the [SMAC research team](http://irit.fr/-SMAC-team-). It is born for the merging of several, orginally independant projects created by members of the team.

INSTALL
-------
This is a maven project. After cloning it you can install it by running

  `mvn install`

inside the project folder. You can then declare it as a dependency for your projects by adding:

````
<dependency>
  <groupId>fr.irit.smac</groupId>
  <artifactId>agent-tooling</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
````

to your pom.xml file.

### Using this project with eclipse

If you want to import on this project inside eclipse, you can simply run

`mvn eclipse:eclipse`

Maven will create the appropriate configuration files and you will be able to import the project in your workspace as usual (for more details, please refer to the [Maven Eclipse plugin documentation](https://maven.apache.org/plugins/maven-eclipse-plugin/eclipse-mojo.html)).

DESCRIPTION
-----------

The project is composed of the following source folders:
* main -- the actual library code
* test -- the tests defined on the library
* examples -- some examples to showcase the use of the different components

The library is currently composed of five parts:
* logging -- easy logging of the agents activity
* messaging -- message-passing between agents
* persistency -- an API for handling agent persistency
* plot -- plotting various values about the agents
* scheduling -- control of the agents execution

You should check the [examples](src/examples/java/fr/irit/smac/libs/tooling/examples) folder of the differents utilities, as well as the package-info files inside each packages.

LICENSE
-------
This project is licensed under a [LGPL 3.0 license](LICENSE.txt).

The contributors to the original projects which have been merged into agent-tooling:
* Alexandre Perles
* Julien Martin
* Sylvain Lemouzy
* Tom Jorquera
