# User Guide

## Introduction 

Task tracker. Supports simple todo (no date),
deadline (due date) and event (event date).

Loads task list from `/data/duke.txt` on startup, creating
the file if not already present.

Task list is saved to `/data/duke.txt` on every modification of task list.

## Usage

### `list` - Display task list

Prints the current task list.

Format: `list`

Task types:
* T - Todo
* D - Deadline
* E - Event

Status:
* y - Done
* n - Not done

Example: 
1. [E][y] Birthday (at: 15/05/2019 11:00)
2. [D][n] Homework (by: 08/04/2020 22:00)

### `todo` - Add todo

Adds a Todo item to the last list. Does not support entering of due date

Format: `todo <task name>`

Example: `todo Cook dinner`

### `deadline` - Add deadline

Adds a Deadline item to the task list. Compulsory to enter due date, in `string` format

Format: `deadline <task name> /by <date> <time>`

Date has format `yyyy-mm-dd`

Time format: `hh:ss` in 24-hour format

Example: `deadline learn Java /by 2020-09-25 16:59`

### `event` - Add event

Adds an Event item to the task list. Compulsory to enter event date, in `string` format

Format: `event <task name> /at <date> <time>`

Date has format `yyyy-mm-dd`

Time format: `hh:ss` in 24-hour format

Example: `event meal with Yanny /at 2020-12-18 01:00`

### `find` - Finds matching tasks

Searches through task list and displays a list of matching tasks. Searches based on exact string matching

Format: `find <search string>`

Example: `find meal`

### `done` - Mark as done

Changes the status of a task to "done". 

Format: `done <task index>`

Example: `done 2`

### `delete` - Delete task

Deletes a task from the task list

Format: `delete <task index>`

Example: `delete 3`

### `clear` - Clears task list

Clears the entire task list. Will require confirmation

Format: `clear`

### `bye` - Exits program

Quits the program. No side effects.

Format: `bye`