# API Documents

## 목차

- [User, 유저 인증](#user-유저-인증)
  - [Sign Up](#post---sign-up)
  - [Sign In](#post---sign-in)
  - [Check Duplicate Email](#get---check-duplicate-email)
  - [Check Duplicate Nickname](#get---check-duplicate-nickname)
- [Battle, 전투](#battle-전투)
  - [Result](#post---result)
- [Ranking, 랭킹](#ranking-랭킹)
  - [Leader Board](#get---leader-board)
  - [Player Ranking](#get---player-ranking)
- [Models](#models)
  - [UserResponseDto Model](#userresponsedto-model)

<br></br>

# User, 유저 인증

## POST - Sign Up

```
~/user/sign-up
```

입력받은 정보로 회원가입을 진행합니다. 성공 여부를 반환합니다.

### Parameters

| Key      | Type   | Description |
| -------- | ------ | ----------- |
| email    | String | 이메일      |
| password | String | 비밀번호    |
| nickname | String | 닉네임      |

### Response

| Key    | Type    | Description        |
| ------ | ------- | ------------------ |
| result | Boolean | 회원가입 성공 여부 |

<br></br>

## POST - Sign In

```
~/user/sign-in
```

입력받은 이메일과 비밀번호로 로그인을 진행합니다. 로그인 성공시 유저 정보를 반환합니다. 로그인 실패시 에러를 반환합니다.

### Parameters

| Key      | Type   | Description |
| -------- | ------ | ----------- |
| email    | String | 이메일      |
| password | String | 비밀번호    |

### Response

| Key      | Type   | Description |
| -------- | ------ | ----------- |
| id       | String | 고유 ID     |
| nickname | String | 닉네임      |
| score    | int    | 점수        |
| win      | int    | 승리        |
| lose     | int    | 패배        |
| draw     | int    | 무승부      |
| ranking  | int    | 랭킹        |

<br></br>

## GET - Check Duplicate Email

```
~/user/duplicate-email
```

입력된 이메일이 이미 존재하는 경우 `true`를 반환합니다. 존재하지 않는 경우 `false`를 반환합니다.

### Parameters

| Key   | Type   | Description   |
| ----- | ------ | ------------- |
| email | String | 사용할 이메일 |

### Response

| Key    | Type    | Description                      |
| ------ | ------- | -------------------------------- |
| result | Boolean | 입력으로 받은 이메일의 존재 여부 |

<br></br>

## GET - Check Duplicate Nickname

```
~/user/duplicate-nickname
```

입력된 닉네임이 이미 존재하는 경우 `true`를 반환합니다. 존재하지 않는 경우 `false`를 반환합니다.

### Parameters

| Key      | Type   | Description   |
| -------- | ------ | ------------- |
| nickname | String | 사용할 닉네임 |

### Response

| Key    | Type    | Description                      |
| ------ | ------- | -------------------------------- |
| result | Boolean | 입력으로 받은 닉네임의 존재 여부 |

<br></br>

# Battle, 전투

## POST - Result

```
~/battle/result
```

전투 결과를 저장합니다.

### Parameters

| Key    | Type         | Description |
| ------ | ------------ | ----------- |
| result | BattleResult | 전투 결과   |

### Response

| Key      | Type   | Description |
| -------- | ------ | ----------- |
| id       | String | 고유 ID     |
| nickname | String | 닉네임      |
| score    | int    | 점수        |
| win      | int    | 승리        |
| lose     | int    | 패배        |
| draw     | int    | 무승부      |
| ranking  | int    | 랭킹        |

<br></br>

# Ranking, 랭킹

## GET - Leader Board

```
~/ranking/leader-board
```

페이지에 따른 상위 랭커 정보를 반환합니다.

### Parameters

| Key  | Type | Description |
| ---- | ---- | ----------- |
| page | int  | 페이지      |

### Response

| Key   | Type                                           | Description                  |
| ----- | ---------------------------------------------- | ---------------------------- |
| users | Array\<[UserResponseDto](#user-response-dto)\> | 상위 랭킹 유저들의 정보 배열 |

## GET - Player Ranking

```
~/ranking/player-ranking
```

사용자의 랭킹을 반환합니다.

### Parameters

None

### Response

| Key     | Type | Description |
| ------- | ---- | ----------- |
| ranking | int  | 랭킹        |

<br></br>
<br></br>

# Models

## UserResponseDto Model

유저 정보 반환용 모델

| Key      | Type   | Description |
| -------- | ------ | ----------- |
| id       | String | 고유 ID     |
| nickname | String | 닉네임      |
| score    | int    | 점수        |
| win      | int    | 승리        |
| lose     | int    | 패배        |
| draw     | int    | 무승부      |
| ranking  | int    | 랭킹        |
