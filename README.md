# Springboot-blog

자바 IDE 인 STS 를 사용하여 스프링부트 + JPA + MySQL을 사용한 블로그 프로젝트입니다.

메인 패키지 내에 각 역할을 담당하는 패키지를 구성하였습니다.
config(인증) / controller / dto / handler(오류발생시 핸들러) / model /repository / service

-----------------------------------------------------------------------------------------------------------------

각 페이지를 구성하는 JSP 파일들
![image](https://user-images.githubusercontent.com/99489461/208575964-306a31d9-f081-42f5-8a00-5e7ae7af122c.png)

-----------------------------------------------------------------------------------------------------------------

jquery 를 이용한 js 파일들(웹페이지 클릭 이벤트 시 발생하는 function 정의)
![image](https://user-images.githubusercontent.com/99489461/208578437-e7aa0b66-0527-4fe5-a0eb-2c7039569e4a.png)

-----------------------------------------------------------------------------------------------------------------

로컬호스트 메인페이지
![image](https://user-images.githubusercontent.com/99489461/208575702-9f6246f3-9239-4f31-9869-cda0eefc8ae9.png)
-----------------------------------------------------------------------------------------------------------------

상단 메뉴 '회원가입' 클릭시 해당 페이지로 이동(/auth/joinForm)
![image](https://user-images.githubusercontent.com/99489461/208578984-6993d3cd-84ba-4d52-a337-163923e457d2.png)
-----------------------------------------------------------------------------------------------------------------

회원가입 정보 입력 후 회원가입을 누르면 DB USER에 데이터가 들어가며 회원가입 완료 (비밀번호 해쉬 암호화)
![image](https://user-images.githubusercontent.com/99489461/208579734-3f7f51ba-2860-4a16-8046-82e894f03e96.png)
-----------------------------------------------------------------------------------------------------------------

로그인이 완료되면 상단 메뉴가 갱신되며 회원정보를 클릭하여 비밀번호 변경이 가능합니다.
![image](https://user-images.githubusercontent.com/99489461/208580216-4c862210-4a82-4d0b-8c96-b511215fce43.png)
![image](https://user-images.githubusercontent.com/99489461/208580250-ebdfd297-439f-49de-bb5e-36d5bec68db6.png)

-----------------------------------------------------------------------------------------------------------------

로그인시 메뉴 글쓰기가 활성화되며 글 업로드가 가능합니다.
![image](https://user-images.githubusercontent.com/99489461/208580456-04de6a62-9dd8-41a2-b310-88fe8f4b8074.png)

글쓰기 완료 버튼을 누르면 글이 업로드되며 메인으로 이동합니다.
![image](https://user-images.githubusercontent.com/99489461/208580568-c54295f2-6a35-48b5-b628-c9e78ee9b552.png)

상세보기 클릭시 해당 글의 내용을 볼 수 있습니다. (수정과 삭제 버튼은 작성자만 활성화됩니다.)
![image](https://user-images.githubusercontent.com/99489461/208580629-ee2cbe84-db5e-41fd-b8d9-3c3c13921366.png)

-----------------------------------------------------------------------------------------------------------------

댓글을 입력 후 등록 버튼을 누르면 댓글이 등록됩니다.
![image](https://user-images.githubusercontent.com/99489461/208581040-ead05e39-be62-4409-811f-92fa98d918c8.png)

DB와 댓글리스트에 댓글이 등록됩니다. (댓글 삭제는 댓글작성자만 활성화됩니다.)
![image](https://user-images.githubusercontent.com/99489461/208581762-60570136-cfc1-4894-8a36-0e37700e4d03.png)

-----------------------------------------------------------------------------------------------------------------

로그인 페이지에서 카카오 API를 활용하여 구현한 카카오로그인 버튼을 클릭시 카카오 계정으로 로그인 가능합니다.
![image](https://user-images.githubusercontent.com/99489461/208581872-873e4667-afc3-46e6-8aab-2c62bcbbaae7.png)
![image](https://user-images.githubusercontent.com/99489461/208581896-8aac311c-72a5-4889-bb19-21005ddfce58.png)
![image](https://user-images.githubusercontent.com/99489461/208582255-b58e0f22-9cca-42c1-a2af-d0de94a5e85b.png)

-----------------------------------------------------------------------------------------------------------------


글과 댓글의 삭제는 작성자 아이디로 로그인시에만 활성화됩니다.
![image](https://user-images.githubusercontent.com/99489461/208583182-c6e46169-e331-4cec-b7c3-793679caa027.png)
![image](https://user-images.githubusercontent.com/99489461/208583208-1509259f-fe4e-4625-b7d2-bb901bbedab6.png)

-----------------------------------------------------------------------------------------------------------------

메인 페이지의 글은 최신글 순으로 보여지며 3개가 넘어가면 페이징되어 previous, next 버튼으로 이동 가능합니다
![image](https://user-images.githubusercontent.com/99489461/208583390-64a1c842-976b-425b-8099-43f1acb8c330.png)
![image](https://user-images.githubusercontent.com/99489461/208583513-014a91bc-503d-4d24-b5ac-29747730544b.png)

-----------------------------------------------------------------------------------------------------------------

AWS EC2-Ubuntu 로 배포과정은 성공적으로 완료하였으나 서버에러가 떠서 구글링한 결과 sts의 버전과 jsp의 지원유무의 충돌로 배포가 성공적으로 완료되지 못하여 아쉬움이 남았습니다.

![aws rds](https://user-images.githubusercontent.com/99489461/208583979-9a6bdd4a-3177-4c8c-ba84-7d0aa872e566.PNG)
![aws 인스턴스](https://user-images.githubusercontent.com/99489461/208583998-8d7cc232-a474-4a32-a769-e47c8f2cfad8.PNG)
![ubuntu1](https://user-images.githubusercontent.com/99489461/208584017-1988c238-8be0-4ca8-8b75-c421b5672ccb.png)
![ubuntu2](https://user-images.githubusercontent.com/99489461/208584031-3f00abf3-cbc6-43c9-af1d-f667386f1d34.png)







