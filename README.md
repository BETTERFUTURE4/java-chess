# java-chess
체스 게임 구현을 위한 저장소

## 협업 규칙 

 - 최대한 페어의 코드를 존중한다.
    - 다른 사람의 코드를 수정하는 것보다는 설명하는 방향으로.
 - 커밋 단위로 드라이버, 네비게이터를 변경한다.
 - 커밋 규칙
    - [docs] 한글 설명의 컨벤션
 - 휴식 or 이해가 필요한 시간이 필요하면 주저없이 상대에게 말한다.
 
## 프로젝트 목표

 - 책임 주도 설계
    - 객체 지향의 사실과 오해에서 지도하는 방법으로
    - 인터페이스 도출 연습
    
 - 완전한 TDD
 
 - Stream 지향
  
  
## 1단계 기능목록

### 체스판 -> 체스통
- [x] 모든 체스말을 생성해주는 기능

### 체스통 -> 체스말
- [x] 1개의 체스말을 생성해주는 기능

### 플레이어 -> 체스판
- [x] 내 체스위치를 받아오는 기능

- [x] 시작 커맨드 입력
    
## 1단계에서 도출한 객체
- 체스말
    - 하는것
        하나의 말을 생성
    - 아는것
        체스의 이름
        플레이어 플래그
- 체스통 
    - 하는 것
        모든 체스말을 생성하는 것
    - 아는 것
           
- 체스판
    - 하는것
        - 내 말의 위치 알려줘
    - 아는것
        - 체스위치와 체스말
- 플레이어
    - 아는것
        소인자인지 대문자인지
- 게임룰
    - 하는것
        말의 위치를 알려준다.
    - 아는것
         초기 말의 위치
         
         
## 2단계 기능목록
- 말을 이동한다(now, target)
    - now와 target이 같은 팀이 검사가 필요하다.
    - now가 말을 집어야한다.
    - target이 now에 있는 말의 범위이어야한다.
    - 말에따라 중간에 장애물이 있는지 확인해야한다.
    
    - 비숍 예외
        - 앞에 적이 없어야 이동가능하다.
        - 대각선에 적이 있어야 이동가능하다.
    
## 3단계 기능목록
- King이 잡혔을 때 게임이 종료
    - 체스판에서 킹의 갯수를 카운팅한다.
- 체스게임의 점수를 구할 수 있어야한다.
    - 체스판에서 같은 팀의 피스들을 가져와서 카운팅한다.
- status를 입력하면 점수정보가 나온다.
   