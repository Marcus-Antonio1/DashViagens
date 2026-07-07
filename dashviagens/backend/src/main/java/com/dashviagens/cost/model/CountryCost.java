package com.dashviagens.cost.model;

import java.math.BigDecimal;

import com.dashviagens.country.model.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country_costs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, unique = true)
    private Country country;

    @Column(name = "hotel_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal hotelPerDay;

    @Column(name = "food_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal foodPerDay;

    @Column(name = "transport_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal transportPerDay;

    @Column(name = "activities_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal activitiesPerDay;

    @Column(name = "estimated_flight", precision = 10, scale = 2)
    private BigDecimal estimatedFlight;

    @Column(nullable = false, length = 3)
    private String currency = "BRL";
}
