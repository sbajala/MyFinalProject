# Reading List Tracker
Android Mobile application that allows users to keep track of their readings.
 
 ## Programming languages and technologies used
 - Java (back-end)
 - XML (front-end)
 - Android Studio
 - SQLite
 
 ## Description
 The goal behind the application is to help users keep track of their reading list by indicating which books are in progess, not started, or completed. Users are equally able to add notes where they can indicate which pages to read or some reminder notes. <br/> <br/>
 In summary, users are able to do the following:
 - Add books to their reading list
 - View details of books in their reading list
 - Edit a book's details
 - Update a book's reading status (not started, in progress or completed)
 - Remove a book form their reading list
 - View number of books by reading status
 - View all books according to their reading status
 
## Required features
### Have more than one Activity or Use of Fragments
Applications uses three activities:\
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

## Future Considerations
It would be nice to send a notification to the user when a book has been 'in progress' or 'not completed' for a certain amount of time.

## Credits
Created by Sharmaine Bajala


