# todo-list-fullstack

1. Create a new empty repository on github
2. Create a codespace for it
3. "Codespaces: Add Dev Container Configuration Files"
    - Create new
    - Java and Postgres
    - Include Maven
    - (Add) Node/Npm
4. "Codespaces: Rebuild container" (not full)
5. Install some extensions for VSCode:
    - Vue - Official
    - Spring Initializr Java Support
    - SQL Tools
    - SQL Tools Postgres/Cockroach Driver
6. Reload container
7. Click database icon on left and add Postgres connection:
    - localhost, postgres:postgres, postgres database
8. Make and run a create db script
    - Make a database folder in the root (next to .devcontainer, etc.)
    - Add a sql file for creating your database, end it with a test select.
    - At the top of the file, click, "Run on active connection" button.  
    - Results for each statement will be on a tab in the right split. Check last one.
9. Spring Initializr: Create Maven Project (Java 21)
    - Add our usual deps:
        - Spring Web
        - JDBC
        - Postgres Driver
        - Validation
        - Lombok
10. Setup config file for your new project
    - project/src/main/resources/application.properties

        spring.datasource.url=jdbc:mysql://localhost:3306/todo
        spring.datasource.username=postgres
        spring.datasource.password=postgres
        
7. Right-click on Java file and run.



