# User Guide

## Introduction 

Task tracker. Supports simple todo (no date),
deadline (due date) and event (event date).

Loads task list from `/data/duke.txt` on startup, creating
the file if not already present.

Task list is saved to `/data/duke.txt` on every modification.

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
1. [E][y] Birthday (at: today)
2. [D][n] Homework (by: 23rd October)

### `todo` - Add todo

Adds a Todo item to the last list. Does not support entering of due date

Format: `todo <task name>`

Example: `todo Cook dinner`

### `deadline` - Add deadline

Adds a Deadline item to the task list. Compulsory to enter due date, in `string` format

Format: `deadline <task name> /by <due date>`

Example: `deadline learn Java /by 5th Sep`

### `event` - Add event

Adds an Event item to the task list. Compulsory to enter event date, in `string` format

Format: `event <task name> /at <event date>`

Example: `event meal with Yanny /at 2-4pm Friday`

### `done` - Mark as done

Changes the status of a task to "done". 

Format: `done <task index>`

Example: `done 2`

### `delete` - Delete task

Deletes a task from the task list

Format: `delete <task index>`

Example: `delete 3`