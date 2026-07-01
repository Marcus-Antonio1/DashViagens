import { NavLink } from "react-router-dom";
import { Container } from "../../ui";

import "./Navbar.css";

export function Navbar() {
  return (
    <header className="navbar">
      <Container>
        <div className="navbar-content">

          <NavLink to="/" className="logo">
            🌍 DashViagens
          </NavLink>

          <nav>

            <NavLink to="/">Início</NavLink>

            <NavLink to="/countries">Países</NavLink>

            <NavLink to="/exchange">Câmbio</NavLink>

            <NavLink to="/budget">Calculadora</NavLink>

          </nav>

        </div>
      </Container>
    </header>
  );
}