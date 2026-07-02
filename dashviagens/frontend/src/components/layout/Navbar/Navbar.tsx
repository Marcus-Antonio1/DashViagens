import { Link } from "react-router-dom";

export function Navbar() {
  return (
    <header>
      <nav>
        <Link to="/">DashViagens</Link>

        <div>
          <Link to="/">Início</Link>
          <Link to="/countries">Países</Link>
          <Link to="/budget">Calculadora</Link>
        </div>
      </nav>
    </header>
  );
}