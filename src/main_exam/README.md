# Main Exam

시험에서 가장 중요하게 볼 파일 읽기, HTTP 통신, JSON/Gson 예제를 별도 패키지로 분리했습니다.

원칙:

- Java 8 호환 문법 사용
- 파일 읽기는 `BufferedReader` 기반으로 작성
- HTTP server는 Jetty 9 embedded server 사용
- HTTP client는 Jetty 9 `HttpClient` 사용
- JSON 파싱은 Google Gson `2.8.6` 사용

| 폴더 | 용도 |
| --- | --- |
| `file` | txt 파일 전체 읽기, List 저장, 라인별 처리 |
| `http` | Jetty embedded server, Jetty HttpClient, JSON body 통신 |
| `json` | Gson 2.8.6 기반 JSON 파일/문자열 파싱 |
