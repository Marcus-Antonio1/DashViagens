import { HeroSection }     from "../../components/home/HeroSection/HeroSection";
import { ExchangeSection } from "../../components/exchange/ExchangeSection/ExchangeSection";
import "./HomePage.css";

export function HomePage() {
  return (
    <>
      <HeroSection />
      <div className="home-container">
        <ExchangeSection />
      </div>
    </>
  );
}
