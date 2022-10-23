# database
Service that provides a possibility to add data in MongoDB and PostgreSQL using а changelog files

## Step-by-step guide to adding a сhangelog files
### Step 1:
Clone **database** project on your laptop.

### Step 2:
Change directory to **changelogs** and create in the folder with the name of the required database a folder with the date the files were added.

### Step 3:
Add your files **(JSON or XML)** (if you use MongoDB) or **SQL**  (if you use PostgreSQL) in just created folder.

Example Changelogs: [JSON](https://docs.liquibase.com/concepts/changelogs/json-format.html), [XML](https://docs.liquibase.com/concepts/changelogs/xml-format.html) or [SQL](https://docs.liquibase.com/concepts/changelogs/sql-format.html) format.

### Step 4:
Push changes in branch. Merge change into main branch.

### Run database on your laptop
The database starts automatically when you start projects using **docker-compose-run.sh** file.