'useStrict'
let done = 0;
function converter() {
    let input = document.getElementById('input').value;
    let results = document.getElementById('results');
    let inputVal = input; //il valore preso dall'input lo salvo in un'altra variabile ancora
    if (done !=1){    //ho usato questo if per verificare la variabile 'done' in modo che non mi stampa i risultati ogni volta che clicco il bottone
        fetch('https://open.er-api.com/v6/latest/EUR')
            .then(response => response.json()) 
            .then(convertedList => {
            let arrayValute = ['USD','JPY','GBP','CAD','AUD','CHF'];
            let i = 0;

            for(i ; i < 6; i++ ) {
                //console.log(convertedList.rates[arrayValute[i]]) //controllo che mi abbia preso le valute giuste
                let valuta = convertedList.rates[arrayValute[i]]
                console.log(valuta) //controllo che mi abbia salvato i valori delle valute che volevo io
                input = input * valuta;
                console.log(input) //controllo in console che mi abbia svolto correttamente la moltiplicazione
                let paragraph = document.createElement('p');
                paragraph.innerText =  + inputVal + " Euro diventano "+  input +" "+ arrayValute[i];
                results.appendChild(paragraph);
                input = inputVal //alla fine di ogni ciclo FOR riporto input al suo valore originale (quello che l'utente digita su html nel tag input)
            }  
        });
    }
    done = 1
};
