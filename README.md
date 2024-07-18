# Github TE Stack Template

This is a template for a full stack postgresql/java/vue project, with a lot of the boilerplate already set up,
and a lot of goodies included:

- An SQL script to set up the database, with authentication tables and an example todo table included
- Spring Boot configured with a postgresql database, controllers for users/todos, and the following libraries:
  - Spring Boot DevTools
  - Spring Boot Web
  - Spring Boot Data JPA/Postgres
  - Spring Boot Validation
  - Simple JWT auth provided by eu.fraho.spring
- Vue.js configured with a basic login page, a todo list page, and the following libraries:
  - Vuex
  - Vue Router
  - Axios
  - Bootstrap Icons
  - Vuetify Components
  - Open Props

## Starting your project from this template

For normal local development, you will need to have the following installed:
- Java 21
- Node.js 16
- Postgresql 13
- PGAdmin 4
- Visual Studio Code with the following plugins installed:
  - Java Extension Pack
  - Vue Official
  - Spring Initializr Java Support
  - SQLTools
  - SQLTools PostgreSQL/Cockroach Driver

To get started, clone this repository from your terminal:

```bash
git clone https://github.com/mlambert125/todo-list-fullstack
```

Next, to make this repository your own, delete the .git folder and reinitialize git:

```bash
cd todo-list-fullstack
rm -rf .git
git init
git add -A
git commit -m "Initial commit"
```

Next, open a browser, navigate to your github account and create a new repository. Copy the url of the new repository, 
and add it as the origin of your local repository (replace YOUR_GITHUB_PROJECT_URL with the url of your new repository):

```bash
git remote add origin YOUR_GITHUB_PROJECT_URL
```

Finally, push to your remote repository:

```bash
git push -u origin master
```

From this point, you can start developing your project, and use your normal git workflow to push changes to your repository.

E.g.

```bash
git add -A
git commit -m "Your commit message here"
git push
```

Or use the integrated git tools in VSCode.


## Initial Setup

### Setting up the database

To set up the database, you will need to have Postgresql installed, and a database created. You can use PGAdmin 
to create a database.

Once the database is created, update the `application.properties` file in the `server/src/main/resources` folder 
with the correct database connection information. (Specifically, change the end of the url to the name of your 
database)

Once you have created the database, you can use pgadmin or the integrated VS Code SQL Tools to run the SQL script 
in the `database` folder to set up the tables.


## Running the project

This project includes a Spring Boot server and a Vue.js client.  You will need to run both to see the full application.


### Running the server

Open the project folder in Visual Studio Code.  On the left, at the bottom of the file explorer, you will see a
tab titled "Java Projects".  Click on this tab, and you will see the project listed.  Right click on the project
and select "Debug" to start the server with debugging enabled.

**Note:** If you do not see a project listed under "Java Projects", and instead see a button that says "Import Java Projects",
click on this button and wait a few for VSCode to discover your the server project, and then proceed. 

**Note:** If you would prefer, you can also open the project in IntelliJ IDEA and run the server from there.

Once the server is running, you should be able to navigate to `http://localhost:8080` in your browser and see that the server is running.

### Running the client

Open the terminal, and click the "+" button near the top right of the terminal window to open a new terminal.  In the new terminal,
run the following commands:

```bash
cd client
npm install
npm run dev
```

You should now be able to navigate to `http://localhost:5173` in your browser and see that the vue client application is running.


### Stop the server and client

To stop the server, click the red square in the floating run toolbar at the top of the window.

To stop the client, click `ctrl+c` in the terminal window where the client is running.

It doesn't matter which you stop first, but you should stop both before closing VSCode.


## Development

### Server

The server is a Spring Boot application, and can be developed in the same way as any other Spring Boot application.  
You can either open the pom.xml project in IntelliJ IDEA, or use the integrated tools in Visual Studio Code.  This
document will focus on using Visual Studio Code.

#### Adding to the the data model

To change the data model to reflect your own application, you will need to add the following for each new table:

- Add the create/drop table SQL to the `database/create-script.sql` file
- Add a new model class to `server/src/main/java/Models` directory
  - The existing model classes use lombok annotations to generate getters/setters/constructors.  You can use these
    annotations for your model classes as well, or write the getters/setters/constructors yourself.
- Add a new DAO class to the `server/src/main/java/Daos` directory to provide data access methods
- Add a new controller class to the `server/src/main/java/Controllers` directory to provide REST endpoints for your new table

#### Customizing user records

User records are important to the authentication system, and are stored in the `users` table.  You can add additional
fields to the user table by adding fields to the `User` model class, and updating the `create-script.sql` file to 
reflect the changes.  This will not affect the authentication system, but will allow you to store additional information
about your users.

#### Removing the todo table

If you do not need the todo table, you can remove the `Todo` model class, `TodoDao` class, and `TodoController` class
from the project, and then remove the todo table declaration and DROP from the `database/create-script.sql` file.


### Client

The client is a Vue.js application, and can be developed in the same way as any other Vue.js application.  The libraries
discussed throughout TE curriculum are included and configured (Vuex, Vue Router, Axios.)  Some additional libraries are
also included and can be used if desired (Bootstrap Icons, Vuetify Components, Open Props.)


#### Included Authentication System


#### Included API Service Library



#### Using Bootstrap Icons

#### Using Vuetify Components

#### Using Open Props in your CSS

#### Removing Bootstrap Icons

#### Removing Vuetify Components

#### Removing the default Open Props Styling

#### Removing Open Props entirely







