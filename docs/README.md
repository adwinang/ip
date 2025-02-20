# Zephyr User Guide

![Ui.png](Ui.png)

Welcome to Zephyr, I am just a simply chat assistant to help thou with thou task

## How to get started
1. Go to the following [release](https://github.com/adwinang/ip/releases) and download the latest release
2. Run the executable
3. To figure out the commands, just type any random word and Zephyr will give you a list of commands you can key in
4. Once you know the command, you can simply just start using Zephyr as your personal task manager


> Do note that all date inputs that is recognisable by Zephyr has to follow this format "_21 Feb 2025_"


Example: `random word`

Output
```
I do not understand what thou art saying.
Please enter a valid command using the follow:
1. list
2. find <keyword>
3. mark <task number>
4. unmark <task number>
5. tag <task number> <...tag>
6. todo <task description>
7. deadline <task description> /by <deadline>
8. event <task description> /from <start time> /to <end time>
9. upcoming <task type> <days>
10. delete <task number>
11. bye - To exit the programme
```

Example: `event Birthday /from 21 Feb 2025 /to 21 Feb 2025`

Output
```
Got it. I've added this task:
[][E] Birthday (from: 2025-02-21 to: 2025-02-21) 
```


## Features
- Add normal todo tasks
- Add deadline tasks
- Add event tasks
- Find tasks that has the keyword indicated
- Upcoming tasks (deadline or event) within how many days from now
- mark and unmark tasks
- list all tasks
- Save tasks as Markdown for ease of migrating to markdown editors