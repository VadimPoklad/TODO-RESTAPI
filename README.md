# RESTFUL API TODO
TODO REST API is a web service developed using Java 19 and Spring (Core, Web, Data JPA, Security), along with Mupstruct and JPAStreamer.
## Description

Users can register and create todo lists, add tasks to these lists, and mark them as completed or not. This is a REST service that only accepts and sends requests in JSON format. Other users or unregistered users cannot access data that is not theirs. There is also an admin in the system with extended rights.

One of the features of this service is the ability to track the time of changes. This allows users to have a clear understanding of when tasks were added or marked as completed.
## Tech Stack
* JAVA(19)
* Spring(Core, Web, Data JPA, Security)
* Mupstruct
* JPAStreamer
  
JPAStreamer is used to solve the N+1 problem using joins. It's a lightweight library that enables fast and efficient database queries for Java Streams. By using JPAStreamer, we can avoid loading unnecessary data and improve the performance of our application.
## Conclusion
On this project I have improved my skills of working with Spring especially Spring Security. The aim of this project is to deepen Spring Security



