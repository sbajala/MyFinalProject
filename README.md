# MyFinalProject

Implemeted features
- 3 different Activity: Add, ReadingList, Stats\
AddActivity allows users to add a book to their ReadingList.\
ReadingListActivity displays books in user's reading list.\
StatsActivity allows users to see the number of books NOT STARTED, IN PROGRESS, and COMPLETED. Users can view books by reading status by clicking on a button.

- Bottom navigation menu\
Users can navigate through the activities with the bottom navigation menu.

- Dialog\
When users want to update a book's information, change a book's reading status or delete a book, a dialog will appear when users click on a book in the list.

- Animation\
A bouncing button animation was used for all buttons. When an activity containing a button is started, buttons will have a bounce animation. The button in the AddActivity equally contains the bouncing animation when the button is clicked. This makes the AddActivity more lively and interactive. The buttons in StatsActivity do not contain the bouncing animation when clicked as the activity redirect users to the ReadingListActivity, therefore cutting off the bouncing animation mid-way.

- SQLite\
SQLite was used to store books entered by user.



