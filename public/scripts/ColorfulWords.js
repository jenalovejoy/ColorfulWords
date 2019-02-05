const allColors = [];
let allWordsRaw = [];

console.log("suh my dudes");

$(document).ready(function(){
    $.get("/api", "", data => generateColors(data));
    
})

function generateColors(data){
    allWordsRaw = data.words;

    for (let word of data.words){
        finishColor(word);
    }

    for (let color of allColors){
        $("#colorCollection").append($("<div />", {style: `background-color: ${color}`, "class": "colorItem"}).text(color));
    }
    

}

function finishColor(word){

    if (word.length === 6) {
        allColors.push("#" + word);
        return;
    } 

    for (let i = 0; i < 4; i++){
        allColors.push(padWord(word));
    }

}

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