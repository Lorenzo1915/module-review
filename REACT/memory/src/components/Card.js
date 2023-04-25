function Card({element, id, handleClick}){
    const elementClass = element.stat ? " active " + element.stat : "" //l'element.stat cambierà ad ogni nostro click sulle card, esso diventerà la classe della card cliccata
    return(
        <div className={"card" + elementClass} onClick={() => handleClick(id)}> 
            <img src={element.img} alt=""/>
        </div>
    )//se cliccate le card svolgeranno la funzione "handle clickf"//
}
export default Card