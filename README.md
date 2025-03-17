# MunJangZip_FE
![image](https://github.com/user-attachments/assets/474c0e21-d239-44d3-8713-b186114acc41)

MainAppComposable.kt
페이지 이동하는 라우팅

## ui 패키지
SimpleBackGround.kt - 배경(초원 그림)   

BackGround.kt - 배경(초원 그림 + 고양이)      

BackGroundBubble.kt - 배경 (초원 그림 + 고양이 + 말풍선)   
말풍선에 쓰일 이미지를 인수로 전달

### theme 패키지 (기본으로 있는 패키지)
Color.kt   
커스텀 색상 추가하고 싶으면 여기에 추가

## appbar 패키지
TopBarWidget.kt   
이런 구조의 탑바는 fun TopBarWidget() 실행   
![image](https://github.com/user-attachments/assets/7bc6429a-7cb1-4288-ab88-5da392dbc373)
   
원래 기획했던 이 탑바는 발바닥 아이콘 기능인 마이페이지가 아직 구현전이라 발바닥 아이콘을 뺌
![image](https://github.com/user-attachments/assets/743072e5-b6c3-4e3a-99fd-45846cc3063d)


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
![image](https://github.com/user-attachments/assets/03539991-c420-4fa1-a2bb-69c25f37d114)

### selectMemo
![image](https://github.com/user-attachments/assets/73498f0d-8be0-4c94-a5af-e4ac4dca3a2c)

## network 패키지
API 요청시 헤더에 자동으로 accessToken을 추가하는 interceptor를 만들어서 retrofit에 적용


## API구현 부분 로직
➕ Notice
알아야할게 있어서 작성합니다

헤드에 엑세스 토큰 넣어서 요청해야되는데 계속 넘기는게 어지러울까봐 토큰 분리했어요
어지러울까봐 예시 보여주고 싶어서 카테고리추가 연결해놨습니다!
accessToken 포함해서 요청 보내는 방법 =
RetrofitApi 작성하기 (파일에선 CategoryApi)
Request/Response 데이터 클래스 추가  (view 모델에서 api 요청할 수 있게 레포 만들기 & Retrofit API가 사용할 데이터 모델 만들기!!)
ViewModel에서 API 호출&상태 관리 (AddCategoryViewModel) 
요청 보낼 버튼 로직처리 (AddCategoryScreen ->onClick = { viewModel.addCategory(text) } 이런식으로)
di > appModule에 API ,레포 추가하기
토큰 관련 추가사항
DataStore설정하기
로그인 성공시 accessToken 을 저장해야돼서 DataStore 사용함.
data > UserPreferences.kt 파일입니다
로그인하면 aaccessToken을 DataStore에 저장하기
SignInViewModel.kt
대충 토큰 저장하는 부분이 있습니다
3.이제 API 요청시 헤더에 자동으로 accessToken을 추가하는 interceptor를 만들어서 retrofit에 적용하기

network > authInterceptor.kt
대충 헤더에 자동 추가하는 코드입니다
Retrofit을 설정할 때 AuthInterceptor를 추가하면, 모든 API 요청에 Authorization 헤더가 자동으로 포함
Retrofit 클라이언트에 AuthInterceptor 적용
di > AppModule.kt
