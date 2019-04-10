var express = require('express');
var app = express();
var fs = require('fs');
app.set('view engine', 'ejs');
app.get('/', function(req, res) {
    var obj = JSON.parse(fs.readFileSync('../PhoneBilling/jsonResult/billReport.json', 'utf8'));
    res.render('index',{data:obj});
});
app.listen(8080);
console.log('8080 is the magic port');