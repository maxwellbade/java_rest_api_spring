# java_rest_api_spring

Submitted to Analytics Vidhya
Post management tools menu

2

Max Bade
Responses
Manage responses

Max Bade
Max Bade
What are your thoughts?﻿

Cancel
Respond
There are currently no responses for this story.
Be the first to respond.

Only you can see this message
This story's distribution setting is on. Learn more
Creating a Rest API with Java in Spring
Max Bade
Max Bade
Jul 13 · 7 min read





Image for post
Spring boot + Rest API
Introduction
We all know there are multiple ways to create a Rest API. You can use Flask, Django, Rails or Sinatra to do so, but this article will focus on creating said restful API endpoint using Java, Spring Boot and Spring Data Rest.
Steps:
Generate a Spring Boot Project
Add Entity
Create Rest Repository
Database Configuration
Create Seed Data File
Run
Image for post
Run console output example
Generate a Spring Boot Project
Go to http://start.spring.io/ to generate your Spring Boot (Maven) project. Add JPA, Rest Repositories, and H2 dependencies and click on Generate Project.
Image for postImage for post
Add Entity, Columns, Getters/Setters and Source
If it looks gnarly, it’s not. Out of the ~ 200 characters below, I typed less than 50 of them. Spring features a way to add getters and setters as well as a tostring; I’m sure other IDEs like Eclipse and IntelliJ offer similar functionality.
Image for postImage for post
Your full spring maven project
First you’ll click on your app (com.example.demo in my case) and add new package — which creates a file with thepackage and public line (see image below). All you will be typing are the private lines! You’ll right click, go to source and select generate tostring and generate getters and setters and voila, the rest is added in. Getters and Setters are all the public void lines and the tostring is the override line. The import lines will get added automatically. Once you add the @Entity line; you’ll get the import entity line (line 3 below) added automatically as well.
Image for postImage for post
Create Rest Repository
Next you’ll create an interface file. Right click on your “com.example.demo” (or whatever you called your project), and create new>interface; you will see thepackage and public interface lines shown below. Add in the @RepositoryRestResource line (click control + enter to bring up the tool tips and press enter on the specific method you want to add — this will generate the import lines (packages)), and the text after interface; this will add the Jparepository which comes with a bunch of built in methods like: findAll , findOne , and delete to name a few.
Image for postImage for post
Your interface file
For Your Edification — Rest Methods:
@RepositoryRestResource is used to set options on the public Repository interface — it will automatically create endpoints as appropriate based on the type of Repository that is being extended (i.e. CrudRepository/PagingAndSortingRepository/etc).
@BasePathAwareController and @RepositoryRestController are used when you want to manually create endpoints, but want to use the Spring Data REST configurations that you have set up.
If you use @RestController, you will create a parallel set of endpoints with different configuration options — i.e. a different message converter, different error handlers, etc — but they will happily coexist (and probably cause confusion).
Database Configuration
*There are other places/ways to store/connect your API, such as MySql, PostgreSQL, NoSQL etc, but for this article, we’ll look at the h2 db option.
We now need to update the src/main/resources/application.properties file with the properties below. Combined with our H2 dependency (outlined below), Spring Boot will create an in-memory database for us to play with, which we can eventually query and do whatever we want with!
Image for postImage for post
The Application properties file
Create data.sql File
Just to start off with some data in our database, lets create a sql file and import some rows (just to make sure it works). Add a file named data.sql in the src/main/resources directory (right click>new>file>”data.sql”>enter), and Spring will automatically pick it up and execute it on startup. Place the following contents in the file (make sure you’re adding the right number of inputs based on the columns you added in your original java file):
Image for postImage for post
SQL file
I did have to add something when I added this .sql file initially — I did this a few days ago and consequently forgot exactly what I needed to do in order for this .sql file to work/show up properly in Spring. But if you google the error, you should figure it out in a few minutes. You might not experience the error at all though. Just a tip*.
Add Dependencies to POM file
This part isn’t tricky, but there’s one extra step you need to perform for your API to work. We need to add the tomcat and possibly the h2 dependency to the pom.xml file.
Tomcat: Apache Tomcat is a long-lived, open source Java servlet container that implements several core Java enterprise specs, namely the Java Servlet, JavaServer Pages (JSP), and WebSockets APIs.
Basically you need to search your Maven Dependencies directory to find your specific version of tomcat and h2, then go to https://mvnrepository.com/artifact/org.apache.tomcat/ (same for h2) to find the right version’s code and add it to your pom.xml file.
This is how you add more dependencies though — so may come in handy in the long run!
Image for postImage for post
Your POM.xml file
Run
At this point, you can run it and it will work. But I’d like to explore a bit further ;)
Image for postImage for post
Your Localhost url
H2 Console
Let’s get a look at what our actual in-memory h2 database looks like right? Yeah let’s do that. Navigate to http://localhost:8080/h2-console/ and enter in the credentials (the stuff you added in your application.properties file) and test>connect.
Image for postImage for post
Login to h2 console
Once logged in, select * your database and let’s see what we’ve got!
Image for postImage for post
Bam
Postman Client Requests
If you’re like me and don’t have an actual app/client to get requests from for your newly built java rest api (so cool), then you need a way to test different requests. This is where Postman comes into play. This is an open source tool and it’s awesome. I won’t go into it much, but essentially you can specify your url and send different types of requests: get, post, delete, put etc.
Image for postImage for post
Postman
Controller Route
If you end up needing to modify/create your own endpoint configurations, then you’ll want to go the controller route. I won’t go too deep into this, but basically you can specify your own methods for X operations. Below is an example I wrote before going the @RepositoryRestResource route. With the Repo rest route, these methods are already available thus no need to recreate them.
You’ll see some random lines in here as well — this is really a test script I used to help get my bearings on Java and Spring API design. Such as the lines below — which post a method I created in the console:
//post to console
System.out.println(repo.findByTech("java"));
System.out.println(repo.findByAidGreaterThan(102));
System.out.println(repo.findByTechSorted("java"));
Image for postImage for post
Example Controller Java File
Add Page for Submitting Data to DB
Let’s do one last thing where we create a page that presents html forum tags for user-inputted data and store that data in our h2 database. Sounds on paper, but is it actually that easy to code? Yep — it’s not so bad.
Steps:
Create a home.jsp file from within src>main>webapp (I added the webapp directory)
Do so by right clicking webapp>new>other>”.jsp” (spring finds the filetype for you)>”home.jsp”>enter
Input your code like so:
Image for postImage for post
Home.jsp file for forum submissions
You can then navigate to http://localhost:8080/home.jsp and submit inputs that get added directly to your h2 database. Pretty cool huh?
Image for postImage for post
http://localhost:8080/home.jsp url
Conclusion
I learned this in just a few days last week. It seems daunting, sure, but ultimately it wasn’t that bad. The hardest part was knowing where to reference all the different files/methods/objects you created. There’s plenty of documentation out there on this, but hopefully this provides you with a decent first few steps to creating a Rest API with Java ans Spring!
Source Code:
Github: https://github.com/maxwellbade/java_rest_api_spring
Image for postImage for post


Java
Rest
Spring Boot
API
Rest Api






Max Bade
WRITTEN BY

Max Bade
Data Engineering and Analytics Consultant Insta:https://www.instagram.com/maxbade/ linkedIn:www.linkedin.com/in/maxbade github:https://github.com/maxwellbade
Discover Medium
Welcome to a place where words matter. On Medium, smart voices and original ideas take center stage - with no ads in sight. Watch
Make Medium yours
Follow all the topics you care about, and we’ll deliver the best stories for you to your homepage and inbox. Explore
Explore your membership
Thank you for being a member of Medium. You get unlimited access to insightful stories from amazing thinkers and storytellers. Browse
About
Help
Legal
