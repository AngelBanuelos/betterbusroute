/* Database analysis */

---Add SERIAL column to all tables.

Users
- Name
- LastName
+ Email
- InitialLocation(Latitute, Longitude)

Route
+ RouteID
- Name
- Path1
- Path2

Alert
+ AlertID
- Name
- Latitute
- Longitude
- Ratio
-> UserID /* Email */

SavedPlace  /* Interested Places */
+ SavedPlaceID
- Name 
-> RouteId /* Nearby Routes*/
-> UserID /* Email */

History /* Routes viewed by a user */
+ HistoryID
-> SavedPlaceID /* Could be null */
-> RouteId /* Nearby Routes*/
-> UserID /* Email */
- Date
- Latitute
- Longitude

Schedule
+ ScheduleID
+ Name
-> RouteId
- Calendar
- MinTime
- MaxTime