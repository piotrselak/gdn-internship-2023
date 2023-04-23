# Backend oriented task (Java)
## Piotr Selak
-------

### Running backend
Everything needed to run backend is docker and docker-compose. Run:
```bash
docker-compose up
```
in directory to get backend running. It will be available on http://localhost:8080.

### Testing
There are two main ways to test the application. You can run frontend application or run curl queries.

#### Frontend
In order to run frontend, go into frontend directory and run:
```bash
npm i
npm run dev
```
frontend should be available on http://localhost:5173. From that point it can be tested through the ui.
Please note that some currency codes in frontend client may not work for all operations as all currency codes are fetch from NBP table A.

#### curl
