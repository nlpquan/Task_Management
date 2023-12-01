# Task Management Java Spring Boot with RESTful API - Quan Nguyen
Below you will find the steps by steps configuring Task Management Restful API
## Screencast Demo
- [Instruction Video Part 1](https://www.loom.com/share/d49900cbfb4948e6b3dac1609fd9367b)
- [Instruction Video Part 2](https://www.loom.com/share/7e78b269e2d647c390e8e8416fd0abb0)
## 1. Setup MySQL
- Download [MySQL 8.0.35 Community Server](https://dev.mysql.com/downloads/mysql/)
## 2. Download and Extract The Zip File to The Preferred Directory
## 3. "Optional" Download [Spring Tool 4 For Eclipse](https://spring.io/tools)
- Open the extracted folder on Spring Tool
- Feel free to choose your own IDE. Personally I use Spring Tool Suite 4 with Eclipse
## 4. Install Postman To Test API
## 5. Launch Application
- Run MySQL server using MySQL Configurator on `port: 3306`, `password: 123456`
- Check the `application.properties` file in task_management project to make sure the `username=root`, `password=123456`
- Open local terminal, connect to mysql `mysql -u root -p`, prompt then enter password `123456`
- Create a new task_management database `create database task_management;`, make sure to match with the `application.properties` file. Then `use task_management;` to choose the database
- Go back to the task_management project, run the application. For Spring Tool 4, navigate to TaskManagementApplication.java -> right-click -> run as -> Spring Boot App
- `tasks` and `users` tables will be automatically created when launched, `show tables;` in the MySQL server running on the local terminal to confirm these two availabilities
- Go to a desired browser and enter `localhost:8080`
- Make sure to go through all the Security and Testing API on Postman
## 6. Using Postman to Test API
- Without authentication, it will show the error `unauthorized`
- Using the `username` and the encrypted `password` for Authorization tab, type Basic Oauth, enter your `username` and `password` created on `localhost:8080`, keep the application running
- You can now be authorized for testing the API, as well as error handling that uses JPA for storing tasks data
## Assumption 1: Testing Data Validation in Register Form
- IMPORTANT: When you try to test data validation in the register form, there will be a pop-up window to tell you to log in. You can turn it off and choose not to. 
## Assumption 2: Deploy to Docker
- Unfortunately, the last step was broken and the deployment was unsuccessful
- Potential issue comes from the Spring Security, since the Spring Boot version is 2.7.1, hence the WebConfigurationAdapter is deprecated
