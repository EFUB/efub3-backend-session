# 📗 EFUB 3기 세션 수행 📗

### ☑️ Spring MVC 자주 사용되는 어노테이션

#### @Controller
- `@Controller`: 스프링에서 컨트롤러 레이어에 있는 객체임을 표시. 스프링 빈에 컨트롤러로서 등록.
- `@RestController`: REST 방식의 컨트롤러임을 명시
	- @Controller와 @ResponseBody의 기능을 동시에 사용하게 된다
		➡️ 해당 컨트롤러가 리턴하는 객체는 JSON 형태로 전달됨
- `@ResponseBody`: 컨트롤러 내부의 메소드에서 반환하는 객체들을 JSON 형태로 변환
- `@RequestMapping`: 특정한 URL 패턴에 맞는 컨트롤러인지 명시, 컨트롤러의 url 상세 주소를 작성한다

#### @Rest && @RequestMapping
- `@GetMapping`, `@PostMapping`, `@DeleteMapping`, `@PutMapping`: HTTP 전송 방식에 따라, 해당 메소드의 방식을 지정하는 경우에 사용
- `@ResponseStatus`: 반환하는 객체의 HTTP 응답의 상태 코드를 설정
- `@RequestMapping`: GET/POST 방식 모두를 지원하는 경우에 사용
- `@ResponseBody`: REST 방식에서 사용

#### @Prameters
- `@RequestParam`: Request에 있는 특정한 이름의 데이터를 파라미터로 받아서 처리하는 경우에 사용
- `@PathVariable`: Request에 있는 특정한 이름의 데이터를 파라미터로 받아서 처리하는 경우에 사용
- `@RequestBody`: HTTP 요청이 JSON임을 명시

<br>

### ⭐ Account CRUD 
🔻계정생성 POST
http://localhost:8080/accounts
![](https://velog.velcdn.com/images/chhaewxn/post/925ca815-cfe2-4621-9eca-700911305520/image.png)

🔻계정조회 GET
http://localhost:8080/accounts/3
![](https://velog.velcdn.com/images/chhaewxn/post/c393bfb6-f703-4227-a51d-0dac7cf31b00/image.png)

🔻계정수정 PATCH
http://localhost:8080/accounts/profile/3
![](https://velog.velcdn.com/images/chhaewxn/post/3227030c-940b-46d0-8dba-9a40a5d752df/image.png)

🔻계정삭제 PATCH 
http://localhost:8080/accounts/3
![](https://velog.velcdn.com/images/chhaewxn/post/a98656aa-7fa8-4a85-89f9-431251ffa7d9/image.png)

---

### ⭐ Post CRUD 
🔻게시글 작성 POST
http://localhost:8080/posts
![](https://velog.velcdn.com/images/chhaewxn/post/31b346f6-d8ab-4cef-877d-2e456c2d8123/image.png)

🔻게시글 조회_전체 GET
http://localhost:8080/posts
![](https://velog.velcdn.com/images/chhaewxn/post/4dd0f502-239b-41cf-8e27-32596680af38/image.png)

🔻게시글 조회_1개 GET
http://localhost:8080/posts/4
![](https://velog.velcdn.com/images/chhaewxn/post/5f4b31c4-9f0a-4175-a52c-e29fe6e22ff2/image.png)

🔻게시글 수정 PUT
http://localhost:8080/posts/4
![](https://velog.velcdn.com/images/chhaewxn/post/de1ab5a3-e80e-4153-bf89-39e9fe955f3a/image.png)
➕ 여기서 modifiedDate에 NULL이라고 뜬다. 해결하지 못함,,


🔻게시글 삭제 DELETE
http://localhost:8080/posts/4?accountId=3
![](https://velog.velcdn.com/images/chhaewxn/post/59f966fb-12e8-4b18-bc95-e174fd319a54/image.png)

>
```java
 @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId){
        postService.removePost(postId, accountId);
        return "성공적으로 삭제되었습니다.";
    }
    
```
➕ `RequestParam`의 대상 변수(여기서는 accountId)는 맵핑하는 url에 적지 않아야한다.
("/{postId}/{accountId}"라고 쓰면 안됨)
이 부분이 헷갈려서 많이 헤맸다 😭
