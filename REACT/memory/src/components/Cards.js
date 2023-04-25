import { useState } from "react"
import Card from "./Card"

function Cards(){
    const [elements, setElements] = useState([
    { id: 1, img: "/img/1.jpg" , stat: ""},   //ogni card avrà il suo id per verificare l'uguaglianza, la foto 
    { id: 1, img: "/img/1.jpg" , stat: ""},   // e lo stat per cambiare la classe della card
    { id: 2, img: "/img/2.jpg" , stat: ""},
    { id: 2, img: "/img/2.jpg" , stat: ""},
    { id: 3, img: "/img/3.jpg" , stat: ""},
    { id: 3, img: "/img/3.jpg" , stat: ""},
    { id: 4, img: "/img/4.jpg" , stat: ""},
    { id: 4, img: "/img/4.jpg" , stat: ""},
    { id: 5, img: "/img/5.jpg" , stat: ""},
    { id: 5, img: "/img/5.jpg" , stat: ""},
    ].sort(() => Math.random() - 0.5)) //le raggruppa a caso ogni volta che la pagina viene ricaricata

    const [prev, setPrev] = useState(-1) //prev sarà usato per identificare la prima card della coppia che stai cercando di indovinare e ha valore base -1 

    function handleClick(id){ //si avvia questa funzione ad ogni click e prende l'id della card clickata
        if(prev === -1){    // controlla se prev -1, nel caso in cui è vero significa che è la prima della coppia che clickiamo e settiamo la card ad active per girarla  
            elements[id].stat = "active" //impostiamo a prev l'id della card clickata
            setElements([...elements])
            setPrev(id)
        }else{         //nel caso in cui prev non è -1 vuol dire che ha al suo interno  l'id di un'altra card 
            check(id) //per questo svolgiamo la funzione check per verificare l'uguaglianza fra la card appena clickata e quella precedente
        }

    }

    function check(current){  //current è l'id che abbiamo messo come parametro appena abbiamo chiamato il check e faremo le verifiche con l'id dentro current e quello dentro prev
        if(elements[current].id == elements[prev].id){ //se prev e current sono uguali abbiamo indovinato le card e le impostiamo con la classe correct
            elements[current].stat = "correct"
            elements[prev].stat = "correct"
            setElements([... elements])
            setPrev(-1) //reimpostiamo prev a -1 poichè adesso va indovinata una nuova coppia di cards
        }else{
            elements[current].stat = "wrong" //se prev e current non sono uguali abbiamo sbagliato le card e le impostiamo con la classe wrong
            elements[prev].stat = "wrong"
            setElements([... elements])
            setTimeout(() => {   //queste cards rimmarranno a wrong per 1s per dare un feedback all'utente, poi verrano resettate e rigirate
                elements[current].stat =""
                elements[prev].stat =""
                setElements([... elements])
                setPrev(-1) //reimpostiamo prev a -1 poichè adesso va indovinata una nuova coppia di cards
            }, 1000)
            
        }
    }



    return(
    <div className="container">      
        { elements.map((element, index) => (   
            <Card element={element} id={index} handleClick={handleClick}
            />

        ))}
     </div>
    )
}

export default Cards