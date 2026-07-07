import { Link } from "react-router-dom";
import "./Footer.css";

export function Footer() {
  return (
    <footer className="footer">
      <div className="footer__inner">
        <div className="footer__brand">
          <svg className="footer__pin" viewBox="0 0 24 30" fill="none">
            <path
              d="M12 0C5.37 0 0 5.37 0 12C0 20 12 30 12 30S24 20 24 12C24 5.37 18.63 0 12 0Z"
              fill="#0F172A"
            />
            <circle cx="12" cy="12" r="5" fill="#F8FAFC" />
            <circle cx="12" cy="12" r="2.5" fill="#38BDF8" />
          </svg>
          <span className="footer__wordmark">
            Dash<span>Viagens</span>
          </span>
        </div>

        <nav className="footer__nav">
          <Link to="/" className="footer__nav-link">Início</Link>
          <Link to="/exchange" className="footer__nav-link">Câmbio</Link>
          <Link to="/countries" className="footer__nav-link">Países</Link>
          <Link to="/budget" className="footer__nav-link">Calculadora</Link>
        </nav>

        <div className="footer__right">
          <p className="footer__copy">
            © {new Date().getFullYear()} DashViagens
          </p>
          <p className="footer__apis">
            Frankfurter API · countries.dev · OpenStreetMap
          </p>
        </div>
      </div>
    </footer>
  );
}
