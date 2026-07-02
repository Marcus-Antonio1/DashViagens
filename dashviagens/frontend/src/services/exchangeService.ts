import { api } from "./api";

export async function getExchangeRates(base = "BRL") {

    const response = await api.get("/exchange/rates", {

        params: {

            base,

        },

    });

    return response.data;

}