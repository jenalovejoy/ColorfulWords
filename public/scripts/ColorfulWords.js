// Jena Lovejoy
// ColorfulWords.js takes in the array of all possible hex words, then generates another array of colors with those words

const allColors = [];
let allWordsRaw = [];

$(document).ready(function(){
    $.get("/api", "", data => generateColors(data));
    
})

// Creates the visual representation of colors
function generateColors(data){
    allWordsRaw = data.words;

    for (let word of data.words){
        finishColor(word);
    }

    for (let color of allColors){
        $("#colorCollection").append($("<div />", {style: `background-color: ${color}`, "class": "colorItem"}).text(color));
    }
}

// If a word is under 6 characters, makes multiple versions of the word
function finishColor(word){

    if (word.length === 6) {
        allColors.push("#" + word);
        return;
    } 

    for (let i = 0; i < 4; i++){
        allColors.push(padWord(word));
    }

}

// Brings a word under 6 characters to an accepted Hex color using random numbers to pad up to the proper length
function padWord(word){

    var length = 6 - word.length;
    var half = Math.floor(length / 2);
    var color = "#";

    for (var i = 0; i < (half + length % 2); i++){
        color += Math.floor(Math.random() * 10);
    }

    color += word;

    for (var i = 0; i < half; i++){
        color += Math.floor(Math.random() * 10);
    }

    return color;
}