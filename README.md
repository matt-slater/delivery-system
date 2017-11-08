# delivery-system
My experiment and attempt to solve a simple business problem using microservices architecture.

This project was a learning experience for me and was done completely outside school. I had been hearing a lot about microservices both online and at several of the meetups I attend, so naturally I wanted to understand what it took to build and manage such a system. This suite of services is the result of a lot of digging around in docs, trying to figure out what makes a distributed sysem tick.


## Tech Stack
I chose to use Spring Boot/Cloud and Netflix OSS for the back end services, and Angular2 for the client service. Spring Boot makes it easy to stand up a service quickly and easily. Netflix has been an industry leader in developing this type of architecture. Check out their github [here](https://netflix.github.io/). I chose Angular 2 for the client because I wanted to experiment with TypeScript.

## Services

#### Config Service

This service exists to keep the configuartions of the various services in a centralized location. This is done so that when switching between environments (prod, dev, test), all of the applications' configurations lie in a central, easy to remember place. This is one of the central ideas of the [12 Factor App](https://12factor.net/). In this case it's a [public github repository](https://github.com/matt-slater/delivery-system-config).

*_What's going on here?_*

When one of the other services start up, they check in with the config service, which in turn gets the particular service's config from the github repo.

#### Eureka Service or Discovery Service

Not much going on here in terms of exciting code, but this service is essentially the phone book for the system. Every other service automatically checks in with the Eureka Service using the `@EnableEurekaService` annotation. This allows all of the IPs and ports of the various services to be abstracted away.

#### Delivery Service

This service provides the core business logic of the system. At it's heart, it's a REST controller that handles incoming HTTP requests and maps them to application methods. The model is defined in the models package, and the database abstraction is defined in the repos package.

#### Edge Service

This service uses Netflix's Zuul to act as an API gateway, or a front door to the other services. In this case there's only one, so the addition might be overkill, but if this system were to grow and add more services, this would be a good way to manage all of the ncoming requests.

## What I learned

A whole lot about microservices.

Aside from that...

This type of architecture has a lot of scaffolding to set up. This makes it hard to test and troubleshoot bugs. For such a simple task (a CRUD app for handling deliveries for a business), I felt that it was simply not necessary. In the end, this didn't bother me because this system was always meant to be a learning experience. 

In a previous iteration of this application, a requirement was that the open deliveries were to be updated in real time so that the dispatcher could always have an up to date picture of the status and number of deliveries. At the time of writing this iteration, Netflix Zuul did not support WebSockets. So he addition of an edge service would have killed that feature. Of course the client could always call the delivery service directly, but to me that seemed hacky and not in alignment with the concpts I was trying to implement.




