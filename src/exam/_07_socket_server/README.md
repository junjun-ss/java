# 07 Socket Server

TCP socket, 실시간 데이터 수신, `ServerSocket` 문제가 나오면 이 폴더를 봅니다.

| 파일 | 용도 |
| --- | --- |
| `RealTimeStreamingServer.java` | client 접속마다 thread를 만들어 데이터를 읽는 서버 |

가장 먼저 볼 파일: `RealTimeStreamingServer.java`

`main()`은 임시 포트로 서버를 띄운 뒤 내장 client가 메시지를 보내고 종료하는 자기완결 예제입니다.
