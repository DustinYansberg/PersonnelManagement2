
# Title

## Sailors of The Point Personnel Management Tool

# Description

This a Personnel Management Tool that interfaces with the SCIM API for SailPoint. It allows for the creation of new users, and allows for the creation of new acounts for connected applications like Salesforce and Entra ID

# Developers
[Gianni St. Clair](https://github.com/Gstclair1)

[Chris Saenz](https://github.com/ChriSaenz)

[Dustin Yansberg](https://github.com/DustinYansberg)

# Technologies Used

### Cloud
<img alt="Static Badge" src="https://img.shields.io/badge/SailPoint-IIQ-EF2D5E?style=for-the-badge"> <img alt="Static Badge" src="https://img.shields.io/badge/Azure-Cloud-blue?style=for-the-badge"> <img alt="Static Badge" src="https://img.shields.io/badge/Microsoft-Entra_id-blue?style=for-the-badge"> <img alt="Static Badge" src="https://img.shields.io/badge/salesforce-grey?style=for-the-badge&logo=salesforce&logoColor=%2300A1E0">



### Database
<img src="https://img.shields.io/badge/mysql-gray?style=for-the-badge&logo=mysql&logoColor=white&labelColor=%234479A1&color=gray"/> 

### Server
<img alt="Static Badge" src="https://img.shields.io/badge/Java-blue?style=for-the-badge&logo=oracle&logoColor=%23F80000"> <img alt="Static Badge" src="https://img.shields.io/badge/Spring_boot-blue?style=for-the-badge&logo=springboot&logoColor=%23%236DB33F"> <img alt="Static Badge" src="https://img.shields.io/badge/json-blue?style=for-the-badge&logo=json&logoColor=%23000000">



### Frontend
<img src="https://img.shields.io/badge/javascript-gray?style=for-the-badge&logo=javascript"/> <img alt="Static Badge" src="https://img.shields.io/badge/Angular-blue?style=for-the-badge&logo=angular&logoColor=%230F0F11"> <img alt="Static Badge" src="https://img.shields.io/badge/PrimeNG-grey?style=for-the-badge&logo=primeng&logoColor=%23DD0031">


# Why?

Our team needed a way to give managers the ability to add, update, and delete employees while also having the ability to properly provision their accounts without accessing the SailPoint admin console.

# How to Run
## Prerequisites
- Have a SailPoint Deployment ready to go
- Have a way to run Java 8 programs
- Have angular 17+ installed
- Adjust the application.settings of the Java program to match your SailPoint deployment information
- Run the java backend
  - install maven dependencies
- Run the angular front end
  - run `npm i` to install angular libraries
- Navigate to `localhost:4200` in your preferred browser and enjoy!

# Demo

## View all Users

## Sort All Users

## Search for a specific User

## View, Edit, and Delete a user

## Add and Account to a User

## Delete an account from a user

## Create a new user




# Other technical stuff

The Server logic is written in Java with RESTful API calls for offices and employees. 

The frontend is written in Angular JS with a Material UI component Library.

# Contributing

At this time, we have no specific elements that we are looking for contributions on. As development continues, that may change. If you have any suggestions or questions, please do not hesitate to reach out to me: DustinYansberg@gmail.com
