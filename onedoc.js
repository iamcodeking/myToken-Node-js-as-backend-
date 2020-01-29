var express=require('express');
var mysql=require('mysql');
const bodyParser = require('body-parser');
var app=express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database:"mytoken"
});



var qu;
var q="INSERT INTO doctor( doc_name, doc_speciality, doc_degree, experience, location,available,curr_token,expected,max_token,fees) VALUES ('lakshay','node','cse','0','delhi',1,20,35,100,500)";

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");


});



var server=app.listen(3001);
app.post('/one',(req,res)=>{
    var data = req.body.id;
    var s="Select * from doctor where id_no='"+data+"'";
    con.query(s, function (err, result) {
        if (err) throw err;
        qu=result
    });
    res.status(200).json({
        qu
    });
});