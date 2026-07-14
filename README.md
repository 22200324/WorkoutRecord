# 운동 기록 관리 CRUD 프로젝트

Java와 MariaDB를 활용하여 운동 기록을 생성하고 조회, 수정, 삭제할 수 있도록 구현한 콘솔 기반 CRUD 프로젝트입니다.

## 주요 기능 및 실행 화면

### 운동 기록 추가

<img width="1235" height="497" alt="운동 기록 추가 실행 화면" src="https://github.com/user-attachments/assets/f09341b3-3665-4a2c-bba6-71c41b2e023c" />

### 전체 기록 조회

<img width="1203" height="373" alt="전체 기록 조회 실행 화면" src="https://github.com/user-attachments/assets/9008dc04-ae55-4756-b6aa-b7918cb7165e" />

### 운동 기록 상세 조회

<img width="1261" height="355" alt="운동 기록 상세 조회 실행 화면" src="https://github.com/user-attachments/assets/95f4eba6-529b-49d3-9c1f-7576ee3ee99b" />

### 운동 기록 수정

<img width="1145" height="500" alt="운동 기록 수정 실행 화면" src="https://github.com/user-attachments/assets/cb04616f-5b73-4140-9735-06f33cd5f3e2" />

### 운동 기록 삭제

<img width="752" height="336" alt="운동 기록 삭제 실행 화면" src="https://github.com/user-attachments/assets/c977e525-4a2a-4d10-92ef-85cddd508754" />

## 프로젝트 회고

이번 과제를 진행하면서 Java 애플리케이션이 구성되고 실행되는 전반적인 개발 흐름을 이해할 수 있었습니다. 특히 `model`, `repository`, `service`, `view`와 같이 역할에 따라 패키지를 분리하고, 각 패키지에 어떤 클래스와 로직이 위치해야 하는지를 직접 구현하며 계층별 책임과 코드의 흐름을 익혔습니다.

또한 Git Flow를 활용하는 과정에서 브랜치를 목적에 맞게 나누고, 작업한 내용을 단계적으로 관리하는 방법을 이전보다 더 정확하게 이해할 수 있었습니다. 이를 통해 단순히 코드를 작성하는 것뿐만 아니라 변경 이력을 체계적으로 관리하는 과정도 개발에서 중요하다는 점을 배웠습니다.

Docker를 사용해 MariaDB 환경을 구성하고 Java 애플리케이션과 데이터베이스를 연결하는 과정은 처음에는 다소 어색했습니다. 하지만 컨테이너 실행과 데이터베이스 연결 설정을 반복하며 전체 과정을 익혔고, 개발 환경을 일관되게 구성하는 Docker의 장점도 경험할 수 있었습니다.

이번 프로젝트는 Java 문법을 적용하는 데서 그치지 않고, 패키지 설계부터 데이터베이스 연결, CRUD 구현, Git을 활용한 버전 관리까지 하나의 애플리케이션을 완성하는 전체 과정을 경험했다는 점에서 의미가 있었습니다.
