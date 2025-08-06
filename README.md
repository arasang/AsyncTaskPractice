# 🧪 비동기 환율 조회 테스트 서버

비동기 처리 연습을 위한 테스트용 서버입니다.  
외부 환율 API를 병렬로 호출하고, 결과를 취합하여 하나의 응답으로 반환합니다.

---

## ✅ 요구사항

- 아래 국가들의 환율 정보를 외부 API를 통해 **비동기적으로 조회**합니다.
- 모든 요청이 완료되면, 결과를 `List<ExchangeRateResponse>` 형태로 **한 번에 반환**합니다.

### 📌 대상 국가 및 통화 코드

| 국가       | 통화 코드 |
|------------|------------|
| 🇺🇸 미국     | USD        |
| 🇯🇵 일본     | JPY        |
| 🇪🇺 유럽연합 | EUR        |
| 🇨🇳 중국     | CNY        |
| 🇬🇧 영국     | GBP        |

---

## 🌐 환율 조회 API 정보

- 기본 API 엔드포인트:
  >https://api.exchangerate.host/convert?from=KRW&to={통화코드}


- 예시 요청 (USD):
  > GET https://api.exchangerate.host/convert?from=KRW&to=USD


---

## 📦 예시 응답 포맷

```json
{
"query": {
  "from": "KRW",
  "to": "USD",
  "amount": 1
},
"info": {
  "rate": 0.00072
},
"result": 0.00072
}
