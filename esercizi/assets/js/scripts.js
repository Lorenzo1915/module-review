'useStrict'
// window.onload = () => {  //non funziona perchè riesco a stampare automaticamente solo il mio ultimo valore dell'array(ho fatto più prove)
//     arrayValute = ["USD","JPY","GBP","CAD","AUD","CHF"]
//     let html=""
//     let i 
//     let option = document.createElement('option')
//     let select = document.getElementById('select1')
//         for(i=0  ; i < 6; i++){
//             html +="<option value='" + arrayValute[i] +"'>" + arrayValute[i] +"</option>" 
//             console.log(html)
//             select.innerHTML= html
//             html=""
            
//         }
        
        
   

// }
let done = 0;
function converter() {
    
    let select = document.getElementById('select1'); //salvo nella variabile select il mio tag select dell'html
    let value = select.options[select.selectedIndex].value; //salvo nella variabile value, il value dell'opzione scelta nel select
    if(value != 'all' ){  // con questo if controllo se nella select di html ha scelto un opzione che non sia con value "all", se la condizione è vera, salvo nel mio array la stringa della valuta selezionata
    value = select.options[select.selectedIndex].value;   
    arrayValute = [value]    
    }else{  //se l'opzione scelta ha value 'all' allora salvo nel mio array tutte le valute
        arrayValute = ["USD","JPY","GBP","CAD","AUD","CHF"]
    }
    let input = document.getElementById('input').value; //input messo dall'utente
    let results = document.getElementById('results');// è un article in cui stamperò successivamente i risultati
    let inputVal = input; //il valore preso dall'input lo salvo in un'altra variabile ancora
    if (done !=5){    //ho usato questo if per verificare la variabile 'done' in modo che mi stampi i risultati fino a massimo 5 click
        fetch('https://open.er-api.com/v6/latest/EUR')
            .then(response => response.json()) 
            .then(convertedList => {
            
            let i = 0;

            for(i ; i < arrayValute.length; i++ ) {
                //console.log(convertedList.rates[arrayValute[i]]) //controllo che mi abbia preso le valute giuste
                let valuta = convertedList.rates[arrayValute[i]]
                console.log(valuta) //controllo che mi abbia salvato i valori delle valute che volevo io
                input = input * valuta;
                console.log(input) //controllo in console che mi abbia svolto correttamente la moltiplicazione
                let paragraph = document.createElement('p');
                paragraph.innerText =  + inputVal + " Euro diventano "+  input +" "+ arrayValute[i];
                results.appendChild(paragraph);
                input = inputVal //alla fine di ogni ciclo FOR riporto input al suo valore originale (quello che l'utente digita su html nel tag input)
                console.log(arrayValute);
            }  
        });
        done ++
    }
    
};
