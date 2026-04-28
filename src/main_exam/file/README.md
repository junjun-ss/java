# Main Exam File

시험에서 txt 파일 읽기 문제가 나오면 이 패키지를 먼저 봅니다.

| 파일 | 용도 |
| --- | --- |
| `TextFileReadExample.java` | txt 파일 전체 읽기, List 저장, 라인별 처리 |
| `sample.txt` | 공백 구분 샘플 데이터 |

핵심 메서드:

- `readAllLines(filePath)`: 파일 전체를 `List<String>`으로 저장
- `sumSecondColumnByLine(filePath)`: 한 줄씩 읽으면서 즉시 파싱/처리
- `processEachLine(filePath, handler)`: 라인별 공통 처리 템플릿
