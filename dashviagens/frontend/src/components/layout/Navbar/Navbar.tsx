import { Link, useLocation } from "react-router-dom";
import "./Navbar.css";

export function Navbar() {
  const { pathname } = useLocation();

  const links = [
    { to: "/",           label: "Início"      },
    { to: "/exchange",   label: "Câmbio"      },
    { to: "/countries",  label: "Países"      },
    { to: "/attractions",  label: "Atrações"      },
    { to: "/budget",     label: "Calculadora" },
  ];

  return (
    <header className="navbar">
      <div className="navbar__inner">
        <Link to="/" className="navbar__logo">
          <svg className="navbar__pin" viewBox="0 0 24 30" fill="none">
            <path d="M12 0C5.37 0 0 5.37 0 12C0 20 12 30 12 30S24 20 24 12C24 5.37 18.63 0 12 0Z" fill="white"/>
            <circle cx="12" cy="12" r="5" fill="#0F172A"/>
            <circle cx="12" cy="12" r="2.5" fill="#38BDF8"/>
          </svg>
          <span className="navbar__wordmark">
            Dash<span>Viagens</span>
          </span>
        </Link>

        <nav className="navbar__links">
          {links.map(l => (
            <Link
              key={l.to}
              to={l.to}
              className={`navbar__link ${pathname === l.to ? "navbar__link--active" : ""}`}
            >
              {l.label}
            </Link>
          ))}
        </nav>

        <Link to="/countries" className="navbar__cta">
          Explorar destinos
        </Link>
      </div>
    </header>
  );
}
