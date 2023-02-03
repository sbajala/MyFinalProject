# Reading List Tracker
Android Mobile application that allows users to keept track of their readings.
 
 ## Programming languages and technologies used
 - Java (back-end)
 - XML (front-end)
 - Android Studio
 - SQLite
 
 ## Description
 Users are able to do the following:
 - Add books to their reading list
 - View details of books in their reading list
 - Edit a book's details
 - Update a book's reading status (not started, in progress or completed)
 - Remove a book form their reading list
 - View number of books by reading status
 - View all books according to their reading status
 
## Required features
### 3 different Activity: AddActivity, ReadingList, Stats\
AddActivity allows users to add a book to their reading list.\
ReadingListActivity displays books in user's reading list.\
StatsActivity allows users to see the number of books NOT STARTED, IN PROGRESS, and COMPLETED. Users can view books by reading status by clicking on a button.

### Include a form (information of state) that will persist (file preferences or db)
Application uses a form to obtain details of a book (title, author, and note) to store in a database.

### Use of menus, dialogs or notifications
Application uses a bottom navigation menu with which users can navigate through the activities (pages).
Application also uses a dialog box when users want to update a book's information, change a book's reading status or delete a book.

### Use graphics, animations or sounds
Application uses a bouncing button animation for all buttons. When an activity containing a button is started, buttons will have a bounce animation. The button in the AddActivity equally contains the bouncing animation when the button is clicked. This makes the AddActivity more lively and interactive. The buttons in StatsActivity do not contain the bouncing animation when clicked as the activity redirect users to the ReadingListActivity, therefore cutting off the bouncing animation mid-way.

### Use SQLite, at least one sensor or an event specific to a mobile platform (touch, scroll, fling, ...).
Application uses SQLite to store books entered by user.


