# Server

Server for Magica Duel  
Team Project of Ajou Univ. Media Software Engineering Lecture

<br></br>

## Database Architecture

|    Column     |   Type   | Not Null | Auto Increment |     Key     | Default Value |
| :-----------: | :------: | :------: | :------------: | :---------: | :-----------: |
|      id       |  bigint  |    O     |       O        | Primary Key |               |
|     email     | varchar  |          |                |             |               |
|   nickname    | varchar  |          |                |             |               |
|   password    | varchar  |          |                |             |               |
|     score     |   int    |          |                |             |       0       |
|      win      |   int    |          |                |             |       0       |
|     lose      |   int    |          |                |             |       0       |
|     draw      |   int    |          |                |             |       0       |
| created_date  | datetime |          |                |             |               |
| modified_date | datetime |          |                |             |               |

<br></br>

## Class Diagrams

![classDiagram](https://user-images.githubusercontent.com/35136024/167622551-d330c654-a289-40a1-8234-e1304a95c545.png)

<br></br>

## Use-case Diagrams

![use-case](https://user-images.githubusercontent.com/35136024/167622869-872fb318-7870-45db-aedd-e0c9aa8532a1.png)
