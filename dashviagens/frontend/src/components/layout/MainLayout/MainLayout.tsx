import type { ReactNode } from "react";

import { Navbar } from "../Navbar";
import { Footer } from "../Footer";

interface Props{

    children:ReactNode

}

export function MainLayout({children}:Props){

    return(

        <>

            <Navbar/>

            <main>

                {children}

            </main>

            <Footer/>

        </>

    )

}