export interface Country {
  id: number;
  code: string;
  name: string;
  capital?: string;
  language?: string;
  currencyCode?: string;
  population?: number;
  timezone?: string;
  bestSeason?: string;
  bestSeasonDescription?: string;
  latitude?: number;
  longitude?: number;
  imageUrl?: string;
}
