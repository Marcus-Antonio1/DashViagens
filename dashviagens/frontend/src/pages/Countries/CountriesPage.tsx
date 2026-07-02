import { useState } from "react";
import { useCountries } from "../../hooks/useCountries";
import { CountryGrid } from "../../components/countries";

export function CountriesPage() {
  const { data, isLoading, error } = useCountries();

  const [search, setSearch] = useState("");

  if (isLoading) return <h2>Carregando...</h2>;

  if (error || !data) return <h2>Erro ao carregar.</h2>;

  const filteredCountries = data.filter((country) =>
    country.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <>
      <h1>Países</h1>

      <input
        type="text"
        placeholder="Pesquisar país..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <p>{filteredCountries.length} países encontrados</p>

      <CountryGrid countries={filteredCountries} />
    </>
  );
}