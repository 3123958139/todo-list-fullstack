# todo-list-fullstack

1. Create a new empty repository on github
2. Create a codespace for it
3. "Codespaces: Add Dev Container Configuration Files"
    - Create new
    - Java and Postgres
    - Include Maven
    - (Add) Node/Npm
4. In command pallete "Codespaces: Rebuild container" (not full)
5. In extensions, install these extensions for VSCode:
    - Vue - Official
    - Spring Initializr Java Support
    - SQL Tools
    - SQL Tools Postgres/Cockroach Driver
6. Restart container (NOT rebuild)
7. Click database icon on left and add Postgres connection:
    - localhost, postgres:postgres, postgres database
8. Make and run a create db script
    - Make a database folder in the root (next to .devcontainer, etc.)
    - Add a sql file for creating your database, end it with a test select.
    - At the top of the file, click, "Run on active connection" button.  
    - Results for each statement will be on a tab in the right split. Check last one.
9. From command palette Spring Initializr: Create Maven Project (Java 21)
    - Add our usual deps:
        - Spring Web
        - JDBC
        - Postgres Driver
        - Validation
        - Lombok
10. Setup config file for your new project
    - project/src/main/resources/application.properties

        spring.application.name=todo
        spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
        spring.datasource.username=postgres
        spring.datasource.password=postgres
        
11. Run the java app from the "Java Projects" tab on the left file browser
    - If all goes well, it will run and will tell you it set up a port-forward to a public IP and give you a link to your running site
        - You can share this link!

12. In terminal:  `npm create vue@latest` and name your project something like todo-client
    
13. Open client/vite.config and change the build folder to point to your java apps static folder.  Add this to top level object:

  build : {
    outDir: '../todo/src/main/resources/static/'
  },
  server : {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }

15. Start a new terminal
    - cd to the client app
    - npm run dev
