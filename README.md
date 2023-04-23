# Backend oriented task (Java)
## Piotr Selak
-------

### Running backend
Everything needed to run backend is docker and docker-compose. Run:
```bash
docker-compose up
```
in repository root directory to get backend running. It will be available on http://localhost:8080.

### Testing
There are two main ways to test the application. You can run frontend application or run curl queries.

#### Frontend
In order to run frontend, go into frontend directory and run:
```bash
npm i
npm run dev
```
Frontend should be available on http://localhost:5173.
From that point it can be tested through the ui.

Please note that some currency codes in frontend client may
not work for all operations, as all currency codes are fetched from NBP table A.

#### curl
Backend may be tested using simple curl queries.

There are 3 endpoints available:
- /exchanges/{code}/{date} - given a date (formatted YYYY-MM-DD) and a currency code,
provide its average exchange rate.
- /exchanges/{code}/minmax/{n} - Given a currency code and the number of last
quotations n (n <= 255), provide the max and min average value.
- /exchanges/{code}/difference/{n} - Given a currency code and the number
of last quotations n (n <= 255), provide the major difference between
the buy and ask rate.

Examples queries to run:
```bash
curl http://localhost:8080/exchanges/USD/2023-04-19
curl http://localhost:8080/exchanges/CAD/2023-04-11
curl http://localhost:8080/exchanges/USD/minmax/100
curl http://localhost:8080/exchanges/CAD/minmax/254
```





