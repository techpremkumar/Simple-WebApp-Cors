# CORS WebApp — Frontend

React 19 + Vite frontend for the Simple WebApp CORS product-catalog API.

## Requirements

- Node.js 20.19+ or 22.12+
- npm
- Spring Boot backend running on port 8080
- MySQL configured for the backend

## Getting started

```bash
npm install
npm run dev
```

Open the Vite URL printed in the terminal (typically http://localhost:5173).

## Available scripts

| Command | Description |
| --- | --- |
| `npm run dev` | Start the development server |
| `npm run build` | Create a production build |
| `npm run preview` | Preview the production build |
| `npm run lint` | Run Oxlint |

## Backend connection and CORS

The frontend currently requests the API at `http://localhost:8080/api/products`.
The backend must allow the frontend origin. Configure `CORS_ALLOWED_ORIGINS`
in the backend environment when using a different origin.

## Project structure

```text
src/
├── assets/
├── App.jsx
├── App.css
├── index.css
└── main.jsx
public/
```

## Production note

Do not hard-code credentials or private API keys in frontend source code.
Configure production API endpoints and backend CORS origins for the deployment
environment before publishing.
