# 10 ProcessBuilder

Java에서 다른 Java class를 자식 프로세스로 실행하거나 재시작하는 문제가 나오면 이 폴더를 봅니다.

| 파일 | 용도 |
| --- | --- |
| `MyProcess.java` | 같은 프로젝트의 다른 class를 여러 프로세스로 실행 |
| `ProcessExample.java` | 자식 프로세스 실행, 출력 수집, 종료 코드 확인 |
| `ChildProcess.java` | 자식 프로세스 역할 |
| `ProcessThreadExample.java` | 프로세스 안에서 thread 실행 |

가장 먼저 볼 파일: `MyProcess.java`, `ProcessExample.java`
