# EindopdrachtBackendErenDogan


post - localhost:8080/users
{
"username": "eren",
"password": "password123",
"roles": [ "ADMIN"]
}


post - localhost:8080/auth
{
}



post- localhost:8080/menu
{
"name": "Cocktails"
}


post - localhost:8080/drink
{

"name": "ammereto sour",
"price": 12.99,
"ingredients": "ammereto, citroensap",
"alcohol": true
}


post -localhost:8080/reservations

{
"reservationName": "eren dogan",
"reservationTime": "2024-09-08T19:00:00",
"tableNumber": 3,
"guests": 2,
"phoneNumber": 12345678
}

post - localhost:8080/menu/1/drink/1
{
}

post - localhost:8080/profiles
{   "username": "erennn",
"firstname": "eren",
"lastname": "dogan",
"address": "straat1",
"phoneNumber" : 1234456465,
"email" : "eren@gmail.com"
}

put - localhost:8080/users/eren/profile/1
{
}
