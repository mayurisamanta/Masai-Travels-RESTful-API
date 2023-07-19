![Logo](https://github.com/mayurisamanta/bright-thrill-6240/blob/main/Assets/logo.png?raw=true)

# Masai Travels

> Masai Travels (bus ticket reservation system) is an RESTful web service designed to provide customers with a personalized easy-to-utilize user experience for booking and purchasing tickets online. It stores customers' personal data records, scheduled routes, frequent trips, drop points, and other information.

> It has 6 modules Admin, User, Reservation, Bus, Route and Feedback.

> Masai Travels (Bus ticket reservation system) application during the construct week at [Masai School](https://masaischool.com/). Completed the project in five days with a team of five aspiring developers.

# 👉 [Click here](https://drive.google.com/drive/folders/1tQn3oJP_2FBNzV-GOHlN1JWGx-RyNB2f?usp=drive_link) to go through detail explanation of this application

# Features

- User and Admin authentication and validation using session uuid
- Admin can provide details like Bus Name, Bus Route, Bus Type –AC/non-AC, seats, departure time and arrival time
- Admin can provide details of route
- Admin can provide the confirmation of the seat to the User
- User can register by giving all the details
- User can book ticket by selecting Source and destination
- User can give feedback for bus experience
- User can cancel reservation

# Technology used 

- Java
- MySQL
- Spring Boot
- Spring data JPA
- RESTful api
- Hibernate
- Swagger
- Lombok
- Maven
- Postman
- Spring tool suite eclipse
- Git & GitHub

# Modules

- Login Logout Module
- Admin Module
- User Module
- Route Module
- Bus Module
- Reservation Module
- Feedback Module

# Lessons Learned

- Gain excessive knowledge on application of Java, MySQL and SpringBoot.
- Gain knowledge on creating RESTful API.
- Got to know how to collabrate with team members.
- Enjoyed the process of learning and creating the application.

# Overview of Our work

## **Er Diagram** 
*Er diagram of our project*
</br>
![Er diagram](https://github.com/mayurisamanta/bright-thrill-6240/blob/main/Assets/erdiagram.png?raw=true)

## **All Modules** 
*All modules of our project*
</br>
![Er diagram](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/All_Modules.jpg?raw=true)

## **Admin Module** 
*Admin can login by using username (admin) and password (admin1234)*
</br>
![Admin Module](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/Admin_Module.jpg?raw=true)


## **User Module**
*Customer can signup first if not registered earlier*
</br>
*Customer can login with earlier provided username and password*
</br>
![User Module](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/User_Module.jpg?raw=true)

## **Bus Module** 
*Admin can add Bus by providing neccessary details*
</br>
![Bus Module*](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/Bus_Module.jpg?raw=true)

## **Route Module**
*Admin can add Route by providing neccessary details*
</br>
![Route Module](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/Route_Module.jpg?raw=true)

## **Reservation Module**
*User can book Reservation by selecting Source and destination*
</br>
![Reservation Module](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/Reservation_Module.jpg?raw=true)

## **Feedback Module**
*User can give feedback for bus experience*
</br>
![Feedback Module](https://github.com/mayurisamanta/Masai-Travels-RESTful-API/blob/main/Assets/Feedback_Module.jpg?raw=true)

# How To Run Our Project In Your Machine

1. Clone our Project into your system
    - Go to the folder location you want to clone
    - Open terminal 
    - Run command ``` git clone https://github.com/mayurisamanta/Masai-Travels-RESTful-API.git ```
2. Open your preferred Integrated Development Environment (IDE) such as Spring Tool Suite (STS) or IntelliJ IDEA.
3. Import the cloned project into your IDE:
   - In STS, click on "File" in the top menu, then select "Import"
   - In IntelliJ IDEA, click on "File" in the top menu, then select "Open"
4. Select the project directory where you cloned the repository ```BusTicketReservationSystem``` and click "OK" or "Open" to import the project.
5. You can run this project with MySQL db or H2 db
    - For MySQL, replace all the values under ```db specific properties``` and comment all specifications under ```H2 db``` in ```application.properties```
    - For H2, you can use the same or replace the values
6. Build the project and run ```BusTicketReservationSystemApplication```
7. To access database
    - For MySQL, you can use ```terminal``` as shown in explanation video or ```MySQL Workbench```
    - For H2, go to ```http://localhost:8008/h2-console/``` and fill proper details mentioned in ```application.properties``` under ```H2 db```
8. Open Swagger UI ```http://localhost:8008/swagger-ui/#/``` and execute the end points as in explanation video

# Contributor

#### Mayuri Samanta
> Github: [mayurisamanta](https://github.com/mayurisamanta) 
(Teamlead)
Worked on Feedback module
#### Brajesh kumar
> Github: [brajesh623](https://github.com/brajesh623)
Worked on Admin and User module

#### Manjunath Patil 
>Github: [btcpatil](https://github.com/btcpatil)
Worked on Route module

#### Anshul Sharma
>Github: [Jhala26](https://github.com/Jhala26)
Worked on Reservation Module

#### Sarthak Singh
>Github: [Sarthak0008](https://github.com/Sarthak0008)
Worked on Bus Module


