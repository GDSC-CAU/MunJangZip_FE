# MunJangZip_FE
![image](https://github.com/user-attachments/assets/474c0e21-d239-44d3-8713-b186114acc41)

MainAppComposable.kt
페이지 이동하는 라우팅

## ui 패키지
SimpleBackGround.kt
배경(초원 그림)

BackGround.kt   
배경(초원 그림 + 고양이)   

BackGroundBubble.kt  
배경 (초원 그림 + 고양이 + 말풍선)   
- 말풍선에 쓰일 이미지를 인수로 전달

### theme 패키지 (기본으로 있는 패키지)
Color.kt   
커스텀 색상 추가하고 싶으면 여기에 추가

## appbar 패키지
TopBarWidget.kt   
이런 구조의 탑바는 fun TopBarWidget() 실행   
![image](https://github.com/user-attachments/assets/7bc6429a-7cb1-4288-ab88-5da392dbc373)

## data 패키지
사용자 토큰(accessToken, refreshToken)을 관리하는 역할   
SharedPreferences를 사용하여 토큰을 저장, 가져오기, 삭제하는 기능을 제공

## di 패키지
Retrofit + OkHttp 설정 → 네트워크 요청을 관리   
UserPreferences(토큰 저장소) 제공 → 로그인 상태 유지   
Hilt를 사용하여 의존성 관리 → 클래스 간 결합도 낮춤, 유지보수 편리   
각 API 및 Repository 객체를 싱글톤으로 제공 → 인스턴스 재사용으로 성능 향상   

## feature 패키지
auth - 로그인,회원가입 페이지들

### addCategory
![image](https://github.com/user-attachments/assets/cc5e7e23-5ded-439f-bddd-2d2fca5c984c)


### booklist   
![image](https://github.com/user-attachments/assets/297856e8-46a1-43bb-aba9-bdedd2b55baf)

## books
![image](https://github.com/user-attachments/assets/f4bf01f0-ab10-476b-a391-76f5e68aedb4)

### category   
![image](https://github.com/user-attachments/assets/17f3a450-eef6-4b28-8839-c6387a5faad2)


### createMemo
![image](https://github.com/user-attachments/assets/819c415b-967a-4ce0-8216-51e178dc75b7)

### loadBookInfo
책 정보 불러오기 성공
![image](https://github.com/user-attachments/assets/d6a13025-cc9e-49d5-801c-ea807996bfa3)
책정보 불러오기 실패
![image](https://github.com/user-attachments/assets/c8353da3-88d5-4ba0-b05a-7934080e678d)


#### savebook   
![image](https://github.com/user-attachments/assets/50226c1e-9652-46c3-9dfe-50ae87431e16)

### selectMemo
![image](https://github.com/user-attachments/assets/73498f0d-8be0-4c94-a5af-e4ac4dca3a2c)

## network 패키지
API 요청시 헤더에 자동으로 accessToken을 추가하는 interceptor를 만들어서 retrofit에 적용
