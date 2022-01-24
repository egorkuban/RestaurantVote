# Restaurant Vote
REST API  Spring-Boot without frontend https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md
*
* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

### User data
 ____________________________
```
User

email - user@user.com
 
password - user
 
Admin

email - admin@admin.com
 
password - admin
``` 
### URL for Admin

 ____________________________
 
create new restaurant
 
PUT `http://localhost:8080/api/v1/admin/restaurant`

Request Body JSON
``` 
{
   "name":"Ресторан №1",
   "address":"Адрес Ресторана №1"
}
{
   "name":"Ресторан №2",
   "address":"Адрес Ресторана №2"
}
``` 
 ____________________________

create menu for restaurant

PUT `http://localhost:8080/api/v1/admin/restaurant/{restaurantId}/menu/`

Request Body JSON
``` 
{
   "menuDate": "2022-01-23",
   "dishes":[
      {
         "name":"суп",
         "price":"200"
      },
      {
         "name":"паста",
         "price":"300"
      },
      {
         "name":"кофе",
         "price":"100"
      }
   ]
}
{
   "menuDate": "2022-01-23",
   "dishes":[
      {
         "name":"пюре",
         "price":"100"
      },
      {
         "name":"торт",
         "price":"250"
      },
      {
         "name":"кофе с молоком",
         "price":"120"
      }
   ]
}
``` 
 ____________________________

delete restaurant by id

DELETE`http://localhost:8080/api/v1/admin/restaurant/{restaurantId}/`

 ____________________________

### URL for User

get all restaurants

GET `http://localhost:8080/api/v1/user/restaurant/`

 ____________________________
get a menu for a restaurant

GET `http://localhost:8080/api/v1/user/restaurant/{restaurantId}/menu`

 ____________________________
vote 

POST `http://localhost:8080/api/v1/user/restaurant/{restaurantId}/vote`







