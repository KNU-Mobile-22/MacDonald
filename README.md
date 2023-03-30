# knu_mobile_project

## 프로젝트 개요
코로나가 진행되며 많은 가게에서 키오스크를 도입하기 시작하였습니다.
키오스크 도입은 곧 인력에 사용되는 비용을 절감시켜 주었으며, 직원에게 서비스를 요청하는 것을 어려워 하는 사람들에게 도움을 주었습니다.
하지만, 키오스크의 사용이 어렵다는 사용자가 상당수 존재하는 것이 현실입니다.
키오스크를 개편하여 사용자 경험을 개선시키는 것이 이번 프로젝트의 목적입니다.

## 개선 방안
### 문제점 도출
+ 사용자 사이의 체감 난이도 편차가 큼
+ 원하는 메뉴 찾기 어려움
+ 키오스크 글자가 작아 사용하기 어려움
+ 주문 단계가 복잡함

### 개선 방법
+ 체감 난이도 편차가 큼, 글자가 작아 사용하기 어려움
  + 두가지 모드의 키오스크로 구성
    + 키오스크 사용이 익숙한 유저에게는 일반모드 제공
      + 간소화 된 가이드 제공 제공
      + 한 눈에 보이는 데이터의 양을 늘려 사용의 편의성을 높임
    + 키오스크 사용이 어려운 유저에게는 간편모드 제공
      + 글자와 이미지의 크기를 증가
      + 상세한 가이드 제공
+ 메뉴를 찾기가 어려움
  + 간편 모드에서 메뉴를 계층화 하여 제공
+ 주문 단계가 복잡함
  + 주문 단계를 간략화 하여 총 단계를 줄임
    + 기존의 세트 메뉴 선택은 사이드 메뉴와 음료, 추가 메뉴의 선택이 모두 다른 창에서 이루어짐
    + 이를 하나의 창에서 토글 메뉴로 구성하여 메뉴 선택 과정을 최소화

## 결과
### 프로젝트 흐름
![image](https://user-images.githubusercontent.com/76612738/228711919-6b95194b-d052-4979-a106-1ae34a49dfdb.png)

## 사용 기술
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=for-the-badge&logo=Android Studio&logoColor=white"> <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=Firebase&logoColor=white">

## 역할
|[장혁수](https://github.com/zangsu)|[손원준](https://github.com/sonwonjun103)|[이도훈](https://github.com/dleh1541)|[정도현](https://github.com/countryCat1202)|
|:-:|:-:|:-:|:-:|
|발표|PPT 작업|DB설계|디자인|
|일반모드 개발|결제 단계 개발|테스트 작업|간편모드 개발|
