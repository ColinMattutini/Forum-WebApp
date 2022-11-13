# Congathering
Forum App Backend with Spring boot

# Technologies Used

**Backend**
* Spring boot
* PostreSQL
* JWT tokens for authorization
* JavaMailSender for email verification
* Hibernate

**Frontend**
* React.js

# Features
* Signup and login (Email and Password)
* Role based authorization (USER and ADMIN)
* JWT based auth (Access and Refresh Token)
* Homepage and topic pages
* Sorting posts by topic
* Search bar for searching topics
* Creating posts with descriptions
* Commenting on posts
* Reviewing posts by giving a positive or negative review
* Logout

# Description
A fun personal full-stack forum web application built through inspiration of Reddit and GameFAQs

 **Sign-up**
 Users can access the main page and topics of the application and view posts and comments. In order to create a post, review anything, or leave a comment, they must have an account and be signed in. In order to sign up, a user will fill out the form on the front-end which will send this request to API that will validate that the email that was included has not been used before, and the username that was created is currently not being used as well. If both of these requirements are fulfilled, the user will then be notified that they must check their email for a verification email to validate their email is real.

**Email Verification**

 Emails are sent after signing up through the app. A user's enabled status is set to "false" after initial sign-up. An email with a verification token is sent to the user's email that they sign-up. The user must then access that email and click the verification link, which sends a request to the API to set the user's enabled status to "true". Upon doing so, users are able to comment on posts, give reviews, and create posts to topics.
 
 **JWT Tokens**
 
 Upon successful login and the backend verifies the username and password entered are valid, two JWT tokens are generated. The Access Token is used for authorization to grant users access to certain features. This is setup in the Security Config class in, where depending on the API endpoint that is attempting to be accessed, the API will validate the JWT Access token is valid (The token expires after a certain amount of time as well), and that the user has access to that endpoint. Should they have access, the API will return a status of 200 and the requested information. If the user does not have the correct authorization, it will return a 403. The Refresh token is used to refresh access when the Access Token has expired.
 
 **Reviews**
 
 In order for a user to leave a review, they must be signed in. Upon giving a positive score or negative to a post, this will be saved in the database within the individual's reviews to keep track of what posts they have reviewed as well as the review the gave. This also is kept track within the PostTotalScore table that keeps a running total of positive and negative reviews for each post. The reason for keeping track of individual's reviews on posts is so that users cannot give multiple of one type of review (i.e. Repeatedly giving one post multiple positive scores) which is validated within the API. Users can however change their review, so if on a post they gave a review of POSITIVE, they can change it back to NEGATIVE, or vice versa.
 
**Search Bar**
 
 Users can search for topics through the search bar, and as they type, suggestions based on the input will drop down for the user to choose from. Upon choosing an available topic, they will be redirected to that topic page which will display all posts that belong to that topic. This is achieved by hitting an endpoint that contains all topics through a GET request that is sent to the front-end that is then stored within local storage.
 
 # How to setup locally on your computer
 
 **Pre-Requirements** 
 * Setup a database in PostgreSQL
 * Have a gmail account with an app-password created to be able to use JavaMailSender
 * Have an IDE installed
 
 # Steps
 
 1. git clone or Download ZIP this repo
 2. cd into the root directory (SocialMediaApp)
 3. Install all Maven dependencies required
 4. Set up the application properties with the following information
 
          spring.datasource.url=jdbc:postgresql://localhost:5432/(database name)
          spring.datasource.driver-class-name=org.postgresql.Driver
          spring.datasource.username= (db username)
          spring.datasource.password= (db password you set up)
          spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
          spring.jpa.hibernate.ddl-auto=update

          spring.mail.host=smtp.gmail.com
          spring.mail.username= (your gmail username)
          spring.mail.password= (your gmail app-password)
          spring.mail.properties.mail.transport.protocol=smtp
          spring.mail.properties.mail.smtp.port=587
          spring.mail.properties.mail.smtp.starttls.enable=true
          spring.mail.properties.mail.smtp.starttls.required=true

5. Upon running the application, Hibernate will take care of table creation within the PostgreSQL database
6. Visit http://localhost:8080


Designed and developed by Colin Mattutini
