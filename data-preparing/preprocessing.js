const json = require('./input.json')
const res = [];
const requireKeys = ["name", "description", "wikipedia_url", "image"];
json.forEach(item => {
    for(let i = 0; i < requireKeys.length; i++) if (!item[requireKeys[i]] || !Object.keys(item[requireKeys[i]]).length){ return;}
    res.push( {
        name: item.name,
        description: item.description,
        wikipedia_url: item.wikipedia_url,
        image: item.image
    })
})

const fs = require('fs')
const path = process.argv[1];

const outputFilePath = path.slice(0, path.lastIndexOf("/")+1) + "output.json";

fs.writeFile(outputFilePath, JSON.stringify(res, null, 3), "utf8", (err) => {
    if (err){
        console.error(err)
    }
})

// console.log(JSON.stringify(res, null, 3))
