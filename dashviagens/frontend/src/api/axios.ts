import axios from "axios";

/**
 * O Vite proxy em vite.config.ts redireciona /api → http://localhost:8080
 * Então baseURL fica apenas "/api" — funciona em dev e é fácil de trocar pra produção.
 */
export const api = axios.create({
  baseURL: "/api",
  timeout: 10000,
  headers: { "Content-Type": "application/json" },
});
