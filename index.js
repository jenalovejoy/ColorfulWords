// Jena Lovejoy
// index.js establishes the server and the connections between files

const express = require("express");
const app = express();
const port = 3000;
let allWordsRaw = [];

app.get("/", (req, res) => res.sendFile("ColorfulSite.html", {root: "public"}));
app.use("/styles", express.static("public/styles"));
app.use("/scripts", express.static("public/scripts"));

app.get("/api", (req, res) => {
    getAllWordsRaw(res);
        
});

app.listen(port, () => console.log("success!"));

function getAllWordsRaw(res){

    var fs = require("fs");
    fs.readFile('./AllHexWords.txt', 'utf-8', function(err, words) {
        allWordsRaw = words.split("\n").map(word => word.trim()).filter(word => word.length > 1);
        res.json({words : allWordsRaw});
    })    
}