# Server

Server for Magica Duel  
Team Project of Ajou Univ. Media Software Engineering Lecture

<br></br>

## Database Architecture

## User Table

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

## Class Diagram

![classDiagram](https://user-images.githubusercontent.com/35136024/167622551-d330c654-a289-40a1-8234-e1304a95c545.png)

<br></br>

## Use-case Diagram

![use-case](https://user-images.githubusercontent.com/35136024/167622869-872fb318-7870-45db-aedd-e0c9aa8532a1.png)

<br></br>

## Activity Diagrams

![activityDiagram_lobby](https://user-images.githubusercontent.com/35136024/167684052-656a6501-2926-4dfe-9b25-0208e10c52b1.png)
![activityDiagram_matching](https://user-images.githubusercontent.com/35136024/167684076-38f74981-9bf9-4ca1-b1d9-a1ae66026e79.png)

<br></br>

## Sequence Diagrams

![sequenceDiagram_spring](https://user-images.githubusercontent.com/35136024/167684399-8fe5941b-25a3-4584-a141-7342ed1b7241.png)

![sequenceDiagram_node](https://user-images.githubusercontent.com/35136024/167684548-fc778cc6-6c52-41f7-8fd1-c6415a891f75.png)


min
