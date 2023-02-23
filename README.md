# Task Tracking System
In this project, I developed a task tracking system.
The main objects of the system: tasks, users and projects.
The repository contains a project with sources, compiled class files, a jar archive, and database with demo data.

The project implemented a menu with basic functionality for managing system objects.

Each task in the system has the following attributes: `ID`, `Project`, `Topic`, `Priority`, `User`, `Description`.

When you are creating a new task, you can only specify the ID of an existing project and the ID of an existing user in the system.
Otherwise, the program will ask you to try again or return to the main menu.
To comply with the condition above, the system will also not allow you to delete a user or project if the objects have active tasks.

H2 is used for data storage, at the root of the repository there is a database with demo data - test.mv.db. This database is  default in the project.
The user of the system has the ability to specify which file to use, to do that user need to `select the path to the data file` in the command menu.

In the program are implemented next functions:

[1] Create project

[2] Create task

[3] Create user

[4] Show all projects

[5] Show all tasks

[6] Show all project`s tasks 

[7] Show all user`s tasks

[8] Show all users

[9] Delete user

[10] Delete tast

[11] Delete project

[12] Choose path to data

[0] Quit

**The application is console, without a graphical interface.**
