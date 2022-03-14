# Nasa-APOD
This app displays the image from the NASA Astronomy Picture of the Day API.

NASA API used: https://api.nasa.gov/



Features covered:
-	Allow users to search for the picture for a date of their choice.
-	Display date, explanation, Title and the image of the day.

Features not covered:

-	Allow users to create/manage a list of "favorite" listings!
-	App should cache information and should display last updated information in case of 
network unavailability.

Only happy path flow is implemented.

Further Developments that can be done:

- Use a ViewModel flow and incorporate this using the same.
- Handle the non functional requirements.
- Add a Netork Availability check.
- Use separate threads (either ExecutorService or Rx-java) to make network calls.

This is a very crude implementation. 
Further improvements to the code can be seen with more time.
